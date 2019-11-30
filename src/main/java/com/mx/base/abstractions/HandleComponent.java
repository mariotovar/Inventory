package com.mx.base.abstractions;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.mx.base.services.SearchService;
import com.mx.base.services.SelectedService;

@Service
public class HandleComponent <T extends CatalogModel> {

	
	@Autowired
	private SearchService searchService;	

	@Autowired
	private SelectedService selectedService;		

	
	public Map<Long, String> getMapRows(Class<T> clazz) {

		Map<Long, String> mapRows;
		mapRows = selectedService.getMapRows(clazz);
		
		return mapRows;
		
	}	
	
	public void loadSessionMap(HttpSession session, Class<T> clazz){
		
		Map<Long, String> map = this.getMapRows(clazz);
		String nameMapRows = "map".concat(StringUtils.capitalize(clazz.getSimpleName()));
		session.setAttribute(nameMapRows, map);
		
	}
	
	
	public Map<Long, String> getMapRows(String beanName) {

		Map<Long, String> mapRows;
		mapRows = selectedService.getMapRows(HandleCatalog.clazz(beanName));
		
		return mapRows;
		
	}	

	public Map<Long, String> getMapRows(String beanName, String inputSearch) {

		Map<Long, String> mapRows;
		mapRows = searchService.getMapRows(HandleCatalog.clazz(beanName), inputSearch);
		return mapRows;
		
	}
	
	
	
	
}
