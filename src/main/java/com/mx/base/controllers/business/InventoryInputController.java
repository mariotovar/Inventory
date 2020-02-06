package com.mx.base.controllers.business;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
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
import com.mx.base.models.catalog.InventoryInput;
import com.mx.base.models.catalog.InventoryInputRow;
import com.mx.base.models.catalog.Product;
import com.mx.base.models.catalog.TicketPDF;
import com.mx.base.services.InventoryInputService;
import com.mx.base.services.impl.TicketPurchasePDF;
import com.mx.base.util.functions.DateUtils;
import com.mx.base.util.response.PieceCondition;

@Controller
@RequestMapping("/inventory")
@SessionAttributes("inventoryInput")
public class InventoryInputController {

	@Autowired
	InventoryInputService inventoryInputService;
	
	@Autowired
	HandleComponent handleComponent;		

	@Autowired
	private TicketPurchasePDF ticketPurchasePDF;	
	
	@RequestMapping(value = "/input/new", method = RequestMethod.GET)
	public String inputNew(HttpSession session, InventoryInput inventoryInput, ModelMap model) {

		handleComponent.loadSessionMap(session, Product.class);
		
		model.addAttribute("inventoryInput", new InventoryInput());

		return "redirect:cart";

	}

	@RequestMapping(value = "/input/cart", method = RequestMethod.GET)
	public String InputCart(InventoryInput inventoryInput, ModelMap model) {

		model.addAttribute("inventoryInput", inventoryInput);
		model.addAttribute("conditions", PieceCondition.values());
		
		return "inputCart";

	}

	@ResponseBody
	@RequestMapping(value = "/input/rows", method = RequestMethod.GET)
	public List<InventoryInputRow> getRows(InventoryInput inventoryInput) {

		List<InventoryInputRow> rows;
		rows = inventoryInput.getRows();

		return rows;

	}

	@RequestMapping(value = "/input/cart", method = RequestMethod.POST)
	public String submitInputCart(ModelMap model, InventoryInput inventoryInput, RedirectAttributes redirect) {

		inventoryInput.getRows().removeIf(row -> (row.getQty() == 0));
		redirect.addFlashAttribute("inventoryInput", inventoryInput);

		return "redirect:checkout";

	}

	@RequestMapping(value = "/input/checkout", method = RequestMethod.GET)
	public String inputCheckout(ModelMap model, InventoryInput inventoryInput) {

		String redirect;
		redirect = "redirect:cart";
		if (inventoryInput.getRows() != null) {
			redirect = "inputCheckout";
			inventoryInput.setInventoryDate(new Date());
			model.addAttribute("inventoryInput", inventoryInput);
		}

		return redirect;

	}

	@RequestMapping(value = "/input/checkout", method = RequestMethod.POST)
	public String submitInputCheckout(ModelMap model, InventoryInput inventoryInput) {

		inventoryInput.setYear(DateUtils.getCurrentYear());
		inventoryInput.setUser(SecurityContextHolder.getContext().getAuthentication().getName());
		inventoryInputService.saveInventoryInput(inventoryInput);

		model.remove("inventoryInput");

		return "redirect:detail/" + inventoryInput.getYear()+"/"+inventoryInput.getPk();

	}

	/********************************
	 * DETAIL INPUT ORDER
	 ************************************/

	@RequestMapping(value = "/input/detail/{year}/{pkInput}", method = RequestMethod.GET)
	public String detailInput(
								ModelMap model, 
							 	@PathVariable("year") int year,
								@PathVariable("pkInput") long pkInput
							) {

		InventoryInput input;
		input = inventoryInputService.getInventoryInputById(year, pkInput);

		model.addAttribute("inventoryInput", input);

		return "detailInputOrder";

	}
	
	/*************************************
	 * TICKET PURCHASE ORDER
	 ************************************/
	@ResponseBody
	@RequestMapping(value = "/input/printing/{ticket}", method = RequestMethod.GET)
	public void printTicket(ModelMap model, 
							HttpServletResponse response,
							InventoryInput inventoryInput, 
							@PathVariable("ticket") long ticket) {

		int year = inventoryInput.getYear();
		System.out.println("size: " + inventoryInput.getRows());
		for (InventoryInputRow row : inventoryInput.getRows()) {
			System.out.println("pk: " + row.getPk());
			if(row.getPk()==ticket){
				TicketPDF ticketPDF = new TicketPDF();
				/*ticketPDF.setYear(year);
				ticketPDF.setQty(row.getQty());
				ticketPDF.setValue(row.getValue());
				ticketPDF.setLotNumber(row.getLotNumber());
				*/
				ticketPDF.setDescription(row.getDescription());
				SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
			//	ticketPDF.setReceive(formatter.format(item.getInputs().get(0).getReceiveDate()));
			//	ticketPDF.setPurchseOrder(purchaseOrder.getOrderNumber());
		//		ticketPDF.setTitle(purchaseOrder.getProvider().getValue());
			//	ticketPDF.setPartNumber(item.getValue());
				//ticketPDF.setDescription(item.getDescription());
				//ticketPDF.setQty(item.getQty());
				//ticketPDF.setValue(item.getValue());
				//ticketPDF.setLoc(item.getProduct().getBin());
				//ticketPDF.setLotNumber(item.getInputs().get(0).getLotNumber());		
				//ticketPDF.setTitle(purchaseOrder.getProvider().getValue());
				
				ticketPDF.setTitle("N/A");
				ticketPDF.setReceive(formatter.format(inventoryInput.getInventoryDate()));
				ticketPDF.setPartNumber(row.getValue());
				ticketPDF.setPurchseOrder(""+inventoryInput.getOrderNumber());					
				//ticketPDF.setLoc(item.getProduct().getBin());
				ticketPDF.setDescription(row.getDescription());
				ticketPDF.setQty(row.getQty());
				ticketPDF.setValue(row.getValue());		
				ticketPDF.setLotNumber(row.getLotNumber());
				ticketPDF.setYear(year);
				
				
				ticketPurchasePDF.makeTicketPDF(ticketPDF, year);	
				ticketPurchasePDF.download(response,ticketPDF, year);	
			}
		}

	}	

}
