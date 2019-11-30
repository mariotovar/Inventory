package com.mx.base.models.catalog;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InventoryOutput {

	private Long pk;
	private int year;
	private Date inventoryDate;
	private List<Item> items;
	private String user;
	
	public InventoryOutput(){
		this.items = new ArrayList<Item>();
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
	public Date getInventoryDate() {
		return inventoryDate;
	}
	public void setInventoryDate(Date inventoryDate) {
		this.inventoryDate = inventoryDate;
	}
	public List<Item> getItems() {
		return items;
	}
	public void setItems(List<Item> items) {
		this.items = items;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}	
	public String getOrderNumber(){		
		String orderNumber;		
		orderNumber = "IO" + (this.getYear() % 100) + '-' + String.format("%04d", this.getPk());
		return orderNumber;	
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
		return total;
	}	

	@Override
	public String toString() {
		return "InventoryOutput [pk=" + pk + ", inventoryDate=" + inventoryDate + "]";
	}

	
}
