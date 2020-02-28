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
@Table(name="VW_SALE")
public class SaleOrder extends ViewModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="_PK")
	private Long pk;
	@Id
	@Column(name="_YEAR")
	private int year;	
	@Column(name="SALE_DATE")	
	private Date saleDate;
	@Column(name="TOTAL_ORDER_USD")
	private double totalOrderUSD;
	@Column(name="TOTAL_ORDER_MXN")
	private double totalOrderMXN;

	@Column(name="TOTAL_PAYMENT_MXN")	
	private double totalPaymentMXN;
	@Column(name="TOTAL_PAYMENT_USD")	
	private double totalPaymentUSD;	
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
	public Date getSaleDate() {
		return saleDate;
	}
	public void setSaleDate(Date saleDate) {
		this.saleDate = saleDate;
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

	public double getTotalPaymentMXN() {
		return totalPaymentMXN;
	}
	public void setTotalPaymentMXN(double totalPaymentMXN) {
		this.totalPaymentMXN = totalPaymentMXN;
	}
	public double getTotalPaymentUSD() {
		return totalPaymentUSD;
	}
	public void setTotalPaymentUSD(double totalPaymentUSD) {
		this.totalPaymentUSD = totalPaymentUSD;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
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
		orderNumber = "SO" + (this.getYear() % 100) + '-' + String.format("%04d", this.getPk());
		return orderNumber;
	}

}
