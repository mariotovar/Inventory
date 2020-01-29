package com.mx.base.util.functions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mx.base.models.entity.ParameterValueEntity;
import com.mx.base.services.ParameterService;

@Component
public class ParameterConfig {
	
	@Autowired
	private ParameterService parameterService;


	@SuppressWarnings("unchecked")
	public String getCCEmail(){

		String ccEmail = null;		
		
		List<ParameterValueEntity> lstParameters;
		lstParameters = (List<ParameterValueEntity>) parameterService.getParametersByCode("config");
		for (ParameterValueEntity param : lstParameters) {
			if(param.getValue().equalsIgnoreCase("CC_EMAIL")){
				ccEmail = param.getDescription();
			}
		}
		
		return ccEmail;
	
	}
	
	@SuppressWarnings("unchecked")
	public String getTermsCondition(){

		String termsCondition = null;		
		
		List<ParameterValueEntity> lstParameters;
		lstParameters = (List<ParameterValueEntity>) parameterService.getParametersByCode("config");
		for (ParameterValueEntity param : lstParameters) {
			if(param.getValue().equalsIgnoreCase("TERMS_CONDITIONS")){
				termsCondition = param.getDescription();
			}
		}
		
		return termsCondition;
	
	}	

}
