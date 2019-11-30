package com.mx.base.services;

import java.util.List;

import com.mx.base.abstractions.ViewModel;
import com.mx.base.navegation.CtrlPage;

public interface ViewService <T extends ViewModel> {
	
	public int getNumberOfRows(Class<T> clazz);
	public List<ViewModel> getView(Class<T> clazz);
	public List<ViewModel> getHistory(Class<T> clazz, int year);
	public void setListRows(CtrlPage page, Class<T> clazz);

}
