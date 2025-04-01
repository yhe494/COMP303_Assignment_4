package com.yanhuahe.assignment4;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {	
	List<Transaction> findByUsername(String username);
}
