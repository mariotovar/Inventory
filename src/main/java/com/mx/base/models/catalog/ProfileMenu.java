package com.mx.base.models.catalog;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * The persistent class for the tc_profile_menu database table.
 * 
 */
@Entity
@Table(name="tc_profile_menu")
@NamedQuery(name="ProfileMenu.findAll", query="SELECT p FROM ProfileMenu p")
public class ProfileMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8920779988804898824L;

	@Id
	@Column(name="ID_PROFILE_MENU")
	private Long idProfileMenu;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATION_DATE")
	private Date creationDate;

	private String status;

	private String user;

	@ManyToOne
	@JoinColumn(name="ID_PROFILE")
	private Profile tcProfile;

	@ManyToOne
	@JoinColumn(name="ID_MENU")
	private Menu tcMenu;

	public ProfileMenu() {
		super();
	}

	public Long getIdProfileMenu() {
		return this.idProfileMenu;
	}

	public void setIdProfileMenu(Long idProfileMenu) {
		this.idProfileMenu = idProfileMenu;
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

	public Profile getTcProfile() {
		return this.tcProfile;
	}

	public void setTcProfile(Profile tcProfile) {
		this.tcProfile = tcProfile;
	}

	public Menu getTcMenu() {
		return this.tcMenu;
	}

	public void setTcMenu(Menu tcMenu) {
		this.tcMenu = tcMenu;
	}

}