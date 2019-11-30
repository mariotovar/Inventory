package com.mx.base.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TW_SALE_ITEM")
public class SaleOrderItemEntity implements Serializable {
	

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_PK")
	private Long pk;

	@Column(name = "_YEAR")
	private int year;	
	
	@Column(name = "PK_SALE")
	private Long pkSale;

	@Column(name = "PK_LOT_OUTPUT")
	private Long pkLotOutPut;
	
	@Column(name = "PK_PRODUCT")
	private Long pkProduct;
	
	@Column(name = "VALUE")
	private String value;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "PIECE_CONDITION")
	private String condition;
	
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
	public Long getPkSale() {
		return pkSale;
	}
	public void setPkSale(Long pkSale) {
		this.pkSale = pkSale;
	}
	public Long getPkLotOutPut() {
		return pkLotOutPut;
	}
	public void setPkLotOutPut(Long pkLotOutPut) {
		this.pkLotOutPut = pkLotOutPut;
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

}
