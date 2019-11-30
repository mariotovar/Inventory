package com.mx.base.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.base.models.catalog.InventoryInput;
import com.mx.base.repository.InventoryInputRepository;
import com.mx.base.services.InventoryInputService;

@Service
@Transactional(readOnly=false)
public class InventoryInputServiceImpl implements InventoryInputService{
	
	@Autowired
	InventoryInputRepository inventoryRepository;
	
	@Override
	public void saveInventoryInput(InventoryInput inventoryInput){				
		inventoryRepository.saveInventoryInput(inventoryInput);		
	}

	@Override
	public InventoryInput getInventoryInputById(int year, Long pk) {
		return inventoryRepository.getInventoryInputById(year, pk);
	}

}
