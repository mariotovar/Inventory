package com.mx.base.controllers.catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mx.base.abstractions.HandleCatalog;

@Controller
@RequestMapping("/catalog")
public class CatalogListController {

	@Autowired
	HandleCatalog handleModel;
	
    @RequestMapping(value = "/rows/{beanName}", method = RequestMethod.GET)
    public String showList(	ModelMap model,	@PathVariable("beanName") String beanName) {
    	    	
    	//List<? extends CatalogModel> listRows = handleModel.getListRows(beanName);
    	
    	//model.addAttribute("listRows", listRows);
    	
        return "rows".concat(StringUtils.capitalize(beanName));
    
    }		
	
	
}
