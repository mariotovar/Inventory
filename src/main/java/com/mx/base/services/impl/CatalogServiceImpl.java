package com.mx.base.services.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.abstractions.CatalogModelList;
import com.mx.base.abstractions.HandleCatalog;
import com.mx.base.navegation.CtrlPage;
import com.mx.base.repository.CatalogRepository;
import com.mx.base.services.CatalogService;

@Service
@Transactional(readOnly = false)
public class CatalogServiceImpl<T extends CatalogModel> implements CatalogService<T> {

	@Autowired
    private CatalogRepository repository;
	
		
	@Override
	public int getNumberOfRows(Class<T> clazz) {		
		int numberOfRows;
		numberOfRows = repository.getNumberOfRows(clazz);
		return numberOfRows;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public void setListRows(CtrlPage ctrlPage, Class<T> clazz) {
		List<CatalogModel> listRows;
		listRows = repository.getPageRows(clazz, ctrlPage);
		ctrlPage.setListRows(listRows);
	}

	
	@Override
	public List<CatalogModel> getListRows(Class<T> clazz) {

		//Change CatalogModel by T when list get by service core
		List<CatalogModel> listRows;
		listRows = repository.getListRows(clazz);//this.fillCatalogModels(clazz);
		return listRows;
		
	}
	
	//Temporal code example list
	//Change CatalogModel by T when list get by service core
	private List<CatalogModel> fillCatalogModels(Class<T> clazz){
		
		List<CatalogModel> listRows;
		listRows = new ArrayList<CatalogModel>();
		for(int i=0; i<=10; i++){
			listRows.add(HandleCatalog.newInstance(clazz.getSimpleName()));
		}
		
		return listRows;
		
	}

	@Override
	public void saveCatalog(CatalogModel catalog) {
		repository.saveCatalog(catalog);
	}


	@Override
	@SuppressWarnings("unchecked")
	public CatalogModel findRow(long pk, Class<T> clazz) {
		CatalogModel catalog = repository.findRow(pk, clazz);	
		return catalog;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public CatalogModel findRow(String value, Class<T> clazz) {
		CatalogModel catalog = repository.findRow(value, clazz);	
		return catalog;
		
	}

	@Override
	@SuppressWarnings("unchecked")
	public void saveListCatalog(CatalogModelList<T> catalogList) {
		repository.saveListCatalog(catalogList);		
	}

	
}
