package com.mx.base.models.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mx.base.models.entity.OrderPKEntity;


@Entity
@Table(name = "TW_PURCHASE")
@IdClass(OrderPKEntity.class)
public class Purchase implements Serializable {


	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "_PK")
	private long pk;

	@Id
	@Column(name = "_YEAR")
	private int year;
	
	@Column(name = "FACTOR_CONVERSION")
	private double factorConversion;

	
//	@OneToMany(mappedBy = "purchase", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@Transient
	private List<PurchaseItem> purchaseItem;

	//	@Transient
	public List<PurchaseItem> getPurchaseItem() {
		return purchaseItem!=null?purchaseItem:new ArrayList<PurchaseItem>();
	}

	//	@Transient
	public void setPurchaseItem(List<PurchaseItem> purchaseItem) {
		for (PurchaseItem item:purchaseItem) {
			item.setPurchase(this);
		}
		this.purchaseItem = purchaseItem;
	}
	public void addItem(PurchaseItem item) {
		this.purchaseItem = this.purchaseItem != null ? this.purchaseItem : new ArrayList<PurchaseItem>();
		this.purchaseItem.add(item);
		item.setPurchase(this);
	}
	
	@Column(name = "PK_PROVIDER")
	private long pkProvider;

	@Column(name = "USER")
	private String user;
	@Column(name = "STATUS")
	private int status;

	@Column(name = "CREATION_DATE")
	protected Date creationDate;

	@Column(name = "PK_SHIPTO")
	private long pkShipto;
	
	@Column(name = "NOTE")
	private String notes;
	
	
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
	public long getPkProvider() {
		return pkProvider;
	}
	public void setPkProvider(long pkProvider) {
		this.pkProvider = pkProvider;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getStatus() {
		return this.status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}	
	public long getPkShipto() {
		return pkShipto;
	}
	public void setPkShipto(long pkShipto) {
		this.pkShipto = pkShipto;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}

	public double getFactorConversion() {
		return factorConversion;
	}

	public void setFactorConversion(double factorConversion) {
		this.factorConversion = factorConversion;
	}

	@Override
	public String toString() {
		return "Purchase [pk=" + pk + ", year=" + year + ", purchaseItem=" + purchaseItem + ", pkProvider=" + pkProvider
				+ ", user=" + user + ", status=" + status + ", creationDate=" + creationDate + ", pkShipto=" + pkShipto
				+ ", notes=" + notes + "]";
	}
	
}
