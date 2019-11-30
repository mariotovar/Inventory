package com.mx.base.repository;

import java.util.List;

public interface ParameterRespository {
	
	Object getParametersByCode(String code);
	
	public void updateParametersByCode(Object lst) ;

}
