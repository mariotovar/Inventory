package com.mx.base.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.base.models.catalog.Item;
import com.mx.base.repository.InventoryLotRepository;
import com.mx.base.services.InventoryLotService;

@Service
@Transactional(readOnly=false)
public class InventoryLotServiceImpl implements InventoryLotService{
	

	@Autowired
	InventoryLotRepository inventoryLotRepository;

	@Override
	public Item getAllInventoryLotByProduct(Long Id) {
		return inventoryLotRepository.getAllInventoryLotByProduct(Id);
	}
	
	
}
