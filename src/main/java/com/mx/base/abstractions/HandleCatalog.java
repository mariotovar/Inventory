package com.mx.base.abstractions;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mx.base.navegation.CtrlPage;
import com.mx.base.services.CatalogService;

@Service
public class HandleCatalog {

	@Autowired
	private CatalogService catalogService;	
	
	
	public static CatalogModel newInstance(String beanName){
		try {
			final String CATALOG_PACKAGE = "com.mx.base.models.catalog.";
			String modelName = StringUtils.capitalize(beanName);
			Class<?> c = Class.forName(CATALOG_PACKAGE.concat(modelName));
			Constructor<?> cons = c.getConstructor();
			CatalogModel catalogModel = (CatalogModel) cons.newInstance();	
			return catalogModel;			
		} catch (Exception e) {			
			e.printStackTrace();
			return null;		
		}
	}		
	
	public static Class<? extends CatalogModel> clazz(String beanName){
		return HandleCatalog.newInstance(beanName).getClass();
	}
	
	
	public CtrlPage getListRows(int numberOfPage, String beanName){

		Class beanClass = HandleCatalog.clazz(beanName);
		int numberOfRows = catalogService.getNumberOfRows(beanClass);
		CtrlPage ctrlPage = new CtrlPage(numberOfPage, numberOfRows);
		catalogService.setListRows(ctrlPage, beanClass);
		
		return ctrlPage;
		
	}
	
	public CtrlPage getRow(long pk, String beanName){

		Class<? extends CatalogModel> clazz = HandleCatalog.clazz(beanName);
		CatalogModel catalogModel = catalogService.findRow(pk, clazz);
		
		List<CatalogModel> listRow = new ArrayList<CatalogModel>();
		listRow.add(catalogModel);
		
		CtrlPage ctrlPage = new CtrlPage(1, 1);
		ctrlPage.setListRows(listRow);
		
		return ctrlPage;
		
	}	
	
	public List<? extends CatalogModel> getListRows(String beanName){
		
		List<? extends CatalogModel> listRows;
		listRows = catalogService.getListRows(HandleCatalog.clazz(beanName));
		return listRows;
		
	}
	
	
	public void saveCatalog(CatalogModel catalog){
		catalogService.saveCatalog(catalog);		
	}

	public void saveListCatalog(CatalogModelList catalogList) {
		catalogService.saveListCatalog(catalogList);		
	}		
	
	
	
	public CatalogModel findRow(long pk, String beanName){
		
		CatalogModel catalog;
		catalog = catalogService.findRow(pk, HandleCatalog.clazz(beanName));
		return catalog;
		
	}	

	public CatalogModel findRow(String value, String beanName){
		
		CatalogModel catalog;
		catalog = catalogService.findRow(value, HandleCatalog.clazz(beanName));
		return catalog;
		
	}	
	

	
}
