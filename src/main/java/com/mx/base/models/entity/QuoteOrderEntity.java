package com.mx.base.models.entity;

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

@Entity
@Table(name = "TW_QUOTE")
@IdClass(OrderPKEntity.class)
public class QuoteOrderEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "_PK")
	private long pk;

	@Id
	@Column(name = "_YEAR")
	private int year;

	@Column(name = "PK_CLIENT")
	private Long pkClient;
	
	@Column(name = "NOTES")
	private String notes;	
	
	@Column(name = "USER")
	private String user;
	
	@Column(name = "STATUS")
	private int status;

	@Column(name = "FACTOR_IVA")
	private double factor_iva;
	


	@Column(name = "CREATION_DATE")
	private Date creationDate;
	
	@Transient
	private List<QuoteOrderItemEntity> items;
	
	public QuoteOrderEntity(){
		this.items = new ArrayList<QuoteOrderItemEntity>();
	}

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
	public List<QuoteOrderItemEntity> getItems() {
		return items;
	}
	public void setItems(List<QuoteOrderItemEntity> items) {
		this.items = items;
	}
	
	public double getFactor_iva() {
		return factor_iva;
	}

	public void setFactor_iva(double factor_iva) {
		this.factor_iva = factor_iva;
	}
	@Override
	public String toString() {
		return "QuoteEntity [pk=" + pk + ", year=" + year + ", pkClient=" + pkClient + ", notes=" + notes + ", user="
				+ user + ", status=" + status + ", creationDate=" + creationDate + "]";
	}
	
	
}
