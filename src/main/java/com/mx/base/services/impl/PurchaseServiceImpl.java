package com.mx.base.services.impl;

import java.util.List;

//import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.base.component.PurchaseConverter;
import com.mx.base.models.catalog.Expense;
import com.mx.base.models.catalog.Item;
import com.mx.base.models.catalog.Payment;
import com.mx.base.models.catalog.Provider;
import com.mx.base.models.catalog.Purchase;
import com.mx.base.models.catalog.PurchaseExpensetEntity;
import com.mx.base.models.catalog.PurchaseItemLot;
import com.mx.base.models.catalog.PurchaseOrder;
import com.mx.base.models.catalog.PurchasePaymentEntity;
import com.mx.base.models.catalog.Shipto;
import com.mx.base.repository.PurchasePaymentRepository;
import com.mx.base.repository.PurchaseRepository;
import com.mx.base.services.CatalogService;
import com.mx.base.services.PurchaseService;
import com.mx.base.util.response.StatusOrder;

@Service
@Transactional(propagation=Propagation.REQUIRED)
public class PurchaseServiceImpl implements PurchaseService{

	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private PurchasePaymentRepository purchasePaymentRepository;
	
	@Autowired
	private PurchaseConverter purchaseConverter;
	
	@Autowired
	private PurchaseRepository purchaseRepository;
	
	@Override
	@Transactional("platformTransactionManager")
	public Purchase savePurchase(Purchase purchase) {		
		return purchaseRepository.save(purchase);	
	}

	@Override
	public Purchase getPurchaseById(Long id, int year) {
		return purchaseRepository.getPurchaseById(id, year);
	}
	
	@Override
	public PurchaseOrder getPurchaseOrderById(Long id, int year) {

		PurchaseOrder purchaseOrder=new PurchaseOrder();		
		Purchase purchase =purchaseRepository.getPurchaseById(id, year);
		purchaseOrder.setPurchaseDate(purchase.getCreationDate());
		purchaseOrder.setPkPurchase(purchase.getPk());
		purchaseOrder.setYear(purchase.getYear());
		purchaseOrder.setStatus(StatusOrder.getStatus(purchase.getStatus()));
		purchaseOrder.setNotes(purchase.getNotes());
		
		Provider provider = (Provider) catalogService.findRow(purchase.getPkProvider(), Provider.class);
		purchaseOrder.setProvider(provider);
		System.out.println("purchase.getPkShipto()"+purchase.getPkShipto());
		Shipto shipto = (Shipto) catalogService.findRow(purchase.getPkShipto(), Shipto.class);
		purchaseOrder.setShipto(shipto);
		
		List<Item> items = purchaseConverter.convertItemsFromEntity2Model(purchase);
		
		purchaseOrder.setItems(items);
		List<PurchasePaymentEntity> lstPayment = purchasePaymentRepository.getByPurchaseId(purchase.getPk(), purchase.getYear());

		List<Payment> payments = purchaseConverter.paymentFromEntity2Model(lstPayment);

		purchaseOrder.setPayments(payments);

		List<PurchaseExpensetEntity> lstExpenses = this.getExpenseByPurchaseId(purchase.getPk(), purchase.getYear());
		List<Expense> expenses = purchaseConverter.expensesFromEntity2Model(lstExpenses);
		purchaseOrder.setExpenses(expenses);
		 
		return purchaseOrder;
	}

	
	@Override
	public List<PurchaseExpensetEntity> getExpenseByPurchaseId(Long pk, int year) {
		return purchaseRepository.getExpenseByPurchaseId(pk, year);
	}

	@Override
	public void saveExpenses(Long pk, int year, List<PurchaseExpensetEntity> lstExpenses) {
		purchaseRepository.saveExpenses(pk, year, lstExpenses);
	}

	
	@Override
	public void updateStatusPurchase(Long pk, int year, int status) {
		purchaseRepository.updateStatusPurchase(pk, year, status);
	}

	@Override
	public void saveReceive(Purchase purchase) {
		// TODO Auto-generated method stub
		purchaseRepository.saveReceive(purchase);
	}

	@Override
	public void saveReceive(List<PurchaseItemLot> purchase) {
		purchaseRepository.saveReceive(purchase);
	}

}
