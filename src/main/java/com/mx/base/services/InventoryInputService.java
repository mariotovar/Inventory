package com.mx.base.services;

import com.mx.base.models.catalog.InventoryInput;

public interface InventoryInputService {
	
	void saveInventoryInput(InventoryInput inventoryInput);
	InventoryInput getInventoryInputById(int year, Long pk);

}
