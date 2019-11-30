package com.mx.base.services;

import java.util.List;

import com.mx.base.models.catalog.Payment;
import com.mx.base.models.catalog.SaleOrder;

public interface SaleOrderService {
	
	public void saveSaleOrder(SaleOrder saleOrder);

	public Object getSaleOrderById(long pk, int year);

	public List<Payment> getAllPaymentById(long pk, int year);

	public void saveAllPaymentById(Object object);
	
	public void updateStatus(long pk, int year, int status);

}
