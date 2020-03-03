package com.mx.base.models.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mx.base.util.response.StatusOrder;

public class PurchaseOrder implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long pkPurchase;
	private int year;
	private int factorConversion;
	private Provider provider;
	private Date purchaseDate;
	private List<Item> items;
	private List<Payment> payments;
	private List<Expense> expenses;
	private StatusOrder status;
	private String user;
	private Shipto shipto;
	private String notes;
	private boolean emailing;
	
	public PurchaseOrder(){
		this.setProvider(new Provider());
		this.items = new ArrayList<Item>();
		this.payments = new ArrayList<Payment>();
		this.expenses = new ArrayList<Expense>();
	
	}
	public PurchaseOrder(int factorConversion){
		this.setFactorConversion(factorConversion);
	}
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}	
	public Provider getProvider() {
		return provider;
	}
	public void setProvider(Provider provider) {
		this.provider = provider;
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
	public boolean isEmailing() {
		return emailing;
	}
	public void setEmailing(boolean emailing) {
		this.emailing = emailing;
	}
	public Long getPkPurchase() {
		return pkPurchase;
	}
	public void setPkPurchase(Long pkPurchase) {
		this.pkPurchase = pkPurchase;
	}
	public String getNotes() {
		if(notes!=null && notes.length() > 200){
			notes =  notes.substring(0,199);
		}
		return  notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}	
	public Shipto getShipto() {
		return shipto;
	}
	public void setShipto(Shipto shipto) {
		this.shipto = shipto;
	}

	public String getOrderNumber() {
		String orderNumber;		
		orderNumber = "PO" + (this.getYear() % 100) + '-' + String.format("%04d", this.getPkPurchase());
		return orderNumber;
	}

	public String getStatusName() {
		String statusName;		
		statusName = this.getStatus().name();
		return statusName;
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
	public double getAmountPaidMXN(){
		double amount = 0;
		if(this.payments!=null){
			for(Payment payment: this.payments){
				amount += payment.getAmountMXN();
			}						
		}
		return amount;
	}
	public double getAmountPaidUSD(){
		double amount = 0;
		if(this.payments!=null){
			for(Payment payment: this.payments){
				amount += payment.getAmountUSD();
			}						
		}
		return amount;
	}	
	public double getAmountToPay(){
		double amount = 0;
		amount = this.getTotalUSD() - getAmountPaidUSD();
		return amount;
	}		
	public double getAmountExpensesMXN(){
		double amount = 0;
		if(this.expenses!=null){
			for(Expense expense: this.expenses){
				amount += expense.getAmountMXN();
			}						
		}
		return amount;
	}
	public double getAmountExpensesUSD(){
		double amount = 0;
		if(this.expenses!=null){
			for(Expense expense: this.expenses){
				amount += expense.getAmountUSD();
			}						
		}
		return amount;
	}


	public int getFactorConversion() {
		return factorConversion;
	}


	public void setFactorConversion(int factorConversion) {
		this.factorConversion = factorConversion;
	}	



}
