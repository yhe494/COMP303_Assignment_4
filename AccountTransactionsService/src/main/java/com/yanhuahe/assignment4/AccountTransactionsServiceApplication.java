package com.yanhuahe.assignment4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
@EnableDiscoveryClient
@SpringBootApplication
public class AccountTransactionsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountTransactionsServiceApplication.class, args);
	}

}
