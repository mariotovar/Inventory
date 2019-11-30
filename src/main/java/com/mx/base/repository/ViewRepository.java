package com.mx.base.repository;

import java.util.List;

import com.mx.base.abstractions.ViewModel;

public interface ViewRepository<T extends ViewModel> {
	
	int getNumberOfRows(Class<T> clazz);
	List<ViewModel> getView(Class<T> clazz);
	List<ViewModel> getHistory(Class<T> clazz, int year);
		
}
