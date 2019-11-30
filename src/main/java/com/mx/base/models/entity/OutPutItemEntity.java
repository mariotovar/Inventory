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
@Table(name = "TW_OUTPUT_ITEM")
public class OutPutItemEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_PK")
	private Long pk;
	
	@Column(name = "PK_OUTPUT")
	private Long pkOutPut;
	
	@Column(name = "_YEAR")
	private int year;	

	@Column(name = "PK_PRODUCT")
	private Long pkProduct;
	
	@Column(name = "PK_LOT_OUTPUT")
	private Long pkLotOutPut;
	
	@Column(name = "VALUE")
	private String value;

	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PIECE_CONDITION")
	private String condition;	

	@Column(name = "NOTES")
	private String notes;


	public Long getPk() {
		return pk;
	}
	public void setPk(Long pk) {
		this.pk = pk;
	}
	public Long getPkOutPut() {
		return pkOutPut;
	}
	public void setPkOutPut(Long pkOutPut) {
		this.pkOutPut = pkOutPut;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public Long getPkProduct() {
		return pkProduct;
	}
	public void setPkProduct(Long pkProduct) {
		this.pkProduct = pkProduct;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Long getPkLotOutPut() {
		return pkLotOutPut;
	}
	public void setPkLotOutPut(Long pkLotOutPut) {
		this.pkLotOutPut = pkLotOutPut;
	}
	
}
