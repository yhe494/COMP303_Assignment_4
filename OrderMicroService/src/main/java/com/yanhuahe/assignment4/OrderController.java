package com.yanhuahe.assignment4;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;
	
	@GetMapping("/placeOrder")
	public ModelAndView showOrderForm() {
		return new ModelAndView("order-form");
	}
	
	@GetMapping("/id/{orderId}")
	public Order getOrderById(@PathVariable String orderId) {
        return orderService.getOrderById(orderId);
        }
	
	@PostMapping("/place")
	public ModelAndView placeOrder(@RequestParam String username, @RequestParam String stockSymbol, @RequestParam int unit,
	        @RequestParam double price) {
	    Order order = new Order(username, stockSymbol, unit, price);
	    Order savedOrder = orderService.saveOrder(order);
	    return new ModelAndView("redirect:/orders/summary/" + savedOrder.getId());
	}

	
	@GetMapping("/summary/{orderId}")
    public ModelAndView showOrderSummary(@PathVariable String orderId, Model model) {
        model.addAttribute("order", orderService.getOrderSummary(orderId));
        return new ModelAndView("order-summary");
    }
	
	@GetMapping("/{username}")
	public String getOrders(@PathVariable String username, Model model) {
		List<Order> orders = orderService.getOrdersByUsername(username);
		
		model.addAttribute("orders", orders);
		return "orders-list";
	}
}
