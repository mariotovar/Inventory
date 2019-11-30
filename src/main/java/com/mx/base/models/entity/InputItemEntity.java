package com.mx.base.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TW_INPUT_ITEM")
public class InputItemEntity implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_PK")
	private Long pk;

	@Column(name = "PK_INPUT")
	private Long pkInput;
	
	@Column(name = "_YEAR")
	private int year;	

	@Column(name = "PK_LOT")
	private Long pkLot;

	@Column(name = "PK_PRODUCT")
	private Long pkProduct;
	
	@Column(name = "VALUE")
	private String value;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "PIECE_CONDITION")
	private String condition;
	
	@Column(name = "PRICE_USD")
	private Double priceUSD;

	@Column(name = "PRICE_MXN")
	private Double priceMXN;

	public Long getPk() {
		return pk;
	}
	public void setPk(Long pk) {
		this.pk = pk;
	}
	public Long getPkInput() {
		return pkInput;
	}
	public void setPkInput(Long pkInput) {
		this.pkInput = pkInput;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Long getPkLot() {
		return pkLot;
	}
	public void setPkLot(Long pkLot) {
		this.pkLot = pkLot;
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
	public Double getPriceUSD() {
		return priceUSD;
	}
	public void setPriceUSD(Double priceUSD) {
		this.priceUSD = priceUSD;
	}
	public Double getPriceMXN() {
		return priceMXN;
	}
	public void setPriceMXN(Double priceMXN) {
		this.priceMXN = priceMXN;
	}

}
