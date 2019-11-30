package com.mx.base.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TW_SALE_PAYMENT")
public class SaleOrderPaymentEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_PK")
	private Long pk;
	
	@Column(name = "PK_SALE")
	private Long pkSaleOrder;
	
	@Column(name = "_YEAR")
	private int year;		
	
	@Column(name = "AMOUNT_MXN")
	private Double amountMXN;
	
	@Column(name = "AMOUNT_USD")
	private Double amountUSD;
	
	@Column(name = "SALE_DATE")
	private Date saleDate;
	
	@Column(name = "USER")
	private String user;
	
	@Column(name = "NOTES")
	private String notes;

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public Long getPkSaleOrder() {
		return pkSaleOrder;
	}

	public void setPkSaleOrder(Long pkSaleOrder) {
		this.pkSaleOrder = pkSaleOrder;
	}

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}	
	
	public Double getAmountMXN() {
		return amountMXN;
	}

	public void setAmountMXN(Double amountMXN) {
		this.amountMXN = amountMXN;
	}

	public Double getAmountUSD() {
		return amountUSD;
	}

	public void setAmountUSD(Double amountUSD) {
		this.amountUSD = amountUSD;
	}

	public Date getSaleDate() {
		return saleDate;
	}

	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
