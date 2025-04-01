package com.yanhuahe.assignment4;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("/reserve")
	public Transaction reserveStock(@RequestBody Map<String, Object> payload) {
		String username = (String) payload.get("username");
		String stockSymbol = (String) payload.get("stockSymbol");
		int reservedUnits;
		double price;
		
		if(payload.get("reservedUnits") instanceof Integer) {
            reservedUnits = (Integer) payload.get("reservedUnits");
            } else {
            	reservedUnits = ((Number)payload.get("reservedUnits")).intValue();
            }
		if(payload.get("price") instanceof Double) {
			price = (Double) payload.get("price");
        } else {
            price = ((Number)payload.get("price")).doubleValue();
		}
		
		return transactionService.reserveStock(username, stockSymbol, reservedUnits, price);
	}
	
	@GetMapping("/{username}")
	public List<Transaction> getTransactions(@PathVariable String username) {
		return transactionService.getReservedStocks(username);
	}
	
}
