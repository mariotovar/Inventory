package com.mx.base.controllers.catalog;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.FieldError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.abstractions.HandleCatalog;
import com.mx.base.abstractions.HandleComponent;
import com.mx.base.navegation.CtrlPage;
import com.mx.base.util.functions.ParameterCurrency;
import com.mx.base.util.response.JSONResponse;
import com.mx.base.util.response.StatusAction;
import com.mx.base.util.response.StatusResponse;

@Controller
@RequestMapping("/catalog")
public class CatalogController {
	
	@Autowired
	Validator validator;
	
	@Autowired
	HandleCatalog handleCatalog;
	
	@Autowired
	HandleComponent handleComponent;	
	
	@Autowired
	private ParameterCurrency parameterCurrency;
	

    @RequestMapping(value = "/page/{beanName}/{numberOfPage}", method = RequestMethod.GET)
    public String showPage(	ModelMap model,
    						HttpSession session,
    					  	@PathVariable("beanName") String beanName, 
    					  	@PathVariable("numberOfPage") int numberOfPage
    					   ) {
    	    	
    	CtrlPage ctrlPage = handleCatalog.getListRows(numberOfPage, beanName);   
    	model.addAttribute("path", "catalog");
    	model.addAttribute("ctrlPage", ctrlPage);
    	model.addAttribute("beanName", beanName);
    	
    	handleComponent.loadSessionMap(session, HandleCatalog.clazz(beanName));    	
    	
        return "lst".concat(StringUtils.capitalize(beanName));
    
    }	    
    
    @RequestMapping(value = "/single/{beanName}/{pk}", method = RequestMethod.GET)
    public String showOneRow(	ModelMap model,
    					  		@PathVariable("beanName") String beanName, 
    					  		@PathVariable("pk") int pk
    					   	) {
    	    	
    	CtrlPage ctrlPage = handleCatalog.getRow(pk, beanName);   
    	model.addAttribute("path", "catalog");
    	model.addAttribute("ctrlPage", ctrlPage);
    	model.addAttribute("beanName", beanName);
    	
        return "lst".concat(StringUtils.capitalize(beanName));
    
    }	        

    @RequestMapping(value = "/lst/{beanName}", method = RequestMethod.GET)
    public String showList(	ModelMap model,	@PathVariable("beanName") String beanName) {
    	    	
    	List<? extends CatalogModel> listRows = handleCatalog.getListRows(beanName);
    	
    	model.addAttribute("listRows", listRows);
    	
        return "lst".concat(StringUtils.capitalize(beanName));
    
    }	
    

    @RequestMapping(value = "/form/{beanName}", method = RequestMethod.GET)
    public String showNewForm(ModelMap model,  @PathVariable("beanName") String beanName) {

    	CatalogModel catalog;
    	catalog = HandleCatalog.newInstance(beanName);
    	catalog.setStatus('A');
    	catalog.setUser(""+SecurityContextHolder.getContext().getAuthentication().getName());
    	model.addAttribute(beanName, catalog);  
    	model.addAttribute("factorConvertion", parameterCurrency.getFactorConvertion());
    
        return "form".concat(StringUtils.capitalize(beanName));
    
    }    
    
    
    @RequestMapping(value = "/form/{beanName}/{pk}", method = RequestMethod.GET)
    public String showExistForm(ModelMap model, 
								@PathVariable("beanName") String beanName,
								@PathVariable("pk") long pk) {
    	CatalogModel catalog;
    	catalog = handleCatalog.findRow(Math.abs(pk), beanName);
    	catalog.setUser(""+SecurityContextHolder.getContext().getAuthentication().getName());
    	catalog.setStatus(pk>0?'A':'B');
    	
    	StatusAction action;
    	action = pk>0?StatusAction.UPDATE:StatusAction.DELETE;

    	model.addAttribute("action", action);    
    	model.addAttribute(beanName, catalog);
    	model.addAttribute("factorConvertion", parameterCurrency.getFactorConvertion());
	
        return "form".concat(StringUtils.capitalize(beanName));    
    }       
  
    @RequestMapping(value = "/read/{beanName}/{pk}", method = RequestMethod.GET)
    public String readForm(ModelMap model, 
								@PathVariable("beanName") String beanName,
								@PathVariable("pk") long pk) {
    	    	        
    	CatalogModel catalog;
    	catalog = handleCatalog.findRow(pk, beanName);
    	model.addAttribute(beanName, catalog);
    	model.addAttribute("action", StatusAction.READ);    	
        return "form".concat(StringUtils.capitalize(beanName));
    
    }      
    
 
    @ResponseBody
    @RequestMapping(value = "/form/{beanName}", method = RequestMethod.POST)
    public JSONResponse submitNew(CatalogModel catalog, ModelMap model) {
    	
    	System.out.println("catalog: " + catalog);
    	
        JSONResponse response;
        response = this.dataValidation(catalog);
        return response;        
    }
    
    
    @ResponseBody
    @RequestMapping(value = "/form/{beanName}/{pk}", method = RequestMethod.POST)
    public JSONResponse submitExist(CatalogModel catalog, ModelMap model) {         
    	
    	System.out.println("catalog: " + catalog);
    	
        JSONResponse response;
        response = this.dataValidation(catalog);
        return response;       
    }    
    
    
    public JSONResponse dataValidation(CatalogModel catalog){
    
    	DataBinder dataBinder = new DataBinder(catalog);
    	dataBinder.setValidator(validator);
        dataBinder.validate();
        
        JSONResponse response = new JSONResponse();
        BindingResult result = dataBinder.getBindingResult();	
        if(result.hasErrors()){
        	response.setStatus(StatusResponse.ERROR);
        	for(FieldError error : result.getFieldErrors()){
        		System.out.println("error: " + error);
        		response.getErrors().put(error.getField(), error.getDefaultMessage());
        	}
        }
        else{
        	handleCatalog.saveCatalog(catalog);
        }
            	
        return response;    	
    	
    }
    
    
}
