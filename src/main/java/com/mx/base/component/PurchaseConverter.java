package com.mx.base.component;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.base.models.catalog.Expense;
import com.mx.base.models.catalog.InputReceived;
import com.mx.base.models.catalog.Item;
import com.mx.base.models.catalog.Lot;
import com.mx.base.models.catalog.Payment;
import com.mx.base.models.catalog.Product;
import com.mx.base.models.catalog.Purchase;
import com.mx.base.models.catalog.PurchaseExpensetEntity;
import com.mx.base.models.catalog.PurchaseItem;
import com.mx.base.models.catalog.PurchaseItemLot;
import com.mx.base.models.catalog.PurchaseOrder;
import com.mx.base.models.catalog.PurchasePaymentEntity;
import com.mx.base.services.CatalogService;
import com.mx.base.util.functions.ParameterCurrency;
import com.mx.base.util.response.PieceCondition;
import com.mx.base.util.response.StatusOrder;

@Component("contactConverter")
public class PurchaseConverter {

	@Autowired
	private ParameterCurrency currency;
	
	public List<Item> convertItemsFromEntity2ModelForReceive(Purchase purchase) {
		List<Item> items = new ArrayList<Item>();
		for (PurchaseItem itemTmp : purchase.getPurchaseItem()) {
			Item item = new Item();

			item.setProduct(itemTmp.getProduct());
			item.setPk(itemTmp.getPk());
			item.setPriceMXN(itemTmp.getPrice_mxn());
			System.out.println("item :" + item);
			item.setPriceUSD(itemTmp.getPrice_usd());
			item.setQty(itemTmp.getQty());
			item.setValue(itemTmp.getValue());
			item.setDescription(itemTmp.getDescripcion());
			item.setCondition(PieceCondition.valueOf(itemTmp.getCondition()));
			int total = 0;
			for (PurchaseItemLot itemTmpLot : itemTmp.getPurchaseItem()) {
				for (Lot lot : itemTmpLot.getLstLot()) {
					InputReceived inputR = new InputReceived();
					inputR.setLot(itemTmpLot.getPkLot());
					total = total + lot.getQty();
					inputR.setReceivedQty(lot.getQty());
					inputR.setReceiveDate(lot.getCreationDate());
					inputR.setNotes(lot.getNote());
					inputR.setSerial(itemTmpLot.getSerie());
					System.out.println("lot :" + item);
					item.getInputs().add(inputR);
				}
			}
			//System.out.println("item.getProduct().apply()" + item.getProduct().apply());
			if (item.getProduct()!=null&&!item.getProduct().apply()) {
				if (item.getInputs().size() == 0) {
					InputReceived inputR = new InputReceived();
					inputR.setReceivedQty(item.getQty());
					inputR.setSerial("N/A");
					item.getInputs().add(inputR);
				} else {
					if (total < itemTmp.getQty()) {
						InputReceived inputR = new InputReceived();
						inputR.setReceivedQty(itemTmp.getQty() - total);
						inputR.setSerial("N/A");
						item.getInputs().add(inputR);
					}
				}
			} else {
				if (item.getInputs().size() == 0) {
					for (int i = itemTmp.getQty(); i > 0; i--) {
						System.out.println("i" + i);
						InputReceived inputR = new InputReceived();
						inputR.setReceivedQty(1);

						item.getInputs().add(inputR);
					}
				}else {
					for (int i = itemTmp.getQty()-total; i > 0; i--) {
						System.out.println("i" + i);
						InputReceived inputR = new InputReceived();
						inputR.setReceivedQty(1);

						item.getInputs().add(inputR);
					}
				}
			}
			items.add(item);
		}
		return items;
	}

