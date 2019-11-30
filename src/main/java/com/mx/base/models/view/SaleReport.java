package com.mx.base.models.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mx.base.abstractions.ViewModel;

@Entity
@Table(name="VW_SALE_REPORT")
public class SaleReport extends ViewModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="_PK")
	private long pk;

	@Id
	@Column(name="_YEAR")
	private int year;
	
	@Column(name="_MONTH")
	private int month;

	@Column(name="_DATE")
	private Date date;
	
	@Column(name="AMOUNT_MXN")
	private double amountMXN;
	
	@Column(name="AMOUNT_USD")
	private double amountUSD;

	@Column(name="_USER")
	private String user;
	
	@Column(name="_TYPE_OP")
	private String typeOp;
	
	@Column(name="_CURRENT")
	private String current;
	
	@Column(name="_TYPE")
	private String type;

	public long getPk() {
		return pk;
	}
	public void setPk(long pk) {
		this.pk = pk;
	}

	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}

	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

	public double getAmountMXN() {
		return amountMXN;
	}
	public void setAmountMXN(double amountMXN) {
		this.amountMXN = amountMXN;
	}

	public double getAmountUSD() {
		return amountUSD;
	}
	public void setAmountUSD(double amountUSD) {
		this.amountUSD = amountUSD;
	}

	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
		
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}	
	public String getTypeOp() {
		return typeOp;
	}
	public void setTypeOp(String typeOp) {
		this.typeOp = typeOp;
	}
	
	@Override
	public String toString() {
		return "SaleReport [pk=" + pk + ", year=" + year + ", month=" + month + ", date=" + date + ", amountMXN="
				+ amountMXN + ", amountUSD=" + amountUSD + ", user=" + user + ", type=" + type + ", current=" + current
				+ "]";
	}	
	
	public String getReportNumber() {
		String orderNumber;		
		orderNumber = (this.getYear() % 100) + '-' + String.format("%04d", this.getPk());
		return orderNumber;
	}
	
	public String getReportPK() {
		String orderNumber;		
	    String saleType = this.getTypeOp();	
	    
        switch (saleType) 
        {
            case "SALE":  saleType = "SO";
                     break;
            case "INPUT":  saleType = "II";
                     break;
            case "OUTPUT":  saleType = "IO";
                     break;
            case "PURCHASE":  saleType = "PO";
                     break;
            case "SHIPPING":  saleType = "SO";
                     break;
            case "EXPENSE":  saleType = "PO";
                     break;
            default: saleType = "PO";
                     break;
        }	
		
		orderNumber = (saleType + this.getYear() % 100) + '-' + String.format("%04d", this.getPk());
		return orderNumber;
	}
	
}
