package com.movingapp.view;

import java.util.Date;


public class ChargeView{
	
	private int id;
	private long moveid;
	private Date date;
	private double amount;
	
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
}