package com.yanhuahe.assignment4;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	@Autowired
	private TransactionRepository transactionRepository;
	
	public Transaction reserveStock(String username, String stockSymbol, int reservedUnits, double price) {
		Transaction transaction = new Transaction(username, stockSymbol, reservedUnits, price, "RESERVED");
		return transactionRepository.save(transaction);
	}
	
	public List<Transaction> getReservedStocks(String username) {
		return transactionRepository.findByUsername(username);
	}
}
