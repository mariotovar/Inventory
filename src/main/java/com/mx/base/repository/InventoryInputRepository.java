package com.mx.base.repository;

import com.mx.base.models.catalog.InventoryInput;

public interface InventoryInputRepository {
	
	void saveInventoryInput(InventoryInput inventoryInput);
	
	InventoryInput getInventoryInputById(int year, Long pk);
	
	long getMaxId(int year);

}
