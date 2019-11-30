package com.mx.base.repository;

import com.mx.base.models.entity.QuoteOrderEntity;

public interface QuoteOrderRepository {

	void deleteQuoteOrder(long pk, int year);
	Long saveQuoteOrder(QuoteOrderEntity quoteOrderEntity);
	QuoteOrderEntity findByIdQuoteOrder(long pk, int year);
	long getMaxId(int year);
	
}
