package com.mx.base.models.catalog;

import java.util.Date;

import com.mx.base.util.response.PieceCondition;

public class InventoryInputRow {
	
	private long pk;
	private long lot;
	private String value;
	private String description;	
	private PieceCondition condition;	
	private int qty;
	private double priceUSD;
	private double priceMXN;		
	private String notes;
	
    private String ProductBin;

	private Date creationDate;
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public long getPk() {
		return pk;
	}
	public void setPk(long pk) {
		this.pk = pk;
	}	
	public long getLot() {
		return lot;
	}
	public void setLot(long lot) {
		this.lot = lot;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	public PieceCondition getCondition() {
		return condition;
	}
	public void setCondition(PieceCondition condition) {
		this.condition = condition;
	}	
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public double getPriceUSD() {
		return priceUSD;
	}
	public void setPriceUSD(double priceUSD) {
		this.priceUSD = priceUSD;
	}
	public double getPriceMXN() {
		return priceMXN;
	}
	public void setPriceMXN(double priceMXN) {
		this.priceMXN=priceMXN;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public double getSubtotalUSD(){
		return this.getPriceUSD() * this.getQty();
	}
	
	public double getSubtotalMXN(){
		return this.getPriceMXN() * this.getQty();
	}
	
	public String getLotNumber() {
		String lotNumber;		
		lotNumber = "L-" + String.format("%06d", this.getLot());
		return lotNumber;
	}
	
	public String getKeyCondition(){
		String keyCondition;
		keyCondition = this.getCondition().getKey();
		return keyCondition;
	}	

	@Override
	public String toString() {
		return "InventoryInputRow [pk=" + pk + ", lot=" + lot + ", value=" + value + ", description=" + description
				+ ", qty=" + qty + ", priceUSD=" + priceUSD + ", priceMXN=" + priceMXN + ", notes=" + notes + "]";
	}
	public String getProductBin() {
		return ProductBin;
	}
	public void setProductBin(String productBin) {
		ProductBin = productBin;
	}

}
