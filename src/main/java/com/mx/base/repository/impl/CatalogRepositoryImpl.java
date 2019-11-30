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

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.abstractions.CatalogModelList;
import com.mx.base.navegation.CtrlPage;
import com.mx.base.repository.CatalogRepository;

@Repository
public class CatalogRepositoryImpl<T extends CatalogModel> implements CatalogRepository<T> {

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
	public int getNumberOfRows(Class<CatalogModel> clazz) {
		Criteria criteria = currentSession().createCriteria(clazz);
		criteria.add(Restrictions.eq("status", 'A'));
		criteria.setProjection(Projections.rowCount());
		long numberOfROws = (long) criteria.list().get(0);
		return (int) numberOfROws;
	}

	@Override
	public CatalogModel findRow(long pk, Class<CatalogModel> clazz) {
		Criteria criteria = currentSession().createCriteria(clazz);
		criteria.add(Restrictions.eq("pk", pk));
		//criteria.add(Restrictions.eq("status", 'A'));
		CatalogModel catalog = (CatalogModel) criteria.uniqueResult();
		return catalog;
	}

	@Override
	public CatalogModel findRow(String value, Class<CatalogModel> clazz) {
		Criteria criteria = currentSession().createCriteria(clazz);
		//criteria.add(Restrictions.eq("status", 'A'));
		criteria.add(Restrictions.eq("value", value));
		CatalogModel catalog = (CatalogModel) criteria.uniqueResult();
		return catalog;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CatalogModel> getListRows(Class<CatalogModel> clazz) {
		Criteria criteria = currentSession().createCriteria(clazz);
		criteria.add(Restrictions.eq("status", 'A'));
		List<CatalogModel> listRows = criteria.list();
		return listRows;		
	}

	@Override
	public void saveCatalog(CatalogModel catalog) {	
		if(catalog.getPk()==0){
			currentSession().persist(catalog);			
		}
		else{
			currentSession().merge(catalog);
			
		}
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CatalogModel> getPageRows(Class<CatalogModel> clazz, CtrlPage ctrlPage) {
		Criteria criteria = currentSession().createCriteria(clazz);
		criteria.setFirstResult(ctrlPage.getStartIndex()-1);
		criteria.setMaxResults(CtrlPage.DISPLAY_ROWS);	
		criteria.add(Restrictions.eq("status", 'A'));
		criteria.addOrder(Order.asc("value"));
		List<CatalogModel> listRows = criteria.list();
		return listRows;	
	}


	@Override
	public void saveListCatalog(CatalogModelList<T> catalogList) {

		
	}

}
