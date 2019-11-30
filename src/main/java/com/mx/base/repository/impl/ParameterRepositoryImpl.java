package com.mx.base.repository.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mx.base.models.catalog.ParameterValues;
import com.mx.base.models.entity.ParameterEntity;
import com.mx.base.models.entity.ParameterValueEntity;
import com.mx.base.repository.ParameterRespository;

@Repository
public class ParameterRepositoryImpl implements ParameterRespository {

	@Autowired
	SessionFactory sessionFactory;

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
	public Object getParametersByCode(String code) {
		// TODO Auto-generated method stub
		Session session = currentSession();
		Criteria crit = session.createCriteria(ParameterEntity.class);
		crit.add(Restrictions.ilike("code", code, MatchMode.ANYWHERE));
		ParameterEntity parameter = (ParameterEntity) crit.uniqueResult();

		Criteria criteriaValue = session.createCriteria(ParameterValueEntity.class);
		criteriaValue.add(Restrictions.ilike("status", "a", MatchMode.ANYWHERE));
		criteriaValue.add(Restrictions.eq("pkParameter", parameter.getPk()));
		List<ParameterValueEntity> lstParameter = (List<ParameterValueEntity>) criteriaValue.list();
		return lstParameter;
	}

	@Override
	public void updateParametersByCode(Object lst) {
		// TODO Auto-generated method stub
		Session session = currentSession();
		
//		Criteria crit = session.createCriteria(ParameterEntity.class);
//		crit.add(Restrictions.ilike("code", code, MatchMode.ANYWHERE));
//		ParameterEntity parameter = (ParameterEntity) crit.uniqueResult();
		
		Iterator it = ((ParameterValues) lst).getValues().entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			Query query = session.createQuery("update ParameterValueEntity set description=:description  where value = :value and status='A' ");
			query.setParameter("value", pair.getKey());

			query.setParameter("description", pair.getValue());
			query.executeUpdate();
			System.out.println(pair.getKey() + " = " + pair.getValue());
		}
		
		
	}

}
