package com.mx.base.models.catalog;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import com.mx.base.abstractions.CatalogModel;

@Entity
@Table(name="TC_CLIENT")
public class Client extends CatalogModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@NotEmpty
	private String address;
	@NotEmpty
	private String zip;	
	@NotEmpty
	private String state;
	@NotEmpty
	private String country;
	@NotEmpty @Email
	private String email;
	@NotEmpty
	private String rfc;
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	
}
