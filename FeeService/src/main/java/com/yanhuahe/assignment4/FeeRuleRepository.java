package com.yanhuahe.assignment4;
import org.springframework.data.mongodb.repository.MongoRepository;
public interface FeeRuleRepository extends MongoRepository<FeeRule, String> {

	FeeRule findByStockSymbol(String stockSymbol);

}
