package com.mx.base.models.catalog;

import java.util.Date;

public class Payment {
	
	private long pk;
	private int year;
	private double amountMXN;
	private double amountUSD;
	private String notes;
	private Date paymentDate;
	private String attach;
	
	public long getPk() {
		return pk;
	}
	public void setPk(long pk) {
		this.pk = pk;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Date getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	public double getAmountMXN() {
		return amountMXN;
	}
	public void setAmountMXN(double amountMXN) {
		this.amountMXN = amountMXN;
	}
	public double getAmountUSD() {
		return amountUSD;
	}
	public void setAmountUSD(double amountUSD) {
		this.amountUSD = amountUSD;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}

	@Override
	public String toString() {
		return "Payment [pk=" + pk + ", amountMXN=" + amountMXN + ", amountUSD=" + amountUSD + ", notes=" + notes
				+ ", paymentDate=" + paymentDate + "]";
	}
	
	public String getPayNumber(){		
		String orderNumber;		
		orderNumber = "PAY-" + String.format("%06d", this.getPk());
		return orderNumber;	
	}
	
}
