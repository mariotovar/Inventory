package com.mx.base.controllers.business;

import java.util.Calendar;
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
import com.mx.base.abstractions.HandleView;
import com.mx.base.abstractions.ViewModel;
import com.mx.base.models.catalog.Client;
import com.mx.base.models.catalog.Email;
import com.mx.base.models.catalog.Item;
import com.mx.base.models.catalog.Product;
import com.mx.base.models.catalog.QuoteOrder;
import com.mx.base.services.CatalogService;
import com.mx.base.services.QuoteOrderService;
import com.mx.base.util.functions.DateUtils;
import com.mx.base.util.response.JSONResponse;
import com.mx.base.util.response.PieceCondition;
import com.mx.base.util.response.StatusOrder;
import com.mx.base.util.response.StatusResponse;
import com.mx.base.util.services.EmailService;
import com.mx.base.util.velocity.QuoteOrderTemplate;

@Controller
@RequestMapping("/order")
@SessionAttributes("quoteOrder")
public class QuoteOrderController {
	
	@Autowired
	HandleView handleView;	
	
	@Autowired
	HandleComponent handleComponent;	
	
	@Autowired
	private CatalogService catalogService;	

	@Autowired
	private QuoteOrderService quoteOrderService;	
	
	@Autowired
	private EmailService emailService;			
	
	/*********************************************************************/
	/************************** QUOTE ORDER ******************************/
	/*********************************************************************/

	@RequestMapping(value = "/quote/start", method = RequestMethod.GET)
	public String loadMaps(HttpSession session) {

		handleComponent.loadSessionMap(session, Client.class);
		handleComponent.loadSessionMap(session, Product.class);

		return "redirect:client";

	}
	
	@RequestMapping(value = "/quote/client", method = RequestMethod.GET)
	public String quoteClient(ModelMap model) {

		model.addAttribute("quoteOrder", new QuoteOrder());

		return "quoteClient";

	}

	@RequestMapping(value = "/quote/client", method = RequestMethod.POST)
	public String submitQuoteClient(ModelMap model, QuoteOrder quoteOrder, RedirectAttributes redirect) {

		long pkClient = quoteOrder.getClient().getPk();
		Client client = (Client) catalogService.findRow(pkClient, Client.class);
		quoteOrder.setClient(client);
		
		redirect.addFlashAttribute("quoteOrder", quoteOrder);

		return "redirect:cart";

	}

	@RequestMapping(value = "/quote/cart", method = RequestMethod.GET)
	public String quoteCart(QuoteOrder quoteOrder, ModelMap model) {

		String redirect;
		redirect = "redirect:client";
		if (quoteOrder.getClient() != null && quoteOrder.getClient().getPk() != 0) {
			redirect = "quoteCart";
			model.addAttribute("quoteOrder", quoteOrder);
			model.addAttribute("conditions", PieceCondition.values());
		}

		return redirect;

	}
	
	@ResponseBody
	@RequestMapping(value = "/quote/items", method = RequestMethod.GET)
	public List<Item> getItems(QuoteOrder quoteOrder) {

		List<Item> items;
		items = quoteOrder.getItems();

		return items;

	}	
	
	
	@RequestMapping(value = "/quote/cart", method = RequestMethod.POST)
	public String submitQuoteCart(ModelMap model, QuoteOrder quoteOrder, RedirectAttributes redirect) {

		System.out.println("items: " + quoteOrder.getItems());
		
		quoteOrder.getItems().removeIf(item -> (item.getCostMXN() == 0));
		redirect.addFlashAttribute("quoteOrder", quoteOrder);

		return "redirect:checkout";

	}

	
	@RequestMapping(value = "/quote/checkout", method = RequestMethod.GET)
	public String quoteCheckout(ModelMap model, QuoteOrder quoteOrder) {

		String redirect;
		redirect = "redirect:client";
		if (quoteOrder.getItems() != null) {
			redirect = "quoteCheckout";
			quoteOrder.setQuoteDate(new Date());
			quoteOrder.setYear(DateUtils.getCurrentYear());
			model.addAttribute("quoteOrder", quoteOrder);
		}

		return redirect;

	}

