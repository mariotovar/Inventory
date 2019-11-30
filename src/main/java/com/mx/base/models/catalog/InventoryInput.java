package com.mx.base.models.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class InventoryInput implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	private Long pk;	
	private int year;
	private Date inventoryDate;
	private String user;
	private List<InventoryInputRow> rows;
		
	public InventoryInput(){
		this.rows = new ArrayList<InventoryInputRow>();
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
	public List<InventoryInputRow> getRows() {
		return rows;
	}
	public void setRows(List<InventoryInputRow> rows) {
		this.rows = rows;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public String getOrderNumber() {
		String orderNumber;		
		orderNumber = "II" + (this.getYear() % 100) + '-' + String.format("%04d", this.getPk());
		return orderNumber;
	}

	public double getTotalUSD(){
		double total = 0;
		if(rows!=null){
			for(InventoryInputRow row: this.rows){
				total += row.getSubtotalUSD();
			}			
		}
		return total;
	}
	public double getTotalMXN(){
		double total = 0;
		if(rows!=null){
			for(InventoryInputRow row: this.rows){
				total += row.getSubtotalMXN();
			}			
		}
		return total;
	}
		
	@Override
	public String toString() {
		return "InventoryInput [pk=" + pk + ", inventoryDate=" + inventoryDate + ", rows="
				+ rows + "]";
	}
	
	
}
