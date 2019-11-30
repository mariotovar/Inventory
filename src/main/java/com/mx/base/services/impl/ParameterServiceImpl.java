package com.mx.base.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.base.repository.ParameterRespository;
import com.mx.base.services.ParameterService;

@Service
public class ParameterServiceImpl implements ParameterService{
	
	@Autowired
	private ParameterRespository parameterRepository;

	@Override
	public Object getParametersByCode(String code) {
		// TODO Auto-generated method stub
		return parameterRepository.getParametersByCode(code);
	}

	@Override
	public void updateParametersByCode(Object lst) {
		// TODO Auto-generated method stub
		parameterRepository.updateParametersByCode(lst);
		
	}
	

}
