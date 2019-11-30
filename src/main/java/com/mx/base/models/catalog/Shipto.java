package com.mx.base.models.catalog;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.mx.base.abstractions.CatalogModel;

@Entity
@Table(name="TC_SHIPTO")
public class Shipto extends CatalogModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String address;
	@NotEmpty
	private String zip;	
	@NotEmpty
	private String state;
	@NotEmpty
	private String country;
	@NotEmpty
	private String phone;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Shipto [address=" + address 
				+ ", zip=" + zip 
				+ ", pk=" + getPk()
				+ ", state=" + state + ", country="
				+ country + "]";
	}

}
