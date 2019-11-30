package com.mx.base.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.base.models.catalog.Payment;
import com.mx.base.models.catalog.SaleOrder;
import com.mx.base.repository.SaleOrderRepository;
import com.mx.base.services.SaleOrderService;

@Service
public class SaleOrderServiceImpl implements SaleOrderService{
	@Autowired
	private SaleOrderRepository saleOrderRepository;

	@Override
	public void saveSaleOrder(SaleOrder saleOrder) {
		saleOrderRepository.saveSaleOrder(saleOrder);
	}

	@Override
	public Object getSaleOrderById(long pk, int year) {
		return saleOrderRepository.getSaleOrderById(pk, year);
	}

	@Override
	public List<Payment> getAllPaymentById(long pk, int year) {
		return saleOrderRepository.getAllPaymentById(pk, year);
	}

	@Override
	public void saveAllPaymentById(Object object) {
		saleOrderRepository.saveAllPaymentById(object);
	}

	@Override
	public void updateStatus(long pk, int year, int status) {
		saleOrderRepository.updateStatus(pk, year, status);
	}

}
