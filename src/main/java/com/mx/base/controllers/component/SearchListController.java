package com.mx.base.controllers.component;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.base.abstractions.CatalogModel;
import com.mx.base.abstractions.HandleCatalog;
import com.mx.base.abstractions.HandleComponent;
import com.mx.base.util.response.JSONCatalog;
import com.mx.base.util.response.JSONMapRows;
import com.mx.base.util.response.JSONResponse;
import com.mx.base.util.response.StatusResponse;

@Controller
@RequestMapping("/search")
public class SearchListController {
	
	@Autowired
	HandleCatalog handleCatalog;	
	
	@Autowired
	HandleComponent handleComponent;	

    @ResponseBody
    @RequestMapping(value = "/create/{beanName}", method = RequestMethod.GET)
    public JSONCatalog createRows(HttpSession session,
    					   @PathVariable("beanName") String beanName) {

    	System.out.println("cargando en session: " + beanName);
    	JSONCatalog response = new JSONCatalog();   
    	handleComponent.loadSessionMap(session, HandleCatalog.clazz(beanName));
    	
    	return response;
        
    }		
	
    @ResponseBody
    @RequestMapping(value = "/lst/{beanName}/{search}", method = RequestMethod.GET)
    public JSONMapRows listRows(HttpSession session,
    							@PathVariable("beanName") String beanName,
    							@PathVariable("search") String search) {
         
    	Map<Long, String> mapRows;    	
    	String nameMapRows = "map".concat(StringUtils.capitalize(beanName));
    	
    	if(session.getAttribute(nameMapRows)==null) {
    		handleComponent.loadSessionMap(session, HandleCatalog.clazz(beanName));
    	}    	

    	System.out.println("buscando en session: " + beanName);
    	mapRows = (Map<Long, String>) session.getAttribute(nameMapRows);
		mapRows.entrySet().stream().filter(entry -> entry.getValue().toLowerCase().contains(search.toLowerCase()));			

		JSONMapRows response = new JSONMapRows();
		response.setMapRows(mapRows);
		
		return response;
        
    }	
    
    @ResponseBody
    @RequestMapping(value = "/find/{beanName}/pk/{pk}", method = RequestMethod.GET)
    public JSONCatalog findRowByPK(ModelMap model, 
    						   	   @PathVariable("beanName") String beanName,
    						   	   @PathVariable("pk") long pk) {
         
    	JSONCatalog response = new JSONCatalog();       
        response.setCatalog(handleCatalog.findRow(pk, beanName));
        
        return response;
        
    }	
    
    @ResponseBody
    @RequestMapping(value = "/find/{beanName}/value/{value}", method = RequestMethod.GET)
    public JSONCatalog findRowByValue(ModelMap model, 
    						   		 @PathVariable("beanName") String beanName,
    						   		 @PathVariable("value") String value) {
         
    	JSONCatalog response = new JSONCatalog();  
    	CatalogModel catalog = handleCatalog.findRow(value, beanName);
    	response.setStatus(catalog!=null?StatusResponse.SUCCESS:StatusResponse.ERROR);
        response.setCatalog(catalog);
        
        return response;
        
    }	
    
    @ResponseBody
    @RequestMapping(value = "/find/{beanName}/duplicate/{pk}/{value}", method = RequestMethod.GET)
    public JSONResponse findRowDuplicateByValue(HttpSession session,
    											@PathVariable("beanName") String beanName,
    											@PathVariable("pk") long pk,
    											@PathVariable("value") String value) {
        
    	Map<Long, String> mapRows;
    	JSONResponse response = new JSONResponse();          
    	String nameMapRows = "map".concat(StringUtils.capitalize(beanName));            	    	
    	mapRows = (Map<Long, String>) session.getAttribute(nameMapRows);
    	
    	if(mapRows!=null){
    		if(pk==0 && mapRows.containsValue(value)) {
    			response.setStatus(StatusResponse.ERROR);
    		}
    		else if(pk!=0 && !mapRows.get(pk).equalsIgnoreCase(value)) {
    			if(mapRows.containsValue(value)) {
    				response.setStatus(StatusResponse.ERROR);
    			}
    		}    		
    	}
    	
        return response;
        
    }	    
    
}
