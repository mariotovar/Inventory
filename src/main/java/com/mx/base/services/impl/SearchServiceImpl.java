package com.mx.base.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.repository.SearchRepository;
import com.mx.base.services.SearchService;

@Service
@Transactional(readOnly = false)
public class SearchServiceImpl<T extends CatalogModel> implements SearchService<T> {

	@Autowired
    private SearchRepository repository;	
	
	@Override
	public Map<Long, String> getMapRows(Class<T> clazz, String inputSearch) {

		Map<Long, String> mapRows;
		mapRows = repository.getMapRows(clazz, inputSearch);
		return mapRows;
		
	}

}
