package com.mx.base.repository;

import java.util.Map;

import com.mx.base.abstractions.CatalogModel;

public interface SelectedRepository<T extends CatalogModel> {

	Map<Long, String> getMapRows(Class<CatalogModel> clazz);

}
