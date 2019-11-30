package com.mx.base.models.catalog;

import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;

public class OutputSold {
	
	private int qty;
	private double costUSD;
	private double costMXN;		
	private Date outputDate;
	private String  notes;
	private String user;
	
	public OutputSold(){
	
	}

	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getCostUSD() {
		return costUSD;
	}
	public void setCostUSD(double costUSD) {
		this.costUSD = costUSD;
	}
	public double getCostMXN() {
		return costMXN;
	}
	public void setCostMXN(double costMXN) {
		this.costMXN = costMXN;
	}	
	public Date getOutputDate() {
		return outputDate;
	}
	public void setOutputDate(Date outputDate) {
		this.outputDate = outputDate;
	}	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public double getSubtotalUSD(){
		double subtotal = 0;
		subtotal = this.getQty() * this.getCostUSD();
		return subtotal;
	}
	
	public double getSubtotalMXN(){
		double subtotal = 0;
		subtotal = this.getQty() * this.getCostMXN();
		return subtotal;
	}	
	
	@Override
	public String toString() {
		return "OutputSold [qty=" + qty + ", costUSD=" + costUSD + ", outputDate=" + outputDate + ", user=" + user + "]";
	}
	
}
