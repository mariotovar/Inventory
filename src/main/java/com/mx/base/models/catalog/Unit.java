package com.mx.base.models.catalog;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.mx.base.abstractions.CatalogModel;

@Entity
@Table(name="TC_UNIT")
public class Unit extends CatalogModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	
}
