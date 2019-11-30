package com.mx.base.models.catalog;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.mx.base.abstractions.CatalogModel;

@Entity
@Table(name="TC_PRODUCT")
public class Product extends CatalogModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotEmpty
	private String description;
	@Min(value=0)
	@Column(name="COST_USD")	
	private double costUSD;
	@Min(value=0)
	@Column(name="COST_MXN")
	private double costMXN;
	@Min(value=0)
	@Column(name="PRICE_USD")
	private double priceUSD;
	@Min(value=0)
	@Column(name="PRICE_MXN")
	private double priceMXN;	
	@Min(value=0)
	@Column(name="STOCK_MIN")
	private int stockMin;
	@Min(value=0)
	@Column(name="STOCK_MAX")
	private int stockMax;
	@Min(value=0)
	@Column(name="VALUE_EXPIRED")
	private int valueExpired;
	@Column(name="TIME_EXPIRED")
	private String timeExpired;
	@NotEmpty
	@Column(name="UNIT")
	private String unit;
	@NotEmpty
	@Column(name="BIN")
	private String bin;
	@NotEmpty
	@Column(name="AIRCRAFT")
	private String airCraft;
	@Column(name="ROTABLE_SERIALIZABLE")
	private boolean isRotableSerializable;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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
	public int getValueExpired() {
		return valueExpired;
	}
	public void setValueExpired(int valueExpired) {
		this.valueExpired = valueExpired;
	}
	public String getTimeExpired() {
		return timeExpired;
	}
	public void setTimeExpired(String timeExpired) {
		this.timeExpired = timeExpired;
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
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getBin() {
		return bin;
	}
	public void setBin(String bin) {
		this.bin = bin;
	}
	public String getAirCraft() {
		return airCraft;
	}
	public void setAirCraft(String airCraft) {
		this.airCraft = airCraft;
	}
	public boolean isRotableSerializable() {
		return isRotableSerializable;
	}
	public void setRotableSerializable(boolean isRotableSerializable) {
		this.isRotableSerializable = isRotableSerializable;
	}

	public boolean apply() {
		return isRotableSerializable;
	}
	
	@Override
	public String toString() {
		return "Product [description=" + description + ", costUSD=" + costUSD + ", costMXN=" + costMXN + ", priceUSD="
				+ priceUSD + ", priceMXN=" + priceMXN + ", stockMin=" + stockMin + ", stockMax=" + stockMax
				+ ", valueExpired=" + valueExpired + ", timeExpired=" + timeExpired + ", unit=" + unit + ", bin=" + bin
				+ ", airCraft=" + airCraft + ", isRotableSerializable=" + isRotableSerializable + "]";
	}

}
