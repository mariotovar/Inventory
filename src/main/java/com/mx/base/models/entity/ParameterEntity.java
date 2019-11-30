package com.mx.base.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TC_PARAMETER")
public class ParameterEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "_PK")
	private Long pk;

	@Column(name = "CODE")
	private String code;

	@Column(name = "VALUE")
	private String value;

	@Column(name = "USER")
	private String user;

	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;

//	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//	@JoinColumn(name = "PK_PARAMETER")
//	private List<ParameterValueEntity> parameterValues = new ArrayList<ParameterValueEntity>();

	public Long getPk() {
		return pk;
	}

	public void setPk(Long pk) {
		this.pk = pk;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

//	public List<ParameterValueEntity> getParameterValues() {
//		return parameterValues;
//	}
//
//	public void setParameterValues(List<ParameterValueEntity> parameterValues) {
//		this.parameterValues = parameterValues;
//	}

}
