package com.mx.base.repository.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.repository.SearchRepository;

@Repository
public class SearchRepositoryImpl<T extends CatalogModel> implements SearchRepository<T> {

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
	public Map<Long, String> getMapRows(Class<CatalogModel> clazz, String inputSearch) {

		Criteria criteria = currentSession().createCriteria(clazz);
		criteria.add(Restrictions.like("value", inputSearch, MatchMode.START));
		criteria.add(Restrictions.eq("status", 'A'));
		criteria.setProjection(
			Projections.projectionList()
			.add(Projections.property("pk"), "pk")
			.add(Projections.property("value"), "value"));
		criteria.setResultTransformer(Transformers.aliasToBean(clazz));
		criteria.addOrder(Order.asc("value"));
		List<CatalogModel> lstRows = criteria.list();
		
		Map<Long, String> mapRows;
		mapRows = new HashMap<Long, String>();
		for(CatalogModel catalog: lstRows){
			System.out.println("pk: " + catalog.getPk() + " value: " + catalog.getValue());
			mapRows.put(catalog.getPk(), catalog.getValue());
		}
		
		return mapRows;

	}
    

}
