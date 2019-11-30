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
@Table(name = "TC_PARAMETER_VALUES")
public class ParameterValueEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_PK")
	private Long pk;
	
	@Column(name = "_VALUE")
	private String value;
	
	@Column(name = "PK_PARAMETER")
	private Long pkParameter;
	
	@Column(name = "DESCRIPTION")
	private String description;
	
	@Column(name = "USER")
	private String user;
	
	@Column(name = "STATUS")
	private String status;
	
	@Column(name = "CREATION_DATE")
	private Date creationDate;

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getPkParameter() {
		return pkParameter;
	}

	public void setPkParameter(Long pkParameter) {
		this.pkParameter = pkParameter;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	

}
