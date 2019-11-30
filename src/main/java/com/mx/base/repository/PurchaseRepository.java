package com.mx.base.repository;

import java.util.List;

import com.mx.base.models.catalog.Purchase;
import com.mx.base.models.catalog.PurchaseExpensetEntity;
import com.mx.base.models.catalog.PurchaseItemLot;

public interface PurchaseRepository {

	public Purchase save(Purchase purchase);
	public Purchase getPurchaseById(Long pk, int year);

	public List<PurchaseExpensetEntity> getExpenseByPurchaseId(Long pk, int year);

	public void  saveExpenses(Long pk, int year, List<PurchaseExpensetEntity> lstExpenses);
	
	public void updateStatusPurchase(Long pk, int year, int status);
	public void saveReceive(Purchase purchase);
	public void saveReceive(List<PurchaseItemLot> purchase);
	long getMaxId(int year);
	
}
