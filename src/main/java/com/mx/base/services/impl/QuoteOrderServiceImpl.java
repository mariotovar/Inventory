package com.mx.base.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mx.base.component.QuoteOrderConveter;
import com.mx.base.models.catalog.QuoteOrder;
import com.mx.base.models.entity.QuoteOrderEntity;
import com.mx.base.repository.QuoteOrderRepository;
import com.mx.base.services.QuoteOrderService;


@Service
@Transactional(propagation=Propagation.REQUIRED)
public class QuoteOrderServiceImpl implements QuoteOrderService{
	
	@Autowired
	private QuoteOrderConveter quoteOrderConveter;	

	@Autowired
    private QuoteOrderRepository quoteOrderRepository;	

	
	@Override
	public QuoteOrder saveQuoteOrder(QuoteOrder quoteOrder) {

		Long pk;
		QuoteOrderEntity quoteOrderEntity;
		quoteOrderEntity = quoteOrderConveter.convertModel2Entity(quoteOrder);
		pk = quoteOrderRepository.saveQuoteOrder(quoteOrderEntity);
		quoteOrder.setPkQuote(pk);
				
		return quoteOrder;
	
	}

	@Override
	public QuoteOrder updateQuoteOrder(QuoteOrder quoteOrder) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteQuoteOrder(long pk, int year) {

		quoteOrderRepository.deleteQuoteOrder(pk, year);
		
	}

	@Override
	public QuoteOrder findByIdQuoteOrder(long pk, int year) {

		QuoteOrderEntity quoteOrderEntity;
		quoteOrderEntity = quoteOrderRepository.findByIdQuoteOrder(pk, year);
		
		QuoteOrder quoteOrder;
		quoteOrder = quoteOrderConveter.convertEntity2Model(quoteOrderEntity);
		
		return quoteOrder;

	}

}
