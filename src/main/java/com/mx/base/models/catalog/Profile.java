package com.mx.base.models.catalog;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tc_profile database table.
 * 
 */
@Entity
@Table(name = "tc_profile")
public class Profile implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2197477450516055408L;

	@Id
	@Column(name = "ID_PROFILE")
	private Long idProfile;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_DATE")
	private Date creationDate;

	private String description;

	@Column(name = "SPRING_ROLE")
	private String springRole;

	private String status;

	private String user;

	// bi-directional many-to-one association to ProfileMenu
	@OneToMany(mappedBy = "tcProfile", fetch=FetchType.EAGER)
	private List<ProfileMenu> tcProfileMenus;

	public Profile() {
		super();
	}

	public Long getIdProfile() {
		return this.idProfile;
	}

	public void setIdProfile(Long idProfile) {
		this.idProfile = idProfile;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSpringRole() {
		return this.springRole;
	}

	public void setSpringRole(String springRole) {
		this.springRole = springRole;
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

	public List<ProfileMenu> getTcProfileMenus() {
		return this.tcProfileMenus;
	}

	public void setTcProfileMenus(List<ProfileMenu> tcProfileMenus) {
		this.tcProfileMenus = tcProfileMenus;
	}

	public ProfileMenu addTcProfileMenus(ProfileMenu tcProfileMenus) {
		getTcProfileMenus().add(tcProfileMenus);
		tcProfileMenus.setTcProfile(this);

		return tcProfileMenus;
	}

	public ProfileMenu removeTcProfileMenus(ProfileMenu tcProfileMenus) {
		getTcProfileMenus().remove(tcProfileMenus);
		tcProfileMenus.setTcProfile(null);

		return tcProfileMenus;
	}

}