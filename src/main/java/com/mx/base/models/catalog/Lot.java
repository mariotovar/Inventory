package com.mx.base.models.catalog;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "TW_LOT")
public class Lot implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "_PK")
	private long pk;

//	@Column(name = "LOT_NUMBER")
//	private Long lotNumber;

	@Column(name = "QTY")
	private int qty;
	
	@Column(name = "USER")
	private String user;

	@Column(name = "NOTES")
	private String note;

	@Column(name = "CREATION_DATE")
	private Date creationDate;

	@Transient
//	@ManyToOne
//	@JoinColumn(name = "_PK",insertable = false,updatable = false)
	private PurchaseItemLot purchaseItemLot;
	
	@Transient
	public PurchaseItemLot getPurchaseItemLot() {
		return purchaseItemLot;
	}
	@Transient
	public void setPurchaseItemLot(PurchaseItemLot purchaseItemLot) {
		this.purchaseItemLot = purchaseItemLot;
	}

	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

//	public Long getLotNumber() {
//		return lotNumber;
//	}
//
//	public void setLotNumber(Long lotNumber) {
//		this.lotNumber = lotNumber;
//	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

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
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}


}
