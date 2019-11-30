package com.mx.base.repository;

import java.util.Map;

import com.mx.base.abstractions.CatalogModel;

public interface SearchRepository<T extends CatalogModel> {

	Map<Long, String> getMapRows(Class<CatalogModel> clazz, String inputSearch);
	
}
