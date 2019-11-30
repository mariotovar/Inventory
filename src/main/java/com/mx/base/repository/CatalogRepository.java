package com.mx.base.repository;

import java.util.List;

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.abstractions.CatalogModelList;
import com.mx.base.navegation.CtrlPage;

public interface CatalogRepository<T extends CatalogModel> {

	public int getNumberOfRows(Class<CatalogModel> clazz);
	public CatalogModel findRow(long pk, Class<CatalogModel> clazz);
	public CatalogModel findRow(String value, Class<CatalogModel> clazz);
	public List<CatalogModel> getListRows(Class<CatalogModel> clazz);
	public List<CatalogModel> getPageRows(Class<CatalogModel> clazz, CtrlPage ctrlPage);
	public void saveCatalog(CatalogModel catalog);
	public void saveListCatalog(CatalogModelList<T> catalogList);
}
