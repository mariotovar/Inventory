package com.mx.base.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TW_QUOTE_ITEM")
public class QuoteOrderItemEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_PK")
	private Long pk;

	@Column(name = "_YEAR")
	private int year;	
	
	@Column(name = "PK_QUOTE")
	private long pkQuote;
	
	@Column(name = "PK_PRODUCT")
	private Long pkProduct;
	
	@Column(name = "VALUE")
	private String value;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PIECE_CONDITION")
	private String condition;
	
	@Column(name = "QTY")
	private int qty;

	@Column(name = "COST_USD")
	private double costUSD;

	@Column(name = "COST_MXN")
	private double costMXN;

	
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
	public long getPkQuote() {
		return pkQuote;
	}
	public void setPkQuote(long pkQuote) {
		this.pkQuote = pkQuote;
	}
	public Long getPkProduct() {
		return pkProduct;
	}
	public void setPkProduct(Long pkProduct) {
		this.pkProduct = pkProduct;
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
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
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
	
	@Override
	public String toString() {
		return "QuoteItemEntity [pk=" + pk + ", year=" + year + ", pkQuote=" + pkQuote + ", pkProduct=" + pkProduct
				+ ", value=" + value + ", description=" + description + ", condition=" + condition + ", qty=" + qty
				+ ", costUSD=" + costUSD + ", costMXN=" + costMXN + "]";
	}
	
}
