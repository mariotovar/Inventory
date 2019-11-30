package com.mx.base.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mx.base.models.entity.QuoteOrderEntity;
import com.mx.base.models.entity.QuoteOrderItemEntity;
import com.mx.base.repository.QuoteOrderRepository;
import com.mx.base.util.response.StatusOrder;

@Repository
public class QuoteOrderRepositoryImpl implements QuoteOrderRepository{
	
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
	public Long saveQuoteOrder(QuoteOrderEntity quoteOrderEntity) {
		
		Long pk = quoteOrderEntity.getPk();
		int year = quoteOrderEntity.getYear();
		Session session = this.currentSession();
		
		if(quoteOrderEntity.getPk()!=0){
			this.deleteQuoteItemOrder(session, pk, year);
			session.merge(quoteOrderEntity);
		}
		else{
			pk = this.getMaxId(year);
			quoteOrderEntity.setPk(pk);
			session.save(quoteOrderEntity);
		}
		
		for(QuoteOrderItemEntity item: quoteOrderEntity.getItems()){
			item.setYear(year);
			item.setPkQuote(pk);
			session.save(item);
		}
		
		session.flush();
		
		return pk;
	}

	@Override
	public void deleteQuoteOrder(long pk, int year) {
		
		Session session = this.currentSession();
		Criteria criteria = session.createCriteria(QuoteOrderEntity.class);
		criteria.add(Restrictions.eq("pk", pk));
		criteria.add(Restrictions.eq("year", year));
			
		QuoteOrderEntity quoteOrderEntity;
		quoteOrderEntity = (QuoteOrderEntity) criteria.uniqueResult();
		quoteOrderEntity.setStatus(StatusOrder.CANCEL.getpk());
		session.save(quoteOrderEntity);
		
		
	}
	
	@SuppressWarnings("unchecked")
	private void deleteQuoteItemOrder(Session session, long pk, int year) {
		
		Criteria criteria = session.createCriteria(QuoteOrderItemEntity.class);
		criteria.add(Restrictions.eq("pkQuote", pk));
		criteria.add(Restrictions.eq("year", year));		
		List<QuoteOrderItemEntity> items = criteria.list();

		for(QuoteOrderItemEntity item: items){
			session.delete(item);
		}
		
	}


	@Override
	@SuppressWarnings("unchecked")
	public QuoteOrderEntity findByIdQuoteOrder(long pk, int year) {

		Session session = this.currentSession();
		Criteria criteria = session.createCriteria(QuoteOrderEntity.class);
		criteria.add(Restrictions.eq("pk", pk));
		criteria.add(Restrictions.eq("year", year));
			
		QuoteOrderEntity quoteOrderEntity;
		quoteOrderEntity = (QuoteOrderEntity) criteria.uniqueResult();
		quoteOrderEntity.setItems(new ArrayList<QuoteOrderItemEntity>());
		
		Criteria criteriaItems = session.createCriteria(QuoteOrderItemEntity.class);
		criteriaItems.add(Restrictions.eq("pkQuote", pk));
		criteriaItems.add(Restrictions.eq("year", year));

		List<QuoteOrderItemEntity> items;
		items = (List<QuoteOrderItemEntity>) criteriaItems.list();
		quoteOrderEntity.getItems().addAll(items);		
		
		return quoteOrderEntity;
	}	
	
	@Override
	public long getMaxId(int year) {
		Criteria criteria = currentSession().createCriteria(QuoteOrderEntity.class);
		criteria.add(Restrictions.eq("year", year));
		criteria.setProjection(Projections.max("pk"));
		Object result = criteria.uniqueResult();
		long pk = (result==null)? 1 : (long) result + 1;
		return pk;
	}	

}