	@RequestMapping(value = "/quote/checkout", method = RequestMethod.POST)
	public String submitQuoteCheckout(ModelMap model, QuoteOrder quoteOrder, RedirectAttributes redirect) {
		
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		quoteOrder.setUser(user);
		quoteOrder.setStatus(StatusOrder.NEW);
		
		quoteOrderService.saveQuoteOrder(quoteOrder);

		model.remove("quoteOrder");
	
		return "redirect:detail/" + quoteOrder.getYear() + "/" +quoteOrder.getPkQuote();

	}
	
	
	@RequestMapping(value = "/quote/detail/{year}/{pkQuote}", method = RequestMethod.GET)
	public String detailQuote(	ModelMap model, 
								@PathVariable("year") int year,
								@PathVariable("pkQuote") Long pkQuote) {

		QuoteOrder quoteOrder;		
		quoteOrder = quoteOrderService.findByIdQuoteOrder(pkQuote, year);
		
		model.addAttribute("quoteOrder", quoteOrder);

		return "detailQuoteOrder";

	}
	
	
	
	/********************************
	 * CANCEL QUOTE ORDER
	 ************************************/

	@RequestMapping(value = "/quote/cancel/{year}/{pkQuote}", method = RequestMethod.GET)
	public String cancelQuote(	ModelMap model, 
								@PathVariable("year") int year,
								@PathVariable("pkQuote") Long pkQuote) {

		QuoteOrder quoteOrder;		
		quoteOrder = quoteOrderService.findByIdQuoteOrder(pkQuote, year);

		model.addAttribute("action", "DELETE");
		model.addAttribute("quoteOrder", quoteOrder);

		return "detailQuoteOrder";

	}

	@RequestMapping(value = "/quote/cancel/{year}/{pkQuote}", method = RequestMethod.POST)
	public String submitCancelQuote(ModelMap model, QuoteOrder quoteOrder) {

		quoteOrder.setStatus(StatusOrder.CANCEL);
		quoteOrderService.deleteQuoteOrder(quoteOrder.getPkQuote(), quoteOrder.getYear());

		model.addAttribute("action", "READ");
		model.addAttribute("quoteOrder", quoteOrder);

		return "detailQuoteOrder";

	}
	
	
	/********************************
	 * UPDATE QUOTE ORDER
	 ************************************/

	@RequestMapping(value = "/quote/update/{year}/{pkQuote}", method = RequestMethod.GET)
	public String updateQuote(	ModelMap model, 
								HttpSession session,
								@PathVariable("year") int year,
								@PathVariable("pkQuote") Long pkQuote) {

		QuoteOrder quoteOrder;		
		quoteOrder = quoteOrderService.findByIdQuoteOrder(pkQuote, year);
		
		handleComponent.loadSessionMap(session, Product.class);

		model.addAttribute("quoteOrder", quoteOrder);

		return "redirect:../../cart";

	}

	
	/********************************
	 * HISTORY PURCHASE ORDER
	 ************************************/

	@RequestMapping(value = "/quote/history", method = RequestMethod.GET)
	public String historyQuote(ModelMap model) {

		int year = Calendar.getInstance().get(Calendar.YEAR);

		return "redirect:history/" + year;

	}

	@RequestMapping(value = "/quote/history/{year}", method = RequestMethod.GET)
	public String searchHistoryQuote(ModelMap model, @PathVariable("year") int year) {

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		List<? extends ViewModel> listRows = handleView.getHistory("quoteOrder", year);

		model.addAttribute("year", year);
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("action", "HISTORY");
		model.addAttribute("listRows", listRows);

		return "viewQuoteOrder";

	}
	

	/************************************************/
	/***********SEND MAIL QUOTE ORDER*****************/
	/************************************************/
	
	@ResponseBody
	@RequestMapping(value = "/quote/mailing", method = RequestMethod.GET)
	public JSONResponse sendMailQuoteOrder(ModelMap model, QuoteOrder quoteOrder) {

		JSONResponse response;
		response = new JSONResponse();
		
		try{
			Email email = new Email();
			email.setSubject("Quote Order");
			email.setTo(quoteOrder.getClient().getEmail());
			email.setBody(QuoteOrderTemplate.getQuoteOrderEmail(quoteOrder));
			emailService.sendEmail(email);	
		}
		catch(Exception ex){
			response.setStatus(StatusResponse.ERROR);
			ex.printStackTrace();
		}

		return response;

	}		
		
}
