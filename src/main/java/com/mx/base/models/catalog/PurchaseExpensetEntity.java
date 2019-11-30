package com.mx.base.models.catalog;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TW_PURCHASE_EXPENSE")
public class PurchaseExpensetEntity implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_PK")
	private Long pk;
	
	@Column(name = "PK_PURCHASE")
	private Long pkPurchase;
	
	@Column(name = "_YEAR")
	private int year;	
	
	@Column(name = "AMOUNT_MXN")
	private Double amountMXN;
	
	@Column(name = "AMOUNT_USD")
	private Double amountUSD;	
	
	@Column(name = "EXPENSE_DATE")
	private Date expenseDate;

	@Column(name = "TYPE")
	private int type;

	@Column(name = "NOTES")
	private String notes;
	
	@Column(name = "USER")
	private String user;


	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}
	
	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public Double getAmountMXN() {
		return amountMXN;
	}

	public void setAmountMXN(Double amountMXN) {
		this.amountMXN = amountMXN;
	}

	public Double getAmountUSD() {
		return amountUSD;
	}

	public void setAmountUSD(Double amountUSD) {
		this.amountUSD = amountUSD;
	}

	public Date getExpenseDate() {
		return expenseDate;
	}

	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Long getPkPurchase() {
		return pkPurchase;
	}

	public void setPkPurchase(Long pkPurchase) {
		this.pkPurchase = pkPurchase;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
