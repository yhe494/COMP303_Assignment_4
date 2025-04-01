package com.yanhuahe.assignment4;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
@Document(collection = "fee_rule")
public class FeeRule {
	
	@Id
	private String id;
	private String stockSymbol;
	private double feeRate;
	
	public FeeRule() {
		super();
	}

	public FeeRule(String stockSymbol, double feeRate) {
		super();
		this.stockSymbol = stockSymbol;
		this.feeRate = feeRate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public double getFeeRate() {
		return feeRate;
	}

	public void setFeeRate(double feeRate) {
		this.feeRate = feeRate;
	}
	
	
}
