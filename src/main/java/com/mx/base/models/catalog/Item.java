package com.mx.base.models.catalog;

import java.util.ArrayList;
import java.util.List;


import com.mx.base.util.response.PieceCondition;

public class Item {
	
	private Product product;
	private long pk;
	private String value;
	private String description;
	private PieceCondition condition;
	private int qty;
	private double costUSD;
	private double costMXN;
	private double priceUSD;
	private double priceMXN;
	private String notes;

	private double lstMXN;
	private double lstUSD;
	
	private String coreValue;
	private List<InputReceived> inputs;
	
	public Item(){		
		this.inputs = new ArrayList<InputReceived>();	
	}

	public long getPk() {
		return pk;
	}
	public void setPk(long pk) {
		this.pk = pk;
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
		this.priceMXN = priceMXN;
	}	
	public double getCostUSD() {
		return costUSD;
	}
	public void setCostUSD(double costUSD) {
		this.costUSD = costUSD;
	}
	public double getCostMXN() {
		return costMXN;
	}
	public void setCostMXN(double costMXN) {
		this.costMXN = costMXN;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getCoreValue() {
		return coreValue;
	}
	public void setCoreValue(String coreValue) {
		this.coreValue = coreValue;
	}

	public List<InputReceived> getInputs() {
		
		return inputs!=null?inputs:new ArrayList<InputReceived>();
	}
	public void setInputs(List<InputReceived> inputs) {
		this.inputs = inputs;
	}

	public double getSubtotalUSD(){
		return this.getPriceUSD() * this.getQty();
	}
	public double getSubtotalMXN(){
		return this.getPriceMXN() * this.getQty();
	}
	public double getSubCostUSD(){
		return this.getCostUSD() * this.getQty();
	}
	public double getSubCostMXN(){
		return this.getCostMXN() * this.getQty();
	}
	public int getOutputQty(){
		int outputsQty = 0;
		if(this.inputs!=null){
			for(InputReceived input: this.getInputs()){
				if(input.getOutputs()!=null){
					for(OutputSold output: input.getOutputs()){
						outputsQty += output.getQty(); 
					}
				}
			}
		}	
		return outputsQty;
	}

	public double getOutputCostUSD(){
		double outputsCost = 0;
		if(this.inputs!=null){
			for(InputReceived input: this.getInputs()){
				if(input.getOutputs()!=null){
					for(OutputSold output: input.getOutputs()){
						outputsCost += output.getCostUSD() * output.getQty(); 
					}
				}
			}
		}	
		return outputsCost;
	}
	
	public double getOutputCostMXN(){
		double outputsCost = 0;
		if(this.inputs!=null){
			for(InputReceived input: this.getInputs()){
				if(input.getOutputs()!=null){
					for(OutputSold output: input.getOutputs()){
						outputsCost += output.getCostMXN() * output.getQty(); 
					}
				}
			}
		}	
		return outputsCost;
	}	
	
	public double getUnitCostMXN(){
		double unitCost = 0;
		unitCost = this.getOutputCostMXN() / this.getOutputQty();
		return unitCost;
	}

	public double getUnitCostUSD(){
		double unitCost = 0;
		unitCost = this.getOutputCostUSD() / this.getOutputQty();
		return unitCost;
	}	
	
	public String getKeyCondition(){
		String keyCondition;
		keyCondition = this.getCondition().getKey();
		return keyCondition;
	}

	@Override
	public String toString() {
		return "Item [pk=" + pk + ", value=" + value + ", description=" + description + ", condition=" + condition
				+ ", qty=" + qty + ", costUSD=" + costUSD + ", costMXN=" + costMXN + ", priceUSD=" + priceUSD
				+ ", priceMXN=" + priceMXN + ", notes=" + notes + ", inputs=" + inputs + "]";
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getLstMXN() {
		return lstMXN;
	}

	public void setLstMXN(double lstMXN) {
		this.lstMXN = lstMXN;
	}

	public double getLstUSD() {
		return lstUSD;
	}

	public void setLstUSD(double lstUSD) {
		this.lstUSD = lstUSD;
	}

	
}
