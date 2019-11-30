package com.mx.base.services;



import java.util.List;

import com.mx.base.models.catalog.Purchase;
import com.mx.base.models.catalog.PurchaseExpensetEntity;
import com.mx.base.models.catalog.PurchaseItemLot;
import com.mx.base.models.catalog.PurchaseOrder;

public interface PurchaseService {


	public Purchase savePurchase(Purchase purchase);
	public PurchaseOrder getPurchaseOrderById(Long id, int year);
	
	public Purchase getPurchaseById(Long id, int year);

	public List<PurchaseExpensetEntity> getExpenseByPurchaseId(Long pk, int year) ;

	public void saveExpenses(Long pk, int year, List<PurchaseExpensetEntity> lstExpenses) ;

	public void saveReceive(Purchase purchase) ;

	public void saveReceive(List<PurchaseItemLot> purchase) ;
	
	public void updateStatusPurchase(Long pk, int year, int status);
	
}
