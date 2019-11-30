package com.mx.base.models.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mx.base.abstractions.ViewModel;

@Entity
@Table(name="VW_INVENTORY_INPUT")
public class InventoryInput extends ViewModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="_PK")
	private Long pk;
	@Column(name="INPUT_DATE")
	private Date inputDate;
	@Column(name="TOTAL_INPUT_USD")
	private double totalOrderUSD;
	@Column(name="TOTAL_INPUT_MXN")
	private double totalOrderMXN;
	@Column(name="YEAR")
	private int year;	
	
	public Long getPk() {
		return pk;
	}
	public void setPk(Long pk) {
		this.pk = pk;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
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
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public String getInputNumber() {
		String orderNumber;		
		orderNumber = "II" + (this.getYear() % 100) + '-' + String.format("%04d", this.getPk());
		return orderNumber;
	}

	
	
}
