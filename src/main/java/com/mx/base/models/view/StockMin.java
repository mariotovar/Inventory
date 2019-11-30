package com.mx.base.models.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="VW_STOCK_MIN")
public class StockMin extends InventoryModel implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
}
