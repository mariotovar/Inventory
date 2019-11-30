package com.mx.base.repository.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.mx.base.models.catalog.InputReceived;
import com.mx.base.models.catalog.Item;
import com.mx.base.models.view.InventoryLot;
import com.mx.base.repository.InventoryLotRepository;
import com.mx.base.util.response.PieceCondition;

@Repository
public class InventoriLotRepositoryImpl implements InventoryLotRepository {

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
	public Item getAllInventoryLotByProduct(Long Id){

		Session session=currentSession();
		Criteria criteria = session.createCriteria(InventoryLot.class);
		criteria.add(Restrictions.eq("pk", Id));
		criteria.addOrder(Order.asc("lot"));
		
		List<InventoryLot> lst = (List<InventoryLot>) criteria.list();
		Item item=null;
		long i=0;
		for(InventoryLot inventoryLot:lst) {
			if (i==0) {
				item=new Item();
				item.setDescription(inventoryLot.getDescription());
				item.setCondition(PieceCondition.valueOf(inventoryLot.getCondition()));
				item.setValue(inventoryLot.getValue());
				item.setPk(inventoryLot.getPk());
				item.setCostMXN(inventoryLot.getCostMXN());
				item.setCostUSD(inventoryLot.getCostUSD());
				i++;
			}
						
			InputReceived inputeReceive=new InputReceived();
			inputeReceive.setLot(inventoryLot.getLot());
			inputeReceive.setNotes("");
			inputeReceive.setReceivedQty(inventoryLot.getInputs());
			inputeReceive.setStock(inventoryLot.getInputs() - inventoryLot.getOutputs());
			item.getInputs().add(inputeReceive);
					
		}
		return item;
	}
}
