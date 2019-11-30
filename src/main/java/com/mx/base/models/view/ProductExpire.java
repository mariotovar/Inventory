package com.mx.base.models.view;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="VW_PRODUCT_EXPIRED")
public class ProductExpire extends InventoryLotModel implements Serializable {
	
	private static final long serialVersionUID = 1L;

}
