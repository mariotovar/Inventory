package com.mx.base.models.catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mx.base.util.response.StatusOrder;

public class PurchaseCart {
	

	private String orderNumber;		
	private String user;
	private int pkProvider;
	private Date purchaseDate;
	private List<Item> items;
	private List<Payment> payments;
	private List<Expense> expenses;
	private StatusOrder status;
	
	public PurchaseCart(){
		
		this.setStatus(StatusOrder.CLOSE);
		this.items = new ArrayList<Item>();
		for(int i=0; i<3; i++){
			items.add(new Item());
		}		
		/*
		this.payments = new ArrayList<Payment>();
		for(int i=0; i<3; i++){
			payments.add(new Payment());
		}
		this.expenses = new ArrayList<Expense>();
		for(int i=0; i<3; i++){
			expenses.add(new Expense());
		}
		*/		
	}

	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public int getPkProvider() {
		return pkProvider;
	}
	public void setPkProvider(int pkProvider) {
		this.pkProvider = pkProvider;
	}	
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}	
	public List<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(List<Expense> expenses) {
		this.expenses = expenses;
	}
	public StatusOrder getStatus() {
		return status;
	}
	public void setStatus(StatusOrder status) {
		this.status = status;
	}

	public double getTotalUSD(){
		double total = 0;
		if(items!=null){
			for(Item item: this.items){
				total += item.getSubtotalUSD();
			}			
		}
		return total;
	}
	public double getTotalMXN(){
		double total = 0;
		if(items!=null){
			for(Item item: this.items){
				total += item.getSubtotalMXN();
			}
		}
		return total;
	}
	public double getAmountReceived(){
		double amount = 0;
		if(this.items!=null){
			for(Item item: this.items){
				if(item.getInputs()!=null){
					for(InputReceived input: item.getInputs()){
						amount += item.getPriceUSD() * input.getReceivedQty();
					}			
				}
			}
		}
		return amount;
	}		
	public double getAmountPaid(){
		double amount = 0;
		if(this.payments!=null){
			for(Payment payment: this.payments){
				amount += payment.getAmountMXN();
			}						
		}
		return amount;
	}	
	public double getAmountToPay(){
		double amount = 0;
		amount = this.getTotalUSD() - getAmountPaid();
		return amount;
	}		
	public double getAmountExpenses(){
		double amount = 0;
		if(this.expenses!=null){
			for(Expense expense: this.expenses){
				amount += expense.getAmountMXN();
			}						
		}
		return amount;
	}	
	
}
