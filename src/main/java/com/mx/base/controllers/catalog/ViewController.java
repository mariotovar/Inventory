package com.mx.base.controllers.catalog;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mx.base.abstractions.HandleView;
import com.mx.base.abstractions.ViewModel;
import com.mx.base.navegation.CtrlPage;

@Controller
@RequestMapping("/view")
public class ViewController {
	
	@Autowired
	HandleView handleView;	

	
    @RequestMapping(value = "/page/{beanName}/{numberOfPage}", method = RequestMethod.GET)
    public String showPage(	ModelMap model,
    					  	@PathVariable("beanName") String beanName, 
    					  	@PathVariable("numberOfPage") int numberOfPage
    					   ) {
    	    	
    	CtrlPage ctrlPage = handleView.getListRows(numberOfPage, beanName);    	
    	model.addAttribute("path", "view");
    	model.addAttribute("ctrlPage", ctrlPage);
    	model.addAttribute("beanName", beanName);
    	
        return "view".concat(StringUtils.capitalize(beanName));
    
    }		
	
    @RequestMapping(value = "/lst/{viewName}", method = RequestMethod.GET)
    public String showList(ModelMap model, @PathVariable("viewName") String viewName) {
    	
    	List<? extends ViewModel> listRows = handleView.getView(viewName);
    	    	
    	model.addAttribute("listRows", listRows);   
    	
        return "view".concat(StringUtils.capitalize(viewName));
    
    }	
    
    @RequestMapping(value = "/history/{viewName}", method = RequestMethod.GET)
    public String historyView(ModelMap model, @PathVariable("viewName") String viewName) {

    	int year = Calendar.getInstance().get(Calendar.YEAR);
    	
    	return "redirect:"+viewName+"/"+year;
    
    }     
    
    @RequestMapping(value = "/history/{viewName}/{year}", method = RequestMethod.GET)
    public String showListByYear(ModelMap model, 
    							 @PathVariable("viewName") String viewName,
    							 @PathVariable("year") int year) {
    	    	
       	int currentYear = Calendar.getInstance().get(Calendar.YEAR);
    	List<? extends ViewModel> listRows = handleView.getHistory(viewName, year);
    	    
    	model.addAttribute("year", year);
    	model.addAttribute("currentYear", currentYear);
    	model.addAttribute("action", "HISTORY");
    	model.addAttribute("listRows", listRows);   
    	
        return "view".concat(StringUtils.capitalize(viewName));
    
    }	
    
    
    @RequestMapping(value = "/detail/{viewName}", method = RequestMethod.GET)
    public String showDetail(ModelMap model, @PathVariable("viewName") String viewName) {
    	    	    	
        return "detail".concat(StringUtils.capitalize(viewName));
    
    }	    

}
