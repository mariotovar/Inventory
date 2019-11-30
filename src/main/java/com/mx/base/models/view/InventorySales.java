package com.mx.base.models.view;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.mx.base.abstractions.ViewModel;

@Entity
@Table(name="VW_OUTS")
public class InventorySales extends ViewModel implements Serializable {
	@Id
	@Column(name="_PK")
	private Long pk;
	
	@Column(name="_YEAR")
	private Long year;
	
	@Column(name="_TYPE")
	private String type;

	@Column(name="_DATE")
	private Date date;
	
	@Column(name="AMOUNT_MXN")
	private Double amount_mxn;
	
	@Column(name="AMOUNT_USD")
	private Double amount_usd;
	
	@Column(name="_USER")
	private String user;
	
	
	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public Long getYear() {
		return year;
	}

	public void setYear(Long year) {
		this.year = year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getAmount_mxn() {
		return amount_mxn;
	}

	public void setAmount_mxn(Double amount_mxn) {
		this.amount_mxn = amount_mxn;
	}

	public Double getAmount_usd() {
		return amount_usd;
	}

	public void setAmount_usd(Double amount_usd) {
		this.amount_usd = amount_usd;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	
	
	
	

}
