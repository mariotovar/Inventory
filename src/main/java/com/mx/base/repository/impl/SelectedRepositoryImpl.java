package com.mx.base.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.repository.SelectedRepository;

@Repository
public class SelectedRepositoryImpl<T extends CatalogModel> implements SelectedRepository<T> {

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
	@SuppressWarnings("unchecked")
	public Map<Long, String> getMapRows(Class<CatalogModel> clazz) {

		Criteria criteria = currentSession().createCriteria(clazz);
		criteria.setProjection(
			Projections.projectionList()
			.add(Projections.property("pk"), "pk")
			.add(Projections.property("value"), "value"));
		criteria.setResultTransformer(Transformers.aliasToBean(clazz));
		criteria.add(Restrictions.eq("status", 'A'));
		criteria.addOrder(Order.asc("value"));
		List<CatalogModel> lstRows = criteria.list();
		
		Map<Long, String> mapRows;
		mapRows = new HashMap<Long, String>();
		for(CatalogModel catalog: lstRows){
			mapRows.put(catalog.getPk(), catalog.getValue());
		}
		
		return mapRows;

	}
    

}
