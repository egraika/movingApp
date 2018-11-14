package com.movingapp.view;

import java.util.Date;


public class ChargeView{
	
	private int id;
	private long moveid;
	private Date date;
	private String type;
	private double amount;
	private String chargeid;
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public Date getDate() {
		return date;
	}

	public long getMoveid() {
		return moveid;
	}

	public void setMoveid(long moveid) {
		this.moveid = moveid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChargeid() {
		return chargeid;
	}

	public void setChargeid(String chargeid) {
		this.chargeid = chargeid;
	}
}