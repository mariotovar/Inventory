package com.mx.base.models.catalog;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the tc_user database table.
 * 
 */
@Entity
@Table(name="tc_user")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID_USER")
	private Long idUser;

	private String contrasena;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;

	private String status;

	private String user;

	@Column(name="USER_LOGIN")
	private String userLogin;

	//bi-directional many-to-one association to Profile
	@ManyToOne
	@JoinColumn(name="ID_PROFILE")
	private Profile tcProfile;

	public User() {
	}

	public Long getIdUser() {
		return this.idUser;
	}

	public void setIdUser(Long idUser) {
		this.idUser = idUser;
	}

	public String getContrasena() {
		return this.contrasena;
	}

	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getUserLogin() {
		return this.userLogin;
	}

	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}

	public Profile getTcProfile() {
		return this.tcProfile;
	}

	public void setTcProfile(Profile tcProfile) {
		this.tcProfile = tcProfile;
	}

}