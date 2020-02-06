package com.mx.base.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.base.models.catalog.Client;
import com.mx.base.models.catalog.Item;
import com.mx.base.models.catalog.QuoteOrder;
import com.mx.base.models.entity.QuoteOrderEntity;
import com.mx.base.models.entity.QuoteOrderItemEntity;
import com.mx.base.services.CatalogService;
import com.mx.base.util.functions.ParameterCurrency;
import com.mx.base.util.response.PieceCondition;
import com.mx.base.util.response.StatusOrder;

@Service
public class QuoteOrderConveter {
	
	@Autowired
	private ParameterCurrency currency;
	
	@Autowired
	private CatalogService catalogService;		
	
	
	public QuoteOrderEntity convertModel2Entity(QuoteOrder model){
		
		QuoteOrderEntity entity;
		QuoteOrderItemEntity entityItem;
		
		entity = new QuoteOrderEntity();
		entity.setPk(model.getPkQuote());
		entity.setYear(model.getYear());
		entity.setPkClient(model.getClient().getPk());
		entity.setNotes(model.getNotes());
		entity.setUser(model.getUser());
		entity.setStatus(model.getStatus().getpk());
		entity.setFactor_iva(model.getFactorIva());
		for(Item modelItem: model.getItems()){
			entityItem = new QuoteOrderItemEntity();
			entityItem.setPkQuote(model.getPkQuote());
			entityItem.setYear(model.getYear());
			entityItem.setPkProduct(modelItem.getPk());
			entityItem.setValue(modelItem.getValue());
			entityItem.setDescription(modelItem.getDescription());
			entityItem.setCondition(modelItem.getCondition().name());
			entityItem.setQty(modelItem.getQty());
			entityItem.setCostMXN(modelItem.getCostMXN());
			entityItem.setCostUSD(modelItem.getCostMXN() / currency.getFactorConvertion());	
			entity.getItems().add(entityItem);
		}
		
		return entity;
	}
	
	
	public QuoteOrder convertEntity2Model(QuoteOrderEntity entity){
		
		QuoteOrder model;
		Item modelItem;
		
		model = new QuoteOrder();
		model.setPkQuote(entity.getPk());
		model.setYear(entity.getYear());
		model.setNotes(entity.getNotes());
		model.setUser(entity.getUser());
		model.setQuoteDate(entity.getCreationDate());
		model.setStatus(StatusOrder.getStatus(entity.getStatus()));
		model.setFactorIva(entity.getFactor_iva());
		Client client;
		client = (Client) catalogService.findRow(entity.getPkClient(), Client.class);
		model.setClient(client);
		
		for(QuoteOrderItemEntity entityItem: entity.getItems()){
			modelItem = new Item();
			modelItem.setPk(entityItem.getPkProduct());
			modelItem.setValue(entityItem.getValue());
			modelItem.setDescription(entityItem.getDescription());
			modelItem.setCondition(PieceCondition.valueOf(entityItem.getCondition()));
			modelItem.setQty(entityItem.getQty());
			modelItem.setCostMXN(entityItem.getCostMXN());
			modelItem.setCostUSD(entityItem.getCostUSD());	
			model.getItems().add(modelItem);
		}
		
		return model;
	}	

}
