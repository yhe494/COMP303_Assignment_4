package com.yanhuahe.assignment4;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transactions")
public class Transaction {
	
	@Id
	private String id;
	private String username;
	private String stockSymbol;
	private int reservedUnits;
	private double price;
	private String status;
	
	public Transaction() {

	}
	
	public Transaction(String username, String stockSymbol, int reservedUnits, double price, String status) {
		this.username = username;
		this.stockSymbol = stockSymbol;
		this.reservedUnits = reservedUnits;
		this.price = price;
		this.status = status;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public int getReservedUnits() {
		return reservedUnits;
	}

	public void setReservedUnits(int reservedUnits) {
		this.reservedUnits = reservedUnits;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
