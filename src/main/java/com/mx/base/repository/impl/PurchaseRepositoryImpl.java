package com.mx.base.repository.impl;

import java.util.Calendar;
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

import com.mx.base.models.catalog.Lot;
import com.mx.base.models.catalog.Product;
import com.mx.base.models.catalog.Purchase;
import com.mx.base.models.catalog.PurchaseExpensetEntity;
import com.mx.base.models.catalog.PurchaseItem;
import com.mx.base.models.catalog.PurchaseItemLot;
import com.mx.base.models.entity.QuoteOrderEntity;
import com.mx.base.repository.PurchaseRepository;
import com.mx.base.util.functions.DateUtils;
import com.mx.base.util.functions.ParameterCurrency;

@Repository
@Transactional
public class PurchaseRepositoryImpl implements PurchaseRepository {
	
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
	@Transactional()
	public Purchase save(Purchase purchase) {
		long pk = purchase.getPk();
		Session session = currentSession();
		if (purchase.getPk() == 0) {
			purchase.setYear(DateUtils.getCurrentYear());
			pk = this.getMaxId(purchase.getYear());
			purchase.setPk(pk);

			System.out.println("---------------------------------");
			System.out.println("---------------------------------");

			System.out.println("PK: " + pk);
			System.out.println("year: " + purchase.getYear());

			
			System.out.println("---------------------------------");
			System.out.println("---------------------------------");

			
			purchase.setCreationDate(Calendar.getInstance().getTime());
			session.save(purchase);
		}else {
			session.merge(purchase);
			Query query = session.createQuery("delete PurchaseItem where PK_PURCHASE = :pk_purchase AND _YEAR = :year");
			query.setParameter("pk_purchase", purchase.getPk());
			query.setParameter("year", purchase.getYear());
			query.executeUpdate();
		}
		
		double factorMXN = currency.getFactorConvertion();
		for (PurchaseItem item : purchase.getPurchaseItem()) {
			item.setPkPurchase(purchase.getPk());
			item.setYear(purchase.getYear());
			item.setPrice_mxn(item.getPrice_usd() * factorMXN);
			session.save(item);
		}
		
		session.flush();

		return purchase;
	}

	@Override
	public Purchase getPurchaseById(Long id, int year) {
		Session session = currentSession();
		Criteria criteria = session.createCriteria(Purchase.class);
		criteria.add(Restrictions.eq("pk", id));
		criteria.add(Restrictions.eq("year", year));
		Purchase purchase = (Purchase) criteria.uniqueResult();
		Criteria criteriaItem = session.createCriteria(PurchaseItem.class);
		criteriaItem.add(Restrictions.eq("pkPurchase", id));
		criteriaItem.add(Restrictions.eq("year", year));

		List<PurchaseItem> lstItems = (List<PurchaseItem>) criteriaItem.list();
		for (PurchaseItem itemTemp : lstItems) {
			Criteria productCriteria = session.createCriteria(Product.class);
			productCriteria.add(Restrictions.eq("pk", itemTemp.getPkProduct()));
			Product product=(Product)productCriteria.uniqueResult();
			itemTemp.setProduct(product);
			
			Criteria criteriaItemLot = session.createCriteria(PurchaseItemLot.class);
			criteriaItemLot.add(Restrictions.eq("pkPurchase", id));
			criteriaItemLot.add(Restrictions.eq("year", year));
			criteriaItemLot.add(Restrictions.eq("pkItem", itemTemp.getPk()));

			List<PurchaseItemLot> lstItemsLot = (List<PurchaseItemLot>) criteriaItemLot.list();

			for (PurchaseItemLot itemLot : lstItemsLot) {
				Criteria criteriaLot = session.createCriteria(Lot.class);
				criteriaLot.add(Restrictions.eq("pk", itemLot.getPkLot()));

				List<Lot> lstLot = (List<Lot>) criteriaLot.list();
				for (Lot lot : lstLot) {

					itemLot.addLot(lot);
				}
				itemTemp.addItemLot(itemLot);
			}
			purchase.addItem(itemTemp);
		}
		return purchase;
	}

	@Override
	public List<PurchaseExpensetEntity> getExpenseByPurchaseId(Long pk, int year) {
		Criteria criteria = currentSession().createCriteria(PurchaseExpensetEntity.class);
		criteria.add(Restrictions.eq("year", year));
		criteria.add(Restrictions.eq("pkPurchase", pk));
		List<PurchaseExpensetEntity> lst = (List<PurchaseExpensetEntity>) criteria.list();

		return lst;
	}

	@Override
	@Transactional()
	public void saveExpenses(Long pk, int year, List<PurchaseExpensetEntity> lstExpenses) {
		Session session = currentSession();
		Query query = session.createQuery("delete PurchaseExpensetEntity where pk_purchase = :pk_purchase  AND _YEAR = :year");
		query.setParameter("pk_purchase", pk);
		query.setParameter("year", year);
		query.executeUpdate();
		for (PurchaseExpensetEntity entity : lstExpenses) {
			entity.setAmountUSD(entity.getAmountMXN()/currency.getFactorConvertion());
			session.save(entity);
		}
		session.flush();
	}

	@Override
	public void updateStatusPurchase(Long pk, int year, int status) {
		Session session = currentSession();

		
		System.out.println("purchase: " + pk);
		
		 Query query = currentSession().createQuery(
			      "UPDATE Purchase SET status =:_status  where pk=:_pk");
		 query.setParameter("_status", status);
		 query.setParameter("_pk", pk);
		 query.executeUpdate();

		session.flush();
	}

	@Override
	@Transactional()
	public void saveReceive(Purchase purchase) {
		Session session = currentSession();
		for (PurchaseItem item : purchase.getPurchaseItem()) {
			for (PurchaseItemLot itemlot : item.getPurchaseItemLot()) {
				if (itemlot.getPk() <= 0) {
					Lot lot = new Lot();
					lot.setQty(itemlot.getQtyForLot());
					lot.setUser(itemlot.getUserForLot());
					session.save(lot);
				}
			}
		}

	}

	@Override
	@Transactional()
	public void saveReceive(List<PurchaseItemLot> purchase) {
		Session session = currentSession();
		for (PurchaseItemLot itemlot : purchase) {
			Lot lot = new Lot();
			lot.setQty(itemlot.getQtyForLot());
			lot.setUser(itemlot.getUserForLot());
			lot.setNote(itemlot.getNoteForLot());
			session.save(lot);
			itemlot.setPkLot(lot.getPk());
			session.save(itemlot);
		}
	}
	
	@Override
	public long getMaxId(int year) {
		Criteria criteria = currentSession().createCriteria(Purchase.class);
		criteria.add(Restrictions.eq("year", year));
		criteria.setProjection(Projections.max("pk"));
		Object result = criteria.uniqueResult();
		long pk = (result==null)? 1 : (long) result + 1;
		return pk;
	}		

}
