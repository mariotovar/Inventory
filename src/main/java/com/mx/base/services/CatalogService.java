package com.mx.base.services;

import java.util.List;

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.abstractions.CatalogModelList;
import com.mx.base.navegation.CtrlPage;

public interface CatalogService<T extends CatalogModel> {
		
	public int getNumberOfRows(Class<T> clazz);
	public CatalogModel findRow(long pk, Class<T> clazz);
	public CatalogModel findRow(String value, Class<T> clazz);
	public void setListRows(CtrlPage page, Class<T> clazz);
	public List<CatalogModel> getListRows(Class<T> clazz);
	public void saveCatalog(CatalogModel catalog);
	public void saveListCatalog(CatalogModelList<T> catalogList);
	
}
