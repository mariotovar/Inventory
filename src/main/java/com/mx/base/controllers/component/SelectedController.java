package com.mx.base.controllers.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mx.base.abstractions.HandleComponent;
import com.mx.base.util.response.JSONMapRows;

@Controller
@RequestMapping("/select")
public class SelectedController {
	
	@Autowired
	HandleComponent handleComponent;		

    @ResponseBody
    @RequestMapping(value = "/lst/{beanName}", method = RequestMethod.GET)
    public JSONMapRows findRow(ModelMap model, 
    						   @PathVariable("beanName") String beanName) {
         
    	JSONMapRows response = new JSONMapRows();       
        response.setMapRows(handleComponent.getMapRows(beanName));
        
        return response;
        
    }	
    

}
