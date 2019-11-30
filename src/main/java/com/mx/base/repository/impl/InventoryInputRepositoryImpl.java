package com.mx.base.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.mapping.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mx.base.models.catalog.InventoryInput;
import com.mx.base.models.catalog.InventoryInputRow;
import com.mx.base.models.catalog.Lot;
import com.mx.base.models.entity.InputEntity;
import com.mx.base.models.entity.InputItemEntity;
import com.mx.base.models.entity.OrderPKEntity;
import com.mx.base.repository.InventoryInputRepository;
import com.mx.base.util.functions.ParameterCurrency;
import com.mx.base.util.response.PieceCondition;

@Repository
public class InventoryInputRepositoryImpl implements InventoryInputRepository {


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

	@Autowired
	private ParameterCurrency currency;
	
	@Override
	 @Transactional()
	public void saveInventoryInput(InventoryInput inventoryInput) {
		
		long pk = this.getMaxId(inventoryInput.getYear());
		Session session = sessionFactory.getCurrentSession();
		InputEntity inputEntity=new InputEntity();		
		inputEntity.setPk(pk);
		inputEntity.setYear(inventoryInput.getYear());
		inputEntity.setUser(inventoryInput.getUser());		
		session.save(inputEntity);
		
		for (InventoryInputRow itemLot : inventoryInput.getRows()) {
			Lot lot = new Lot();
			
			lot.setQty(itemLot.getQty());
			lot.setUser(inventoryInput.getUser());
			lot.setNote(itemLot.getNotes());
			session.save(lot);
			
			InputItemEntity inputItemEntity =new InputItemEntity();
			inputItemEntity.setPkInput(inputEntity.getPk());
			inputItemEntity.setYear(inputEntity.getYear());
			inputItemEntity.setPkLot(lot.getPk());
			inputItemEntity.setPkProduct(itemLot.getPk());
			inputItemEntity.setPriceMXN(itemLot.getPriceUSD()*currency.getFactorConvertion());

			inputItemEntity.setPriceUSD(itemLot.getPriceUSD());
			inputItemEntity.setDescription(itemLot.getDescription());
			inputItemEntity.setValue(itemLot.getValue());
			inputItemEntity.setCondition(itemLot.getCondition().name());
			session.save(inputItemEntity);
			
		}
		session.flush();
		inventoryInput.setPk(inputEntity.getPk());
	}

	@Override
	public InventoryInput getInventoryInputById(int year, Long pk) {
		Criteria criteria = currentSession().createCriteria(InputEntity.class);
		criteria.add(Restrictions.eq("pk", pk));
		criteria.add(Restrictions.eq("year", year));
		InputEntity inputEntity=(InputEntity) criteria.uniqueResult();
		Criteria criteriaItem = currentSession().createCriteria(InputItemEntity.class);
		criteriaItem.add(Restrictions.eq("pkInput", pk));
		criteriaItem.add(Restrictions.eq("year", year));
		InventoryInput inputResult=new InventoryInput();
		inputResult.setPk(inputEntity.getPk());
		inputResult.setYear(inputEntity.getYear());
		inputResult.setInventoryDate(inputEntity.getCreationDate());
		List<InventoryInputRow> rows=new ArrayList<InventoryInputRow>();
		List<InputItemEntity> itera=criteriaItem.list();
		for(InputItemEntity inputItem:itera) {
			InventoryInputRow row=new InventoryInputRow();
			row.setValue(inputItem.getValue());
			row.setDescription(inputItem.getDescription());
			row.setPriceUSD(inputItem.getPriceUSD());
			row.setPriceMXN(inputItem.getPriceUSD()*currency.getFactorConvertion());
			//row.setNotes(inputItem.get);
			Criteria criteriaItemLot = currentSession().createCriteria(Lot.class);
			criteriaItemLot.add(Restrictions.eq("pk", inputItem.getPkLot()));
			row.setDescription(inputItem.getDescription());
			row.setCondition(PieceCondition.valueOf(inputItem.getCondition()));
			row.setPk(inputItem.getPk());			
		
			Lot lot=(Lot) criteriaItemLot.uniqueResult();			
			row.setLot(lot.getPk());
			row.setNotes(lot.getNote());			
			row.setQty(lot.getQty());
			rows.add(row);
			
		}
		inputResult.setRows(rows);

		
		return inputResult;
	}

	@Override
	public long getMaxId(int year) {
		Criteria criteria = currentSession().createCriteria(InputEntity.class);
		criteria.add(Restrictions.eq("year", year));
		criteria.setProjection(Projections.max("pk"));
		Object result = criteria.uniqueResult();
		long pk = (result==null)? 1 : (long) result + 1;
		return pk;
	}


}
