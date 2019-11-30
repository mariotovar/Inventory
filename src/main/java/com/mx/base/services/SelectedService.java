package com.mx.base.services;

import java.util.Map;

import com.mx.base.abstractions.CatalogModel;

public interface SelectedService <T extends CatalogModel> {
	
	Map<Long, String> getMapRows(Class<T> clazz);

}
