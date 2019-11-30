package com.mx.base.models.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="VW_INVENTORY")
public class Inventory extends InventoryModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
}
