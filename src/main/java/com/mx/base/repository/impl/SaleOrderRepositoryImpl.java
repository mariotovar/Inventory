
package com.mx.base.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mx.base.models.catalog.Client;
import com.mx.base.models.catalog.InputReceived;
import com.mx.base.models.catalog.Item;
import com.mx.base.models.catalog.OutputSold;
import com.mx.base.models.catalog.Payment;
import com.mx.base.models.catalog.SaleOrder;
import com.mx.base.models.entity.LotOutPutEntity;
import com.mx.base.models.entity.QuoteOrderEntity;
import com.mx.base.models.entity.SaleOrderEntity;
import com.mx.base.models.entity.SaleOrderItemEntity;
import com.mx.base.models.entity.SaleOrderPaymentEntity;
import com.mx.base.repository.SaleOrderRepository;
import com.mx.base.util.functions.ParameterCurrency;
import com.mx.base.util.response.PieceCondition;
import com.mx.base.util.response.StatusOrder;

@Repository
public class SaleOrderRepositoryImpl implements SaleOrderRepository {

	@Resource(name = "sessionFactory")
	private SessionFactory sessionFactory;

	private Session currentSession() {
		Session session;
		try {
			session = sessionFactory.getCurrentSession();
		} catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	@Autowired
	private ParameterCurrency currency;
	
	@Override
	@Transactional
	public void saveSaleOrder(SaleOrder saleOrder) {
		
		long pk = this.getMaxId(saleOrder.getYear());
		
		Session session = currentSession();
		SaleOrderEntity saleEntity = new SaleOrderEntity();
		saleEntity.setPk(pk);
		saleEntity.setYear(saleOrder.getYear());
		saleEntity.setPkClient(saleOrder.getClient().getPk());
		saleEntity.setTaxIVA(currency.getFactorIVA() / 100);
		saleEntity.setStatus(StatusOrder.NEW.getpk());
		saleEntity.setNotes(saleOrder.getNotes());
		saleEntity.setUser(saleOrder.getUser());
		
		session.save(saleEntity);
		for (Item item : saleOrder.getItems()) {

			for (InputReceived input : item.getInputs()) {
				SaleOrderItemEntity saleItemEntity = new SaleOrderItemEntity();
				LotOutPutEntity lotOutPut = new LotOutPutEntity();
				lotOutPut.setQty(input.getOutputs().get(0).getQty());
				lotOutPut.setUser(saleOrder.getUser());
				lotOutPut.setPkLot(input.getLot());
				lotOutPut.setCostMXN(input.getOutputs().get(0).getCostMXN());
				lotOutPut.setCostUSD(input.getOutputs().get(0).getCostMXN()/currency.getFactorConvertion());
				session.save(lotOutPut);
				
				saleItemEntity.setPkLotOutPut(lotOutPut.getPk());
				saleItemEntity.setDescription(item.getDescription());
				saleItemEntity.setCondition(item.getCondition().name());
				saleItemEntity.setPkProduct(item.getPk());
				saleItemEntity.setValue(item.getValue());
				saleItemEntity.setPkSale(saleEntity.getPk());
				saleItemEntity.setYear(saleEntity.getYear());
				session.save(saleItemEntity);
			}

		}

		saleOrder.setPkSale(saleEntity.getPk());
		session.flush();
	}

	@Override
	public Object getSaleOrderById(long pk, int year) {
		// TODO Auto-generated method stub
		SaleOrder saleOrder = new SaleOrder();
		Session session = currentSession();

		Criteria criteria = session.createCriteria(SaleOrderEntity.class);
		criteria.add(Restrictions.eq("pk", pk));
		criteria.add(Restrictions.eq("year", year));
		SaleOrderEntity saleOrderEntity = (SaleOrderEntity) criteria.uniqueResult();
		saleOrder.setPkSale(saleOrderEntity.getPk());
		saleOrder.setYear(saleOrderEntity.getYear());
		saleOrder.setSaleDate(saleOrderEntity.getCreationDate());
		saleOrder.setTaxIVA(saleOrderEntity.getTaxIVA());
		saleOrder.setStatus(StatusOrder.getStatus(saleOrderEntity.getStatus()));
		saleOrder.setNotes(saleOrderEntity.getNotes());
		
		Client client = new Client();
		client.setPk(saleOrderEntity.getPkClient());
		saleOrder.setClient(client);
		Criteria criteriaItem = session.createCriteria(SaleOrderItemEntity.class);
		criteriaItem.add(Restrictions.eq("year", saleOrderEntity.getYear()));
		criteriaItem.add(Restrictions.eq("pkSale", saleOrderEntity.getPk()));
		List<SaleOrderItemEntity> lst = criteriaItem.list();
		for (SaleOrderItemEntity itemLst : lst) {
			Item item = new Item();
			item.setDescription(itemLst.getDescription());
			item.setCondition(PieceCondition.valueOf(itemLst.getCondition()));
			item.setValue(itemLst.getValue());
			InputReceived input = new InputReceived();
			Criteria criteriaLotOutput = session.createCriteria(LotOutPutEntity.class);
			criteriaLotOutput.add(Restrictions.eq("pk", itemLst.getPkLotOutPut()));
			LotOutPutEntity lotOutPutEntity = (LotOutPutEntity) criteriaLotOutput.uniqueResult();
			
			OutputSold sol = new OutputSold();
			input.getOutputs().add(sol);
			input.getOutputs().get(0).setQty(lotOutPutEntity.getQty());
			input.getOutputs().get(0).setUser(lotOutPutEntity.getUser());
			input.getOutputs().get(0).setCostMXN(lotOutPutEntity.getCostMXN());
			input.getOutputs().get(0).setCostUSD(lotOutPutEntity.getCostUSD());
			input.setLot(lotOutPutEntity.getPkLot());
			item.getInputs().add(input);
			saleOrder.getItems().add(item);
		}
		return saleOrder;
	}

	@Override
	public List<Payment> getAllPaymentById(long pk, int year) {

		List<Payment> result = new ArrayList<Payment>();
		Session session = currentSession();

		Criteria criteria = session.createCriteria(SaleOrderPaymentEntity.class);
		criteria.add(Restrictions.eq("year", year));
		criteria.add(Restrictions.eq("pkSaleOrder", pk));

		for (SaleOrderPaymentEntity salePayment : (List<SaleOrderPaymentEntity>) criteria.list()) {
			Payment payment = new Payment();
			payment.setPk(salePayment.getPk());
			payment.setYear(salePayment.getYear());
			payment.setAmountMXN(salePayment.getAmountMXN());
			payment.setAmountUSD(salePayment.getAmountMXN() / currency.getFactorConvertion());
			payment.setNotes(salePayment.getNotes());
			payment.setPaymentDate(salePayment.getSaleDate());
			result.add(payment);
		}

		return result;
	}

	@Override
	public void saveAllPaymentById(Object object) {
		Session session = currentSession();
		SaleOrder saleOrder = (SaleOrder) object;
	
		for (Payment payment : saleOrder.getPayments()) {
			SaleOrderPaymentEntity paymentEntity = new SaleOrderPaymentEntity();
			paymentEntity.setPk(payment.getPk());
			paymentEntity.setYear(payment.getYear());
			paymentEntity.setPkSaleOrder(saleOrder.getPkSale());
			paymentEntity.setAmountMXN(payment.getAmountMXN());
			paymentEntity.setAmountUSD(payment.getAmountMXN() / currency.getFactorConvertion());
			paymentEntity.setNotes(payment.getNotes());
			paymentEntity.setUser(saleOrder.getUser());
			paymentEntity.setSaleDate(payment.getPaymentDate());
			if (payment.getPk()==0) {
				session.save(paymentEntity);
			}else if(payment.getPk()>0 &&payment.getAmountMXN()==0){
				session.delete(paymentEntity);	
			}else {
				session.merge(paymentEntity);	
			}
			payment.setPk(paymentEntity.getPk());
			payment.setYear(paymentEntity.getYear());
		}
		session.flush();

	}

	@Override
	public void updateStatus(long pk, int year, int status) {

		Session session = currentSession();
	/*	Criteria criteria = session.createCriteria(SaleOrderEntity.class);
		criteria.add(Restrictions.eq("pk", pk));
		criteria.add(Restrictions.eq("year", year));
		SaleOrderEntity saleOrderEntity = (SaleOrderEntity) criteria.uniqueResult();
		saleOrderEntity.setStatus(status);
*/	 
		Query query = currentSession().createQuery(
	      "UPDATE SaleOrderEntity SET status =:_status  where pk=:_pk");
		query.setParameter("_status", status);
		query.setParameter("_pk", pk);
		query.executeUpdate();
//		session.save(saleOrderEntity);
		session.flush();
	}

	@Override
	public long getMaxId(int year) {
		Criteria criteria = currentSession().createCriteria(SaleOrderEntity.class);
		criteria.add(Restrictions.eq("year", year));
		criteria.setProjection(Projections.max("pk"));
		Object result = criteria.uniqueResult();
		long pk = (result==null)? 1 : (long) result + 1;
		return pk;
	}	

	
}
