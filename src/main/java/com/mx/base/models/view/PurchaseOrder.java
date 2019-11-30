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
@Table(name="VW_PURCHASE")
public class PurchaseOrder extends ViewModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="_PK")
	private Long pk;
	@Id
	@Column(name="_YEAR")
	private int year;	
	@Column(name="PURCHASE_DATE")	
	private Date purchaseDate;
	@Column(name="SUPPLIER")	
	private String supplier;
	@Column(name="TOTAL_ORDER_USD")
	private double totalOrderUSD;
	@Column(name="TOTAL_ORDER_MXN")
	private double totalOrderMXN;
	@Column(name="TOTAL_PAYMENT_USD")
	private double totalPaymentUSD;
	@Column(name="TOTAL_PAYMENT_MXN")
	private double totalPaymentMXN;	
	@Column(name="ADMIN_EXPENSES_USD")
	private double adminExpensesUSD;
	@Column(name="ADMIN_EXPENSES_MXN")
	private double adminExpensesMXN;
	@Column(name="NUMBER_OF_INPUTS")
	private int numberOfInputs;
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
	public Date getPurchaseDate() {
		return purchaseDate;
	}
	public void setPurchaseDate(Date purchaseDate) {
		this.purchaseDate = purchaseDate;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
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
	public double getTotalPaymentUSD() {
		return totalPaymentUSD;
	}
	public void setTotalPaymentUSD(double totalPaymentUSD) {
		this.totalPaymentUSD = totalPaymentUSD;
	}
	public double getTotalPaymentMXN() {
		return totalPaymentMXN;
	}
	public void setTotalPaymentMXN(double totalPaymentMXN) {
		this.totalPaymentMXN = totalPaymentMXN;
	}
	public double getAdminExpensesUSD() {
		return adminExpensesUSD;
	}
	public void setAdminExpensesUSD(double adminExpensesUSD) {
		this.adminExpensesUSD = adminExpensesUSD;
	}
	public double getAdminExpensesMXN() {
		return adminExpensesMXN;
	}
	public void setAdminExpensesMXN(double adminExpensesMXN) {
		this.adminExpensesMXN = adminExpensesMXN;
	}
	public int getNumberOfInputs() {
		return numberOfInputs;
	}
	public void setNumberOfInputs(int numberOfInputs) {
		this.numberOfInputs = numberOfInputs;
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
		orderNumber = "PO" + (this.getYear() % 100) + '-' + String.format("%04d", this.getPk());
		return orderNumber;
	}
	@Override
	public String toString() {
		return "PurchaseOrder [pk=" + pk + ", purchaseDate=" + purchaseDate + ", supplier=" + supplier
				+ ", totalOrderUSD=" + totalOrderUSD + ", totalOrderMXN=" + totalOrderMXN + ", totalPaymentUSD="
				+ totalPaymentUSD + ", totalPaymentMXN=" + totalPaymentMXN + ", adminExpensesUSD=" + adminExpensesUSD
				+ ", adminExpensesMXN=" + adminExpensesMXN + ", numberOfInputs=" + numberOfInputs + ", status=" + status
				+ ", year=" + year + ", current=" + current + "]";
	}
	
}
