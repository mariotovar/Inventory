package com.mx.base.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name = "TW_SALE")
@IdClass(OrderPKEntity.class)
public class SaleOrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "_PK")
	private long pk;

	@Column(name = "_YEAR")
	private int year;

	@Column(name = "PK_CLIENT")
	private Long pkClient;
	
	
	
	@Column(name = "TAX_IVA")
	private Double taxIVA;

	@Column(name = "NOTES")
	private String notes;	
	
	@Column(name = "USER")
	private String User;
	
	@Column(name = "STATUS")
	private int status;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;

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
	public Long getPkClient() {
		return pkClient;
	}
	public void setPkClient(Long pkClient) {
		this.pkClient = pkClient;
	}
	public Double getTaxIVA() {
		return taxIVA;
	}
	public void setTaxIVA(Double taxIVA) {
		this.taxIVA = taxIVA;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getUser() {
		return User;
	}
	public void setUser(String user) {
		User = user;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	@Override
	public String toString() {
		return "SaleOrderEntity [pk=" + pk + ", year=" + year + ", pkClient=" + pkClient  +", notes=" + notes + ", User=" + User
				+ ", status=" + status + ", creationDate=" + creationDate + "]";
	}

}
