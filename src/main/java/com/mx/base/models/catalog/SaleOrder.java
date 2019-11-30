package com.mx.base.models.catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mx.base.util.response.StatusOrder;

public class SaleOrder{

	private int year;
	private long pkSale;
	private String user;
	private Date saleDate;
	private Client client;
	private List<Item> items;
	private List<Payment> payments;
	private boolean emailing;
	private StatusOrder status;
	private double shippingCostMXN;
	private double shippingCostUSD;
	private double taxIVA;
	private boolean isCharge;
	private String notes;
	
	public SaleOrder(){		
		this.isCharge = true;
		this.items = new ArrayList<Item>();
		this.payments = new ArrayList<Payment>();
	}
	
	
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public long getPkSale() {
		return pkSale;
	}
	public void setPkSale(long pkSale) {
		this.pkSale = pkSale;
	}	
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
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
	public List<Payment> getPayments() {
		return payments;
	}
	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}
	public boolean isEmailing() {
		return emailing;
	}
	public void setEmailing(boolean emailing) {
		this.emailing = emailing;
	}	
	public StatusOrder getStatus() {
		return status;
	}
	public void setStatus(StatusOrder status) {
		this.status = status;
	}
	public double getShippingCostMXN() {
		return shippingCostMXN;
	}
	public void setShippingCostMXN(double shippingCostMXN) {
		this.shippingCostMXN = shippingCostMXN;
	}
	public double getShippingCostUSD() {
		return shippingCostUSD;
	}
	public void setShippingCostUSD(double shippingCostUSD) {
		this.shippingCostUSD = shippingCostUSD;
	}
	public double getTaxIVA() {
		return taxIVA;
	}
	public void setTaxIVA(double taxIVA) {
		this.taxIVA = taxIVA;
	}
	public boolean isCharge() {
		return isCharge;
	}
	public void setCharge(boolean isCharge) {
		this.isCharge = isCharge;
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
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
	public double getTotalUSD(){
		double total = 0;
		if(this.getItems()!=null){
			for(Item item: this.getItems()){
				if(item.getInputs()!=null){
					for(InputReceived input: item.getInputs()){
						if(input.getOutputs()!=null){
							for(OutputSold output: input.getOutputs()){
								total += output.getCostUSD() * output.getQty();
							}
						}
					}
				}
			}			
		}
		if(this.isCharge){
			total += this.shippingCostUSD;
		}		
		return total;
	}
	public double getTotalMXN(){
		double total = 0;
		if(this.getItems()!=null){
			for(Item item: this.getItems()){
				if(item.getInputs()!=null){
					for(InputReceived input: item.getInputs()){
						if(input.getOutputs()!=null){
							for(OutputSold output: input.getOutputs()){
								total += output.getCostMXN() * output.getQty();
							}
						}
					}
				}
			}			
		}
		if(this.isCharge){
			total += this.shippingCostMXN;
		}		
		return total;
	}
	
	public double getAmountCharged(){
		double amount = 0;
		if(this.payments!=null){
			for(Payment payment: this.payments){
				amount += payment.getAmountMXN();
			}						
		}
		return amount;
	}	
	public double getAmountToCharge(){
		double amount = 0;
		amount = this.getTotalMXN() - this.getAmountCharged();
		return amount;
	}
	
	
	
	public String getOrderNumber() {
		String orderNumber;		
		orderNumber = "SO" + (this.getYear() % 100) + '-' + String.format("%04d", this.getPkSale());
		return orderNumber;
	}
	
	
	public double getTotalNoteMXN(){
		double total = 0;
		if(this.getItems()!=null){
			for(Item item: this.getItems()){
				if(item.getInputs()!=null){
					for(InputReceived input: item.getInputs()){
						if(input.getOutputs()!=null){
							for(OutputSold output: input.getOutputs()){
								total += output.getCostMXN() * output.getQty();
							}
						}
					}
				}
			}			
		}	
		return total;
	}

	public double getIVATotalNoteMXN(){

		double iva = 0;
		double total = 0;
		double ivaTotal = 0;
		
		iva = this.getTaxIVA();
		total = this.getTotalNoteMXN();
		ivaTotal = total * (iva);
				
		return ivaTotal;
	}	
	
	public double getSubTotalNoteMXN(){
		
		double total = 0;
		double ivaTotal = 0;
		double subtotal = 0;

		total = this.getTotalNoteMXN();
		ivaTotal = this.getIVATotalNoteMXN();
		subtotal = total - ivaTotal;
				
		return subtotal;
		
	}	
	
}
