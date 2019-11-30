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
@Table(name = "TW_LOT_OUTPUT")
public class LotOutPutEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_PK")
	private Long pk;

	@Column(name = "USER")
	private String user;

	@Column(name = "QTY")
	private int qty;

	@Column(name = "CREATION_DATE")
	private Date creationDate;

	@Column(name = "PK_LOT")
	private Long pkLot;

	@Column(name = "COST_USD")
	private Double costUSD;
	
	@Column(name = "COST_MXN")
	private Double costMXN;

	
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Long getPkLot() {
		return pkLot;
	}

	public void setPkLot(Long pkLot) {
		this.pkLot = pkLot;
	}

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	public Double getCostUSD() {
		return costUSD;
	}

	public void setCostUSD(Double costUSD) {
		this.costUSD = costUSD;
	}

	public Double getCostMXN() {
		return costMXN;
	}

	public void setCostMXN(Double costMXN) {
		this.costMXN = costMXN;
	}

	
}
