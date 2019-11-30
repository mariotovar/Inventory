package com.mx.base.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.base.models.catalog.PurchaseItem;


public interface PurchaseItemRepository {
//extends CrudRepository<PurchaseItem, Long> {

	
	public PurchaseItem save(PurchaseItem purchase);
//	void save(PurchaseItem temp);

	
}
