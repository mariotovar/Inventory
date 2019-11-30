package com.mx.base.repository.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mx.base.models.catalog.Client;
import com.mx.base.models.catalog.InputReceived;
import com.mx.base.models.catalog.InventoryOutput;
import com.mx.base.models.catalog.Item;
import com.mx.base.models.catalog.OutputSold;
import com.mx.base.models.catalog.SaleOrder;
import com.mx.base.models.entity.InputEntity;
import com.mx.base.models.entity.LotOutPutEntity;
import com.mx.base.models.entity.OutPutEntity;
import com.mx.base.models.entity.OutPutItemEntity;
import com.mx.base.models.entity.SaleOrderEntity;
import com.mx.base.models.entity.SaleOrderItemEntity;
import com.mx.base.repository.OutPutRepository;
import com.mx.base.util.functions.ParameterCurrency;
import com.mx.base.util.response.PieceCondition;

@Repository
public class OutPutRepositoryImpl implements OutPutRepository {

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

	@Autowired
	private ParameterCurrency currency;
	
	@Override
	@Transactional
	public void saveOutPut(Object out) {

		InventoryOutput invout = (InventoryOutput) out;
		long pk = this.getMaxId(invout.getYear());
		
		Session session = currentSession();
		OutPutEntity outputIden = new OutPutEntity();
		outputIden.setPk(pk);
		outputIden.setYear(invout.getYear());
		outputIden.setUser(invout.getUser());
		session.save(outputIden);

		for (Item item : invout.getItems()) {

			for (InputReceived input : item.getInputs()) {
				LotOutPutEntity lotOutPut = new LotOutPutEntity();
				lotOutPut.setQty(input.getOutputs().get(0).getQty());
				lotOutPut.setUser(invout.getUser());
				lotOutPut.setPkLot(input.getLot());
				lotOutPut.setCostMXN(input.getOutputs().get(0).getCostMXN());
				lotOutPut.setCostUSD(input.getOutputs().get(0).getCostMXN()/currency.getFactorConvertion());
				session.save(lotOutPut);

				OutPutItemEntity outPutItemEntity = new OutPutItemEntity();
				outPutItemEntity.setDescription(item.getDescription());
				outPutItemEntity.setCondition(item.getCondition().name());
				outPutItemEntity.setValue(item.getValue());
				outPutItemEntity.setPkOutPut(outputIden.getPk());
				outPutItemEntity.setYear(outputIden.getYear());
				outPutItemEntity.setNotes(input.getNotes());
				outPutItemEntity.setPkProduct(item.getPk());
				outPutItemEntity.setPkLotOutPut(lotOutPut.getPk());
				session.save(outPutItemEntity);
			}

		}

		invout.setPk(outputIden.getPk());

	}

	@Override
	public Object getInventoryOutPut(int year, Long pk) {
		
		Session session = currentSession();
		InventoryOutput inventoryOutput = new InventoryOutput();

		Criteria criteria = session.createCriteria(OutPutEntity.class);
		criteria.add(Restrictions.eq("pk", pk));
		criteria.add(Restrictions.eq("year", year));
		OutPutEntity outPutEntity = (OutPutEntity) criteria.uniqueResult();
		inventoryOutput.setPk(outPutEntity.getPk());
		inventoryOutput.setYear(outPutEntity.getYear());

		inventoryOutput.setInventoryDate(outPutEntity.getCreationDate());
		Criteria criteriaItem = session.createCriteria(OutPutItemEntity.class);
		criteriaItem.add(Restrictions.eq("pkOutPut", outPutEntity.getPk()));
		criteriaItem.add(Restrictions.eq("year", outPutEntity.getYear()));
		List<OutPutItemEntity> lst = criteriaItem.list();

		for (OutPutItemEntity itemLst : lst) {
			Item item = new Item();
			item.setValue(itemLst.getValue());
			item.setDescription(itemLst.getDescription());
			item.setCondition(PieceCondition.valueOf(itemLst.getCondition()));

			InputReceived input = new InputReceived();

			Criteria criteriaLotOutput = session.createCriteria(LotOutPutEntity.class);
			criteriaLotOutput.add(Restrictions.eq("pk", itemLst.getPkLotOutPut()));
			LotOutPutEntity lotOutPutEntity = (LotOutPutEntity) criteriaLotOutput.uniqueResult();

			OutputSold sol = new OutputSold();

			input.getOutputs().add(sol);
			input.getOutputs().get(0).setQty(lotOutPutEntity.getQty());
			input.getOutputs().get(0).setUser(lotOutPutEntity.getUser());
			input.getOutputs().get(0).setCostMXN(lotOutPutEntity.getCostMXN());
			input.getOutputs().get(0).setCostUSD(lotOutPutEntity.getCostUSD());
			input.setNotes(itemLst.getNotes());
			item.getInputs().add(input);
			inventoryOutput.getItems().add(item);
		}
		return inventoryOutput;

	}
	
	
	@Override
	public long getMaxId(int year) {
		Criteria criteria = currentSession().createCriteria(OutPutEntity.class);
		criteria.add(Restrictions.eq("year", year));
		criteria.setProjection(Projections.max("pk"));
		Object result = criteria.uniqueResult();
		long pk = (result==null)? 1 : (long) result + 1;
		return pk;
	}

}
