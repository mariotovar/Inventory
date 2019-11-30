package com.mx.base.models.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mx.base.abstractions.ViewModel;
import com.mx.base.util.response.StatusOrder;

@Entity
@Table(name="VW_QUOTE")
public class QuoteOrder extends ViewModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="_PK")
	private Long pk;
	
	@Id
	@Column(name="_YEAR")
	private int year;	

	@Column(name="CLIENT")	
	private String client;	
	
	@Column(name="QUOTE_DATE")	
	private Date quoteDate;
	
	@Column(name="TOTAL_ORDER_USD")
	private double totalOrderUSD;
	
	@Column(name="TOTAL_ORDER_MXN")
	private double totalOrderMXN;
	
	@Column(name="STATUS")
	private int status;	
		
	@Column(name="_CURRENT")
	private String current;	
	
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
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public Date getQuoteDate() {
		return quoteDate;
	}
	public void setQuoteDate(Date quoteDate) {
		this.quoteDate = quoteDate;
	}
	public double getTotalOrderUSD() {
		return totalOrderUSD;
	}
	public void setTotalOrderUSD(double totalOrderUSD) {
		this.totalOrderUSD = totalOrderUSD;
	}
	public double getTotalOrderMXN() {
		return totalOrderMXN;
	}
	public void setTotalOrderMXN(double totalOrderMXN) {
		this.totalOrderMXN = totalOrderMXN;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
	public StatusOrder getStatusOrder() {
		StatusOrder statusOrder;
		statusOrder = StatusOrder.getStatus(this.getStatus());
		return statusOrder;	
	}
	public String getOrderNumber() {
		String orderNumber;		
		orderNumber = "QO" + (this.getYear() % 100) + '-' + String.format("%04d", this.getPk());
		return orderNumber;
	}

}
