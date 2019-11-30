package com.mx.base.repository;

import java.util.List;

import com.mx.base.models.catalog.Purchase;
import com.mx.base.models.catalog.PurchaseOrder;
import com.mx.base.models.catalog.PurchasePaymentEntity;

public interface PurchasePaymentRepository {

	public List<PurchasePaymentEntity> getByPurchaseId(Long pk, int year);
	
	public void savePayments(Long pk, int year, List<PurchasePaymentEntity> lstPayments);
	
}
