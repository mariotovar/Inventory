package com.mx.base.models.catalog;

import java.util.concurrent.ThreadLocalRandom;

import com.mx.base.abstractions.CatalogModel;

public class Person extends CatalogModel{
	
	private String name;
	private String address;
	private String state;
	private String country;
	private String email;
	private String localPhone;
	private String mobilePhone;
	private String taxID;
	private String contact;

	public Person(){
		
		//this.pk=ThreadLocalRandom.current().nextInt(0, 1000);
		this.name=("name" + ThreadLocalRandom.current().nextInt(0, 1000));
		this.email=("email@" + ThreadLocalRandom.current().nextInt(0, 1000));
		this.mobilePhone="6666666";
			
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
	public String getTaxID() {
		return taxID;
	}
	public void setTaxID(String taxID) {
		this.taxID = taxID;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	
}
