package com.mx.base.models.catalog;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TW_PURCHASE_ITEM_LOT")
public class PurchaseItemLot implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_PK")
	private long pk;

	@Column(name = "PK_PURCHASE")
	private Long pkPurchase;
	
	@Column(name = "_YEAR")
	private int year;	

	@Column(name = "PK_LOT")
	private Long pkLot;
	
	@Column(name = "PK_PURCHASE_iTEM")
	private long pkItem;
	
	@Column(name = "SERIE")
	private String serie;
	
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	@Transient
	private int qtyForLot;

	@Transient
	private String noteForLot;

	@Transient
	private String userForLot;
	
	

	@Transient
	private PurchaseItem purchaseItem;

	@Transient
	private List<Lot> lstLot;
	
	@Transient
	public List<Lot> getLstLot() {
		
		return lstLot!=null?lstLot:new ArrayList<Lot>();
	}
	@Transient
	public void setLstLot(List<Lot> lstLot) {
		this.lstLot = lstLot;
	}

	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public PurchaseItem getPurchaseItem() {
		return purchaseItem;
	}

	public void setPurchaseItem(PurchaseItem purchaseItem) {
		this.purchaseItem = purchaseItem;
	}

	public Long getPkPurchase() {
		return pkPurchase;
	}

	public void setPkPurchase(Long pkPurchase) {
		this.pkPurchase = pkPurchase;
	}

	public int getYear() {
		return year;
	}
	
	public void setYear(int year) {
		this.year = year;
	}
	
	public Long getPkLot() {
		return pkLot;
	}

	public void setPkLot(Long pkLot) {
		this.pkLot = pkLot;
	}
	public long getPkItem() {
		return pkItem;
	}
	public void setPkItem(long pkItem) {
		this.pkItem = pkItem;
	}
	public void addLot(Lot item) {
		this.lstLot = this.lstLot != null ? this.lstLot : new ArrayList<Lot>();
		this.lstLot.add(item);
		item.setPurchaseItemLot(this);
	}
	public int getQtyForLot() {
		return qtyForLot;
	}
	public void setQtyForLot(int qtyForLot) {
		this.qtyForLot = qtyForLot;
	}
	public String getNoteForLot() {
		return noteForLot;
	}
	public void setNoteForLot(String noteForLot) {
		this.noteForLot = noteForLot;
	}
	public String getUserForLot() {
		return userForLot;
	}
	public void setUserForLot(String userForLot) {
		this.userForLot = userForLot;
	}
	
	@Override
	public String toString() {
		return "PurchaseItemLot [pk=" + pk + ", pkPurchase=" + pkPurchase + ", year=" + year + ", pkLot=" + pkLot
				+ ", pkItem=" + pkItem + ", serie=" + serie + ", qtyForLot=" + qtyForLot + ", noteForLot=" + noteForLot
				+ ", userForLot=" + userForLot + ", purchaseItem=" + purchaseItem + ", lstLot=" + lstLot + "]";
	}
	
}
