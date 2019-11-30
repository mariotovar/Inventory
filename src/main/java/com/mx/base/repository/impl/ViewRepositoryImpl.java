package com.mx.base.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mx.base.abstractions.ViewModel;
import com.mx.base.models.view.PurchaseOrder;
import com.mx.base.repository.ViewRepository;

@Repository
public class ViewRepositoryImpl<T extends ViewModel> implements ViewRepository<T> {

    @Resource(name="sessionFactory")
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
	public int getNumberOfRows(Class<T> clazz) {
		Criteria criteria = currentSession().createCriteria(clazz);
		criteria.setProjection(Projections.rowCount());
		long numberOfROws = (long) criteria.list().get(0);
		return (int) numberOfROws;
	}
    
	@Override
	@SuppressWarnings("unchecked")
	public List<ViewModel> getView(Class<T> clazz) {
		Criteria criteria = currentSession().createCriteria(clazz);
		criteria.add(Restrictions.eq("current", "ACTIVE"));
		List<ViewModel> listRows = criteria.list();
		return listRows;	
	}
    
	
	@Override
	@SuppressWarnings("unchecked")
	public List<ViewModel> getHistory(Class<T> clazz, int year) {
		Criteria criteria = currentSession().createCriteria(clazz);
		criteria.add(Restrictions.eq("year", year));
		criteria.addOrder(Order.desc("pk"));
		List<ViewModel> listRows = criteria.list();
		return listRows;	
	}
    	
}
