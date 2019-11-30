package com.mx.base.models.view;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.mx.base.abstractions.ViewModel;

@MappedSuperclass
public class InventoryModel extends ViewModel{

	@Id
	@Column(name="_PK")	
	private long pk;
	@Column(name="_VALUE")
	private String value;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="STOCK_MIN")
	private int stockMin;
	@Column(name="STOCK_MAX")
	private int stockMax;
	@Column(name="COST_USD")
	private double costUSD;
	@Column(name="COST_MXN")
	private double costMXN;
	@Column(name="LIST_USD")
	private double listUSD;	
	@Column(name="LIST_MXN")
	private double listMXN;	
	@Column(name="INPUTS_PURCHASE")
	private int inputPurchase;
	@Column(name="OUTPUTS_PURCHASE")
	private int outputPurchase;
	@Column(name="INPUTS_INVENTORY")
	private int inputInventory;
	@Column(name="OUTPUTS_INVENTORY")
	private int outputInventory;
	@Column(name="_CURRENT")
	private String current;	
	
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
	public int getStockMin() {
		return stockMin;
	}
	public void setStockMin(int stockMin) {
		this.stockMin = stockMin;
	}
	public int getStockMax() {
		return stockMax;
	}
	public void setStockMax(int stockMax) {
		this.stockMax = stockMax;
	}
	public double getListUSD() {
		return listUSD;
	}
	public void setListUSD(double listUSD) {
		this.listUSD = listUSD;
	}
	public double getListMXN() {
		return listMXN;
	}
	public void setListMXN(double listMXN) {
		this.listMXN = listMXN;
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
	public int getInputPurchase() {
		return inputPurchase;
	}
	public void setInputPurchase(int inputPurchase) {
		this.inputPurchase = inputPurchase;
	}
	public int getOutputPurchase() {
		return outputPurchase;
	}
	public void setOutputPurchase(int outputPurchase) {
		this.outputPurchase = outputPurchase;
	}
	public int getInputInventory() {
		return inputInventory;
	}
	public void setInputInventory(int inputInventory) {
		this.inputInventory = inputInventory;
	}
	public int getOutputInventory() {
		return outputInventory;
	}
	public void setOutputInventory(int outputInventory) {
		this.outputInventory = outputInventory;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}

	public int getInputs() {
		int inputs = 0;
		inputs = (this.getInputPurchase() + this.getInputInventory());
		return inputs;
	}
	public int getOutputs() {
		int outputs = 0;
		outputs = (this.getOutputPurchase() + this.getOutputInventory());
		return outputs;
	}	
	public int getStock() {
		int stock = 0;
		stock = (this.getInputPurchase() + this.getInputInventory()) - (this.getOutputPurchase() + this.getOutputInventory());
		return stock;
	}

	
}
