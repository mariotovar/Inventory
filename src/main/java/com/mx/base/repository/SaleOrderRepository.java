package com.mx.base.repository;

import java.util.List;

import com.mx.base.models.catalog.Payment;
import com.mx.base.models.catalog.SaleOrder;

public interface SaleOrderRepository {

	public void saveSaleOrder(SaleOrder saleOrder);

	public Object getSaleOrderById(long pk, int year);
	
	List<Payment> getAllPaymentById(long pk, int year);

	public void saveAllPaymentById(Object object) ;
	
	public void updateStatus(long pk, int year, int status) ;

	long getMaxId(int year);

}
