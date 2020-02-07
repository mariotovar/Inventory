package com.mx.base.models.catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mx.base.util.response.StatusOrder;

public class QuoteOrder{

	private int year;
	private long pkQuote;
	private String user;
	private Date quoteDate;
	private Client client;
	private List<Item> items;
	private StatusOrder status;
	private String notes;
	
	private double factorIva;
	
	public QuoteOrder(){		
		this.items = new ArrayList<Item>();
	}
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public long getPkQuote() {
		return pkQuote;
	}
	public void setPkQuote(long pkQuote) {
		this.pkQuote = pkQuote;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public Date getQuoteDate() {
		return quoteDate;
	}
	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public StatusOrder getStatus() {
		return status;
	}
	public void setStatus(StatusOrder status) {
		this.status = status;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getOrderNumber() {
		String orderNumber;		
		orderNumber = "QO" + (this.getYear() % 100) + '-' + String.format("%04d", this.getPkQuote());
		return orderNumber;
	}
	
	public double getTotalUSD(){
		double total = 0;
		if(items!=null){
			for(Item item: this.items){
				total += item.getSubCostUSD();
			}			
		}
		return total;
	}
	public double getTotalMXN(){
		double total = 0;
		if(items!=null){
			for(Item item: this.items){
				total += item.getSubCostMXN();
			}
		}
		return total;
	}


	public double getFactorIva() {
		return factorIva;
	}


	public void setFactorIva(double factorIva) {
		this.factorIva = factorIva;
	}	
	
}
