package com.mx.base.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mx.base.models.catalog.Profile;
import com.mx.base.models.catalog.Purchase;
import com.mx.base.repository.ProfileRepository;
import com.mx.base.repository.PurchaseRepository;

@Repository("profileRepository")
public class ProfileRepositoryImp implements ProfileRepository {
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

//		return sessionFactory.openSession();
	}

	@Override
	public List<Profile> findByStatus(String status) {
		
//		Session session = currentSession();
		Criteria criteria = currentSession().createCriteria(Profile.class);
//		criteria.add(Restrictions.eq("orderNumber", orderNumber));
		return (List<Profile>) criteria.list();
		// TODO Auto-generated method stub
//		return null;
	}

}
