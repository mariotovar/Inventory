package com.mx.base.models.catalog;

import java.util.Date;

public class Expense {
	
	private Long pk;
	private int year;
	private int type;
	private double amountMXN;
	private double amountUSD;
	private String notes;
	private Date expenseDate;
	
	public Expense(){
		this.expenseDate = new Date();
	}

	
	public Long getPk() {
		return pk;
	}
	public void setPk(Long pk) {
		this.pk = pk;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
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
	public Date getExpenseDate() {
		return expenseDate;
	}
	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	@Override
	public String toString() {
		return "Expense [type=" + type + ", amountMXN=" + amountMXN + ", amountUSD=" + amountUSD + ", notes=" + notes
				+ ", expenseDate=" + expenseDate + "]";
	}
	
}
