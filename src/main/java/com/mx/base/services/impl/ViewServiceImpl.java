package com.mx.base.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.base.abstractions.ViewModel;
import com.mx.base.navegation.CtrlPage;
import com.mx.base.repository.ViewRepository;
import com.mx.base.services.ViewService;

@Service
public class ViewServiceImpl<T extends ViewModel> implements ViewService<T> {
	
	@Autowired
    private ViewRepository<T> repository;	
	
	@Override
	public List<ViewModel> getView(Class<T> clazz) {

		List<ViewModel> listRows;
		listRows = repository.getView(clazz);
		return listRows;
		
	}

	@Override
	public List<ViewModel> getHistory(Class<T> clazz, int year) {

		List<ViewModel> listRows;
		listRows = repository.getHistory(clazz, year);
		return listRows;
		
	}	

	@Override
	public int getNumberOfRows(Class<T> clazz) {		
		int numberOfRows;
		numberOfRows = repository.getNumberOfRows(clazz);
		return numberOfRows;
	}

	@Override
	public void setListRows(CtrlPage ctrlPage, Class<T> clazz) {
		List<ViewModel> listRows;
		listRows = repository.getView(clazz);
		ctrlPage.setListRows(listRows);
	}

	
}