	public List<Item> convertItemsFromEntity2Model(Purchase purchase) {
		List<Item> items = new ArrayList<Item>();
		for (PurchaseItem itemTmp : purchase.getPurchaseItem()) {
			Item item = new Item();
			item.setProduct(itemTmp.getProduct());
			item.setPk(itemTmp.getPk());
			item.setPriceMXN(itemTmp.getPrice_mxn());
			// System.out.println("item :" + item);
			item.setPriceUSD(itemTmp.getPrice_usd());
			item.setQty(itemTmp.getQty());
		
			item.setDescription(itemTmp.getDescripcion());
			item.setCondition(PieceCondition.valueOf(itemTmp.getCondition()));
			item.setValue(itemTmp.getValue());
			item.setCoreValue(itemTmp.getCoreValue());
			int total = 0;
			for (PurchaseItemLot itemTmpLot : itemTmp.getPurchaseItem()) {

				for (Lot lot : itemTmpLot.getLstLot()) {

					InputReceived inputR = new InputReceived();
					inputR.setLot(itemTmpLot.getPkLot());
					inputR.setSerial(itemTmpLot.getSerie());
					// inputR.setRealQty(itemTmp.getQty());
					total = total + lot.getQty();
					inputR.setReceiveDate(lot.getCreationDate());
					inputR.setReceivedQty(lot.getQty());
					inputR.setNotes(lot.getNote());
					System.out.println("lot :" + item);
					item.getInputs().add(inputR);
				}

			}

//			if (item.getInputs().size() == 0) {
//				InputReceived inputR = new InputReceived();
//				item.getInputs().add(inputR);
//			} else {
//				System.out.println("total :" + total);
//				System.out.println("getQty :" + itemTmp.getQty());
//				if (total < itemTmp.getQty()) {
//					InputReceived inputR = new InputReceived();
//					inputR.setReceivedQty(itemTmp.getQty() - total);
//					item.getInputs().add(inputR);
//				}
//			}
			items.add(item);
		}
		return items;
	}

	public Purchase convertModel2Entity(PurchaseOrder purchaseOrder) {
		Purchase purchaseBD = new Purchase();
		// se agregar para update o crear order de compra
		purchaseBD.setPk(purchaseOrder.getPkPurchase() != null ? purchaseOrder.getPkPurchase() : 0L);
		purchaseBD.setYear(purchaseOrder.getYear());
		purchaseBD.setPkProvider(purchaseOrder.getProvider().getPk());
		purchaseBD.setStatus(StatusOrder.NEW.getpk());
		purchaseBD.setNotes(purchaseOrder.getNotes());
		purchaseBD.setPkShipto(purchaseOrder.getShipto().getPk());
		for (Item item : purchaseOrder.getItems()) {
			PurchaseItem temp = new PurchaseItem();
			temp.setPkProduct(item.getPk());
			temp.setDescripcion(item.getDescription());
			temp.setCondition(item.getCondition().name());
			temp.setPrice_mxn(item.getPriceMXN());
			temp.setPrice_usd(item.getPriceUSD());
			temp.setValue(item.getValue());
			temp.setQty(item.getQty());
			temp.setCoreValue(item.getCoreValue());
			purchaseBD.addItem(temp);
		}
		return purchaseBD;

	}

	public List<PurchaseItemLot> convertModel2EntityForReceive(PurchaseOrder purchaseOrder, String user) {
		Purchase purchaseBD = new Purchase();
		List<PurchaseItemLot> lst = new ArrayList<PurchaseItemLot>();
		purchaseBD.setPkProvider(purchaseOrder.getProvider().getPk());

		for (Item item : purchaseOrder.getItems()) {
			for (InputReceived input : item.getInputs()) {
				if (input.getLot() == null || input.getLot() == 0 && input.getReceivedQty() > 0) {
					PurchaseItemLot piLot = new PurchaseItemLot();
					piLot.setPkItem(item.getPk());
					piLot.setPkPurchase(purchaseOrder.getPkPurchase());
					piLot.setYear(purchaseOrder.getYear());
					piLot.setQtyForLot(input.getReceivedQty());
					piLot.setSerie(input.getSerial());
					piLot.setNoteForLot(input.getNotes());
					piLot.setUserForLot(user);
					lst.add(piLot);
				}
			}
		}
		return lst;

	}

