package com.yanhuahe.assignment4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private RestTemplate restTemplate;
    
    private final String transactionServiceUrl = "http://AccountTransactionsService/transactions/reserve";
    private final String feeServiceUrl = "http://FeeService/fees/calculate/";

    public Order saveOrder(Order order) {
        Order savedOrder = orderRepository.save(order);
        
        try {
            Map<String, Object> payload = new HashMap<>();
            payload.put("username", order.getUsername());
            payload.put("stockSymbol", order.getStockSymbol());
            payload.put("reservedUnits", order.getUnit());
            payload.put("price", order.getPrice());
            
            ObjectMapper mapper = new ObjectMapper();
            String jsonPayload = mapper.writeValueAsString(payload);
            
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            
            HttpEntity<String> request = new HttpEntity<>(jsonPayload, headers);
            
            // Call transaction service and capture response as a Map
            Map<String, Object> transactionResponse = restTemplate.postForObject(
                transactionServiceUrl, 
                request, 
                Map.class
            );
            
            // Update order status if transaction was successful
            if (transactionResponse != null && "RESERVED".equals(transactionResponse.get("status"))) {
                savedOrder.setStatus("Reserved");
                savedOrder = orderRepository.save(savedOrder);
            }
            
        } catch(Exception e) {
            System.err.println("Error calling Transaction Service: " + e.getMessage());
        }
        return savedOrder;
    }
    
    public Map<String, Object> getOrderSummary(String orderId) {
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new RuntimeException("Order not found"));
        Map<String, Object> summary = new HashMap<>();
        summary.put("orderId", order.getId());
        summary.put("stockSymbol", order.getStockSymbol());
        summary.put("unit", order.getUnit());
        summary.put("pricePerShare", order.getPrice());
        summary.put("totalPrice", order.getPrice() * order.getUnit());
        summary.put("status", order.getStatus());

        // Fetch Total Fee from Fee Service
        Map<String, Object> feeResponse = restTemplate.getForObject(feeServiceUrl + order.getId(), Map.class);
        Double totalFee = feeResponse != null ? ((Number)feeResponse.get("Fee")).doubleValue() : 0.0;
        summary.put("totalFee", totalFee);
        summary.put("finalCost", order.getPrice() * order.getUnit() + totalFee);

        return summary;
    }
    
    public List<Order> getOrdersByUsername(String username) {
        return orderRepository.findByUsername(username);
    }
    
    public Order getOrderById(String orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));
    }
}