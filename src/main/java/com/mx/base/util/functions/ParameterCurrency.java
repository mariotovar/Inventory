package com.mx.base.util.functions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.base.models.entity.ParameterValueEntity;
import com.mx.base.services.ParameterService;

@Component
public class ParameterCurrency {
	
	@Autowired
	private ParameterService parameterService;


	@SuppressWarnings("unchecked")
	public double getFactorConvertion(){

		double factorConvertion = 0;		
		
		List<ParameterValueEntity> lstParameters;
		lstParameters = (List<ParameterValueEntity>) parameterService.getParametersByCode("currency");
		for (ParameterValueEntity param : lstParameters) {
			if(param.getValue().equalsIgnoreCase("FACTOR_CONVERTION")){
				factorConvertion = Double.parseDouble(param.getDescription());
			}
		}
		
		return factorConvertion;
	
	}
	
	@SuppressWarnings("unchecked")
	public double getFactorIVA(){

		double factorIVA = 0;		
		
		List<ParameterValueEntity> lstParameters;
		lstParameters = (List<ParameterValueEntity>) parameterService.getParametersByCode("currency");
		for (ParameterValueEntity param : lstParameters) {
			if(param.getValue().equalsIgnoreCase("IVA")){
				factorIVA = Double.parseDouble(param.getDescription());
			}
		}
		
		return factorIVA;
	
	}	

}
