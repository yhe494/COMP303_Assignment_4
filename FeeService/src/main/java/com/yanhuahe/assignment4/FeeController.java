package com.yanhuahe.assignment4;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;
@RestController
@RequestMapping("/fees")
public class FeeController {
	
	@Autowired
	private FeeService feeService;
	
	@GetMapping("/calculate/{orderId}")
	public Map<String, Object> getFee(@PathVariable String orderId) {
		return feeService.calculateFee(orderId);
	}

}
