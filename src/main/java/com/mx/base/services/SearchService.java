package com.mx.base.services;

import java.util.Map;

import com.mx.base.abstractions.CatalogModel;

public interface SearchService<T extends CatalogModel> {
	
	public Map<Long, String> getMapRows(Class<T> clazz, String inputSearch);

}
