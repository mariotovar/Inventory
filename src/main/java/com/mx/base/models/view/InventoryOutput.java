package com.mx.base.models.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mx.base.abstractions.ViewModel;

@Entity
@Table(name="VW_INVENTORY_OUTPUT")
public class InventoryOutput extends ViewModel implements Serializable {
	
	private static final long serialVersionUID = 1L;	
	
	@Id
	@Column(name="_PK")
	private Long pk;	
	@Column(name="_YEAR")
	private int year;
	@Column(name="OUTPUT_DATE")
	private Date outputDate;
	@Column(name="TOTAL_OUTPUT_USD")
	private double totalOrderUSD;
	@Column(name="TOTAL_OUTPUT_MXN")
	private double totalOrderMXN;


	public Long getPk() {
		return pk;
	}
	public void setPk(Long pk) {
		this.pk = pk;
	}
	public Date getOutputDate() {
		return outputDate;
	}
	public void setOutputDate(Date outputDate) {
		this.outputDate = outputDate;
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
	public String getOutputNumber() {
		String orderNumber;		
		orderNumber = "IO" + (this.getYear() % 100) + '-' + String.format("%04d", this.getPk());
		return orderNumber;
	}	
	
	
}
