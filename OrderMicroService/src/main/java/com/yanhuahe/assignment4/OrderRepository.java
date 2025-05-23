package com.yanhuahe.assignment4;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends  MongoRepository<Order, String>{
	
	List<Order> findByUsername(String username);

}
