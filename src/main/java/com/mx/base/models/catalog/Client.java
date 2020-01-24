package com.mx.base.models.catalog;

import java.io.Serializable;

import javax.persistence.Column;
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
	@Email
	@Column(name="EMAIL_CC1")
	private String emailCC1;
	
	
	@Email
	@Column(name="EMAIL_CC2")
	private String emailCC2;
	@Column(name="LOCAL_PHONE")
	private String localPhone;
	@Column(name="MOBILE_PHONE")
	private String mobilePhone;
//	@NotEmpty
//	@Column(name="TAX_ID")
//	private String taxID;
	@NotEmpty
	private String contact;
	
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
	public String getEmailCC1() {
		return emailCC1;
	}
	public void setEmailCC1(String emailCC1) {
		this.emailCC1 = emailCC1;
	}
	public String getEmailCC2() {
		return emailCC2;
	}
	public void setEmailCC2(String emailCC2) {
		this.emailCC2 = emailCC2;
	}
	public String getLocalPhone() {
		return localPhone;
	}
	public void setLocalPhone(String localPhone) {
		this.localPhone = localPhone;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
}
