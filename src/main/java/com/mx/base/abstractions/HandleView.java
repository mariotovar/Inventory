package com.mx.base.abstractions;

import java.lang.reflect.Constructor;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mx.base.navegation.CtrlPage;
import com.mx.base.services.ViewService;

@Service
public class HandleView {

	@Autowired
	private ViewService viewService;	
	
	
	public static ViewModel newInstance(String viewName){
		try {
			final String CATALOG_PACKAGE = "com.mx.base.models.view.";
			String modelName = StringUtils.capitalize(viewName);
			Class<?> c = Class.forName(CATALOG_PACKAGE.concat(modelName));
			Constructor<?> cons = c.getConstructor();
			ViewModel viewModel = (ViewModel) cons.newInstance();	
			return viewModel;			
		} catch (Exception e) {			
			e.printStackTrace();
			return null;		
		}
	}		
	
	public static Class<? extends ViewModel> clazz(String beanName){
		return HandleView.newInstance(beanName).getClass();
	}
		
	public CtrlPage getListRows(int numberOfPage, String beanName){

		Class beanClass = HandleView.clazz(beanName);
		int numberOfRows = viewService.getNumberOfRows(beanClass);
		CtrlPage ctrlPage = new CtrlPage(numberOfPage, numberOfRows);
		viewService.setListRows(ctrlPage, beanClass);
		
		return ctrlPage;
		
	}	
	
	public List<? extends ViewModel> getView(String viewName){
		
		List<? extends ViewModel> listRows;
		listRows = viewService.getView(HandleView.clazz(viewName));
		return listRows;
		
	}

	public List<? extends ViewModel> getHistory(String viewName, int year){
		
		List<? extends ViewModel> listRows;
		listRows = viewService.getHistory(HandleView.clazz(viewName), year);
		return listRows;
		
	}
	
}
