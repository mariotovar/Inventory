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
@Table(name = "TW_PURCHASE_ITEM")
public class PurchaseItem implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_PK")
	private long pk;

	@Column(name = "QTY")
	private int qty;

	@Column(name = "VALUE")
	private String value;

	@Column(name = "DESCRIPTION")
	private String descripcion;

	@Column(name = "PIECE_CONDITION")
	private String condition;	
	
	@Column(name = "PRICE_USD")
	private Double price_usd;

	@Column(name = "PRICE_MXN")
	private Double price_mxn;
	
	@Column(name = "PK_PRODUCT")
	private long pkProduct;

	@Column(name = "PK_PURCHASE")
	private Long pkPurchase;
	
	@Column(name = "_YEAR")
	private int year;		
	
//	@ManyToOne
//	@JoinColumn(name = "PK_PURCHASE")
	@Transient
	private Purchase purchase;
	
	@Transient
	private Product product;
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}

	//	@OneToMany(mappedBy = "purchaseItem", cascade = CascadeType.ALL,fetch=FetchType.EAGER)
	@Transient
	private List<PurchaseItemLot> purchaseItemLot;
	@Transient
	public List<PurchaseItemLot> getPurchaseItem() {
		
		return purchaseItemLot!=null?purchaseItemLot:new ArrayList<PurchaseItemLot>();
	}

	public void setPurchaseItemLot(List<PurchaseItemLot> purchaseItemLot) {
		for (PurchaseItemLot item:purchaseItemLot) {
			item.setPurchaseItem(this);
		}
		this.purchaseItemLot = purchaseItemLot;
	}
	
	public long getPk() {
		return pk;
	}

	public void setPk(long pk) {
		this.pk = pk;
	}

	public long getPkProduct() {
		return pkProduct;
	}

	public void setPkProduct(long pkProduct) {
		this.pkProduct = pkProduct;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Double getPrice_usd() {
		return price_usd;
	}

	public void setPrice_usd(Double price_usd) {
		this.price_usd = price_usd;
	}

	public Double getPrice_mxn() {
		return price_mxn;
	}

	public void setPrice_mxn(Double price_mxn) {
		this.price_mxn = price_mxn;
	}

	public Purchase getPurchase() {
		return purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
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

	public List<PurchaseItemLot> getPurchaseItemLot() {
		return purchaseItemLot;
	}

	public void addItemLot(PurchaseItemLot item) {
		this.purchaseItemLot = this.purchaseItemLot != null ? this.purchaseItemLot : new ArrayList<PurchaseItemLot>();
		this.purchaseItemLot.add(item);
		item.setPurchaseItem(this);
	}
	
}
