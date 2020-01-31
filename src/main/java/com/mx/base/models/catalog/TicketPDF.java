package com.mx.base.models.catalog;

public class TicketPDF {

	private int year;
	private int qty;
	private String value;
	private String lotNumber;
	private String partNumber;
	private String description;
	private String loc;
	private String title;
	private String purchseOrder;
	private String receive;
	

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getValue() {
		if (value==null) {
		this.value="";	
		}
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getLotNumber() {
		if (lotNumber==null) {
			this.lotNumber="";	
			}
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public String getPartNumber() {
		if (partNumber==null) {
			this.partNumber="";	
			}
		return partNumber;
	}
	public void setPartNumber(String partNumber) {

		this.partNumber = partNumber;
	}
	public String getDescription() {
		if (description==null) {
			this.description="";	
			}
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLoc() {
		if (loc==null) {
			this.loc="";	
			}
		return loc;
	}
	public void setLoc(String loc) {
		this.loc = loc;
	}
	public String getTitle() {
		if (title==null) {
			this.title="";	
			}
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPurchseOrder() {
		if (purchseOrder==null) {
			this.purchseOrder="";	
			}
		return purchseOrder;
	}
	public void setPurchseOrder(String purchseOrder) {
		this.purchseOrder = purchseOrder;
	}
	public String getReceive() {
		if (receive==null) {
			this.receive="";	
			}
		return receive;
	}
	public void setReceive(String receive) {
	
		this.receive = receive;
	}
	
}