	public List<Payment> paymentFromEntity2Model(List<PurchasePaymentEntity> lstPayment) {
		List<Payment> payments = new ArrayList<Payment>();
		for (PurchasePaymentEntity itemPayment : lstPayment) {
			Payment payment = new Payment();
			payment.setPk(itemPayment.getPk());
			payment.setAmountMXN(itemPayment.getAmountMXN());
			payment.setAmountUSD(itemPayment.getAmountUSD());
			payment.setNotes(itemPayment.getNotes());
			payment.setPaymentDate(itemPayment.getPaymentDate());
			payments.add(payment);
		}
		return payments;
	}

	public List<PurchasePaymentEntity> paymentFromModel2Entity(PurchaseOrder purchaseOrder, String User) {

		List<PurchasePaymentEntity> lstPaymentsBD = new ArrayList<PurchasePaymentEntity>();
		for (Payment payment : purchaseOrder.getPayments()) {
			PurchasePaymentEntity paymentBD = new PurchasePaymentEntity();
			paymentBD.setPk(payment.getPk());
			paymentBD.setYear(payment.getYear());
			paymentBD.setPkPurchase(purchaseOrder.getPkPurchase());
			paymentBD.setUser(User);
			paymentBD.setAmountUSD(payment.getAmountUSD() );
			paymentBD.setAmountMXN(payment.getAmountUSD() * currency.getFactorConvertion());			
			paymentBD.setNotes(payment.getNotes());
			paymentBD.setPaymentDate(payment.getPaymentDate());
			System.out.println("payment :" + paymentBD);
			lstPaymentsBD.add(paymentBD);
		}
		return lstPaymentsBD;

	}

	public List<Expense> expensesFromEntity2Model(List<PurchaseExpensetEntity> lstExpenses) {
		List<Expense> expenses = new ArrayList<Expense>();
		for (PurchaseExpensetEntity entity : lstExpenses) {
			Expense expenseView = new Expense();
			expenseView.setType(entity.getType());
			expenseView.setAmountMXN(entity.getAmountMXN());
			expenseView.setAmountUSD(entity.getAmountUSD());
			expenseView.setNotes(entity.getNotes());
			expenseView.setExpenseDate(entity.getExpenseDate());
			expenses.add(expenseView);
		}
		return expenses;
	}

	public List<PurchaseExpensetEntity> expensesFromModel2Entity(PurchaseOrder purchaseOrder, String User) {

		List<PurchaseExpensetEntity> lstExpenses = new ArrayList<PurchaseExpensetEntity>();
		for (Expense expense : purchaseOrder.getExpenses()) {
			System.out.println("expense :" + expense);
			PurchaseExpensetEntity entity = new PurchaseExpensetEntity();
			entity.setAmountMXN(expense.getAmountMXN());
			entity.setAmountUSD(expense.getAmountUSD());
			entity.setNotes(expense.getNotes());
			entity.setYear(purchaseOrder.getYear());
			entity.setPkPurchase(purchaseOrder.getPkPurchase());
			entity.setType(expense.getType());
			entity.setUser(User);
			lstExpenses.add(entity);
		}
		return lstExpenses;
	}
//	List<PurchasePaymentEntity> lstPaymentsBD=new ArrayList<PurchasePaymentEntity>();
//	public ContactModel convertContact2ContactModel(Optional<Contact> contactOpt) {
//		Contact contact =contactOpt.get();
//		ContactModel contactModel=new ContactModel();
//		contactModel.setCity(contact.getCity());
//		contactModel.setId(contact.getId());
//		contactModel.setFirstname(contact.getFirstname());
//		contactModel.setLastname(contact.getLastname());
//		contactModel.setTelephone(contact.getTelephone());
//		return contactModel;
//	}
//	public ContactModel convertContact2ContactModel(Contact contact) {
//		ContactModel contactModel=new ContactModel();
//		contactModel.setCity(contact.getCity());
//		contactModel.setId(contact.getId());
//		contactModel.setFirstname(contact.getFirstname());
//		contactModel.setLastname(contact.getLastname());
//		contactModel.setTelephone(contact.getTelephone());
//		return contactModel;
//		
//	}

}
