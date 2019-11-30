package com.mx.base.models.catalog;

import java.util.Date;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.mx.base.abstractions.CatalogModel;


public class SingleOne extends CatalogModel{

	@Min(1) 
	private int attrIntValue;
	@Min(1)
	private double attrDoubleValue;
	@NotEmpty
	private String attrStringValue;
	@AssertTrue
	private boolean attrBooleanValue;
	@NotNull @Past
	@DateTimeFormat(pattern="dd.MM.yyyy")
	private Date attrDateValue;
	
	public int getAttrIntValue() {
		return attrIntValue;
	}
	public void setAttrIntValue(int attrIntValue) {
		this.attrIntValue = attrIntValue;
	}
	public double getAttrDoubleValue() {
		return attrDoubleValue;
	}
	public void setAttrDoubleValue(double attrDoubleValue) {
		this.attrDoubleValue = attrDoubleValue;
	}
	public String getAttrStringValue() {
		return attrStringValue;
	}
	public void setAttrStringValue(String attrStringValue) {
		this.attrStringValue = attrStringValue;
	}
	public boolean isAttrBooleanValue() {
		return attrBooleanValue;
	}
	public void setAttrBooleanValue(boolean attrBooleanValue) {
		this.attrBooleanValue = attrBooleanValue;
	}
	public Date getAttrDateValue() {
		return attrDateValue;
	}
	public void setAttrDateValue(Date attrDateValue) {
		this.attrDateValue = attrDateValue;
	}
	@Override
	public String toString() {
		return "SingleOne [attrIntValue=" + attrIntValue + ", attrDoubleValue=" + attrDoubleValue + ", attrStringValue="
				+ attrStringValue + ", attrBooleanValue=" + attrBooleanValue + ", attrDateValue=" + attrDateValue + "]";
	}
	
}
