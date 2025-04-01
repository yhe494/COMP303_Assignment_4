package com.yanhuahe.assignment4;
import org.springframework.stereotype.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;


import com.fasterxml.jackson.databind.ObjectMapper;
@Service
public class FeeService {
	
	@Autowired
	private FeeRuleRepository feeRuleRepository;
	
	@Autowired
	private RestTemplate restTemplate;
	
	private final String orderServiceUrl = "http://OrderMicroService/orders/id/";
	
	public Map<String, Object> calculateFee(String orderId){
		Map<String, Object> response = new HashMap<>();
		try {
			String orderJson = restTemplate.getForObject(orderServiceUrl+orderId, String.class);
			
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> order = mapper.readValue(orderJson, HashMap.class);
			
			String stockSymbol = (String) order.get("stockSymbol");
			int unit = (int) order.get("unit");
			double price = (double) order.get("price");
			
			FeeRule feeRule = feeRuleRepository.findByStockSymbol(stockSymbol);
			double feeRate = (feeRule != null) ? feeRule.getFeeRate() : 0.05;
			
			double totalPrice = unit * price;
			double fee = totalPrice * feeRate;
			double finalPrice = totalPrice + fee;
			
			response.put("OrderId", orderId);
			response.put("StockSymbol", stockSymbol);
			response.put("Unit", unit);
			response.put("Price", price);
			response.put("feeRate", feeRate);
			response.put("TotalPrice", totalPrice);
			response.put("Fee", fee);
			response.put("FinalPrice", finalPrice);
			
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			response.put("Error", e.getMessage());
		}
		return response;
	}

}
