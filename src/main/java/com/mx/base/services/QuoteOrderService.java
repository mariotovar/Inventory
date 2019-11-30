package com.mx.base.services;

import com.mx.base.models.catalog.QuoteOrder;

public interface QuoteOrderService {
	
	QuoteOrder saveQuoteOrder(QuoteOrder quoteOrder);
	QuoteOrder updateQuoteOrder(QuoteOrder quoteOrder);
	QuoteOrder findByIdQuoteOrder(long pk, int year);	
	void deleteQuoteOrder(long pk, int year);
}
