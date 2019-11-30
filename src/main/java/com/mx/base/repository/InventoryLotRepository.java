package com.mx.base.repository;

import java.util.List;

import com.mx.base.models.catalog.Item;

public interface InventoryLotRepository {

	public Item getAllInventoryLotByProduct(Long Id);
}
