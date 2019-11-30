package com.mx.base.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mx.base.models.catalog.PurchasePaymentEntity;
import com.mx.base.repository.PurchasePaymentRepository;

@Repository
public class PurchasePaymentRepositoryImpl implements PurchasePaymentRepository{
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
	
	

	@Override
	@SuppressWarnings("unchecked")
	public List<PurchasePaymentEntity> getByPurchaseId(Long pk, int year) {
		
		Criteria criteria = currentSession().createCriteria(PurchasePaymentEntity.class);
		criteria.add(Restrictions.eq("year", year));
		criteria.add(Restrictions.eq("pkPurchase", pk));
		return (List<PurchasePaymentEntity>) criteria.list();
	
	}

	@Override
	@Transactional
	public void savePayments(Long pk, int year, List<PurchasePaymentEntity> lstPayments) {

		Session session=currentSession();	
		for(PurchasePaymentEntity entity: lstPayments) {
			if (entity.getPk()==0) {
				session.save(entity);
			}else if(entity.getPk()>0 && entity.getAmountUSD()==0){
				session.delete(entity);	
			}else {
				session.merge(entity);	
			}	
		}
		
		session.flush();
	}

}
