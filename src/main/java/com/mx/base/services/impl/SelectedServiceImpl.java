package com.mx.base.services.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.repository.SelectedRepository;
import com.mx.base.services.SelectedService;

@Service
@Transactional(readOnly = false)
public class SelectedServiceImpl<T extends CatalogModel> implements SelectedService<T> {

	@Autowired
    private SelectedRepository repository;	
	
	@Override
	public Map<Long, String> getMapRows(Class<T> clazz) {

		Map<Long, String> mapRows;
		mapRows = repository.getMapRows(clazz);
		return mapRows;
		
	}
	

}
