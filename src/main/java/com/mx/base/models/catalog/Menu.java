package com.mx.base.models.catalog;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the tc_menu database table.
 * 
 */
@Entity
@Table(name = "tc_menu")
public class Menu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1432166401544196159L;

	@Id
	@Column(name = "ID_MENU")
	private Long idMenu;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATION_DATE")
	private Date creationDate;

	private String description;

	@Column(name = "ID_PARENT_MENU")
	private Long idParentMenu;

	@Column(name = "`LABEL`")
	private String label;

	@Column(name = "SPRING_ANT_PATH")
	private String springAntPath;

	private String status;

	private String url;

	private String user;

	public Menu() {
		super();
	}

	public Long getIdMenu() {
		return this.idMenu;
	}

	public void setIdMenu(Long idMenu) {
		this.idMenu = idMenu;
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

	public Long getIdParentMenu() {
		return this.idParentMenu;
	}

	public void setIdParentMenu(Long idParentMenu) {
		this.idParentMenu = idParentMenu;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getSpringAntPath() {
		return this.springAntPath;
	}

	public void setSpringAntPath(String springAntPath) {
		this.springAntPath = springAntPath;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}