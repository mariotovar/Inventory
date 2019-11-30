package com.mx.base.controllers.business;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mx.base.abstractions.HandleComponent;
import com.mx.base.models.catalog.InventoryOutput;
import com.mx.base.models.catalog.Item;
import com.mx.base.models.catalog.Product;
import com.mx.base.services.OutPutInventoryService;
import com.mx.base.util.functions.DateUtils;

@Controller
@RequestMapping("/inventory")
@SessionAttributes("inventoryOutput")
public class InventoryOutputController {

	@Autowired
	HandleComponent handleComponent;
	
	@Autowired
	private OutPutInventoryService outPutInventoryService;

    @RequestMapping(value = "/output/new", method = RequestMethod.GET)
    public String outputNew(HttpSession session, InventoryOutput inventoryOutput, ModelMap model) {
 	    	
    	handleComponent.loadSessionMap(session, Product.class);
    	model.addAttribute("inventoryOutput", new InventoryOutput());   
  
    	
    	return "redirect:cart";
    
    }    	
	
    @RequestMapping(value = "/output/cart", method = RequestMethod.GET)
    public String outputCart(InventoryOutput inventoryOutput, ModelMap model) {
 	
    	model.addAttribute("inventoryOutput", inventoryOutput);    	
    	
        return "outputCart";
    
    }    
    
    
    @ResponseBody
    @RequestMapping(value = "/output/items", method = RequestMethod.GET)
    public List<Item> getItems(InventoryOutput inventoryOutput) {         
    	
    	List<Item> items;
    	items = inventoryOutput.getItems();
    	
        return items;       
    
    }    
    

    
    @RequestMapping(value = "/output/cart", method = RequestMethod.POST)
    public String submitOutputCart(ModelMap model, 
    						      InventoryOutput inventoryOutput,
    							  RedirectAttributes redirect) {
    	
    	for(Item item: inventoryOutput.getItems()){   
    		item.getInputs().removeIf(input -> (input.getOutputs().isEmpty()));
    		item.getInputs().removeIf(input -> (!input.getOutputs().isEmpty() && input.getOutputs().get(0).getCostMXN() == 0));
    	}
    	inventoryOutput.getItems().removeIf(item -> (item.getInputs().size()==0));

    	redirect.addFlashAttribute("inventoryOutput", inventoryOutput);
    	
        return "redirect:checkout";
    
    }	    
    
    @RequestMapping(value = "/output/checkout", method = RequestMethod.GET)
    public String outputCheckout(ModelMap model, InventoryOutput inventoryOutput) {

    	String redirect;
    	redirect= "redirect:cart";
    	if(inventoryOutput.getItems()!=null){    		
    		redirect = "outputCheckout";
    		inventoryOutput.setInventoryDate(new Date());
        	model.addAttribute("inventoryOutput", inventoryOutput);
    	}	
    	
        return redirect;
    
    }	
    
    @RequestMapping(value = "/output/checkout", method = RequestMethod.POST)
    public String submitOutputCheckout(ModelMap model, InventoryOutput inventoryOutput) {
    		
    	inventoryOutput.setYear(DateUtils.getCurrentYear());
		inventoryOutput.setUser(SecurityContextHolder.getContext().getAuthentication().getName());
		outPutInventoryService.saveOutPut(inventoryOutput);
	
    	model.remove("inventoryOutput");
    	
        return "redirect:detail/"+ inventoryOutput.getYear()+"/"+inventoryOutput.getPk();
    
    }	
    
    /********************************DETAIL OUTPUT ORDER************************************/
    
    @RequestMapping(value = "/output/detail/{year}/{pkOutput}", method = RequestMethod.GET)
    public String detailOutput(
								ModelMap model, 
							 	@PathVariable("year") int year,
								@PathVariable("pkOutput") long pkOutput
							) {


    	InventoryOutput output;
    	output = (InventoryOutput)outPutInventoryService.getInventoryOutPut(year, pkOutput);
   
    	model.addAttribute("inventoryOutput", output);
    	
        return "detailOutputOrder";
    
    }
        
    
}
