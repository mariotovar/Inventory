package com.mx.base.controllers.business;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mx.base.abstractions.HandleComponent;
import com.mx.base.abstractions.HandleView;
import com.mx.base.abstractions.ViewModel;
import com.mx.base.models.catalog.Client;
import com.mx.base.models.catalog.Email;
import com.mx.base.models.catalog.Item;
import com.mx.base.models.catalog.Payment;
import com.mx.base.models.catalog.Product;
import com.mx.base.models.catalog.SaleOrder;
import com.mx.base.services.CatalogService;
import com.mx.base.services.InventoryLotService;
import com.mx.base.services.SaleOrderService;
import com.mx.base.util.functions.DateUtils;
import com.mx.base.util.response.JSONResponse;
import com.mx.base.util.response.StatusOrder;
import com.mx.base.util.response.StatusResponse;
import com.mx.base.util.services.EmailService;
import com.mx.base.util.velocity.SaleOrderNoteTemplate;
import com.mx.base.util.velocity.SaleOrderTemplate;

@Controller
@RequestMapping("/order")
@SessionAttributes("saleOrder")
public class SaleOrderController {

	private String fileFormat = "pdf";
	private static String filePathToBeServed = "/home/milla/sales/payments/";

	
	@Autowired
	HandleView handleView;

	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private InventoryLotService inventoryLotService;

	@Autowired
	HandleComponent handleComponent;		
	
	@Autowired
	private SaleOrderService saleOrderService;

	@Autowired
	private EmailService emailService;

	/************************** SALE ORDER ******************************/
	

	@RequestMapping(value = "/sale/start", method = RequestMethod.GET)
	public String loadMaps(HttpSession session) {

		handleComponent.loadSessionMap(session, Client.class);
		handleComponent.loadSessionMap(session, Product.class);

		return "redirect:client";

	}	

	@RequestMapping(value = "/sale/client", method = RequestMethod.GET)
	public String saleClient(ModelMap model) {

		model.addAttribute("saleOrder", new SaleOrder());

		return "saleClient";

	}

	@RequestMapping(value = "/sale/client", method = RequestMethod.POST)
	public String submitSaleClient(ModelMap model, SaleOrder saleOrder, RedirectAttributes redirect) {

		long pkClient = saleOrder.getClient().getPk();
		Client client = (Client) catalogService.findRow(pkClient, Client.class);

		saleOrder.setClient(client);
		redirect.addFlashAttribute("saleOrder", saleOrder);

		return "redirect:cart";

	}

	@RequestMapping(value = "/sale/cart", method = RequestMethod.GET)
	public String saleCart(SaleOrder saleOrder, ModelMap model) {

		String redirect;
		redirect = "redirect:client";
		if (saleOrder.getClient() != null && saleOrder.getClient().getPk() != 0) {
			redirect = "saleCart";
			model.addAttribute("saleOrder", saleOrder);
		}

		return redirect;

	}

	@ResponseBody
	@RequestMapping(value = "/sale/lots/{pkProduct}", method = RequestMethod.GET)
	public Item getLots(@PathVariable("pkProduct") long pkProduct) {

		Item item = null;
		item = inventoryLotService.getAllInventoryLotByProduct(pkProduct);
		return item;

	}

	@ResponseBody
	@RequestMapping(value = "/sale/items", method = RequestMethod.GET)
	public List<Item> getItems(SaleOrder saleOrder) {

		List<Item> items;
		items = saleOrder.getItems();

		return items;

	}

	@RequestMapping(value = "/sale/cart", method = RequestMethod.POST)
	public String submitSaleCart(ModelMap model, SaleOrder saleOrder, RedirectAttributes redirect) {

		for (Item item : saleOrder.getItems()) {
			item.getInputs().removeIf(input -> (input.getOutputs().isEmpty()));
			item.getInputs().removeIf(input -> (!input.getOutputs().isEmpty() && input.getOutputs().get(0).getCostMXN() == 0));
		}
		saleOrder.getItems().removeIf(item -> (item.getInputs().size() == 0));

		redirect.addFlashAttribute("saleOrder", saleOrder);

		return "redirect:shippingCost";

	}

	@RequestMapping(value = "/sale/shippingCost", method = RequestMethod.GET)
	public String saleShippingCost(ModelMap model, SaleOrder saleOrder) {

		String redirect;
		redirect = "redirect:client";
		if (saleOrder.getItems() != null) {
			redirect = "saleShippingCost";
			model.addAttribute("saleOrder", saleOrder);
		}

		return redirect;

	}

	@RequestMapping(value = "/sale/shippingCost", method = RequestMethod.POST)
	public String submitSaleShippingCost(ModelMap model, SaleOrder saleOrder, RedirectAttributes redirect) {

		redirect.addFlashAttribute("saleOrder", saleOrder);

		return "redirect:checkout";

	}

	@RequestMapping(value = "/sale/checkout", method = RequestMethod.GET)
	public String saleCheckout(ModelMap model, SaleOrder saleOrder) {

		String redirect;
		redirect = "redirect:client";
		if (saleOrder.getItems() != null) {
			redirect = "saleCheckout";
			saleOrder.setSaleDate(new Date());
			saleOrder.setYear(DateUtils.getCurrentYear());
			model.addAttribute("saleOrder", saleOrder);
		}

		return redirect;

	}

	@RequestMapping(value = "/sale/checkout", method = RequestMethod.POST)
	public String submitSaleCheckout(ModelMap model, SaleOrder saleOrder) {



		saleOrder.setUser(SecurityContextHolder.getContext().getAuthentication().getName());
		saleOrderService.saveSaleOrder(saleOrder);

		model.remove("saleOrder");

		return "redirect:detail/" + saleOrder.getYear()+"/"+saleOrder.getPkSale();

	}

	/******************************** PAYMENTS ************************************/

	@RequestMapping(value = "/sale/payment/{year}/{pkSale}", method = RequestMethod.GET)
	public String paymentSale(
								ModelMap model, 
								@PathVariable("year") int year,
								@PathVariable("pkSale") long pkSale) {

		SaleOrder saleOrder = (SaleOrder) saleOrderService.getSaleOrderById(pkSale, year);
		List<Payment> payments = saleOrderService.getAllPaymentById(pkSale, year);

		// saleOrder.setItems(items);
		saleOrder.setPayments(payments);
		saleOrder.setPkSale(pkSale);
		model.addAttribute("saleOrder", saleOrder);

		return "paymentSaleOrder";

	}

	@ResponseBody
	@RequestMapping(value = "/sale/payments", method = RequestMethod.GET)
	public List<Payment> getPayments(SaleOrder saleOrder) {

		List<Payment> payments;
		payments = saleOrder.getPayments() != null ? saleOrder.getPayments() : new ArrayList<Payment>();

		return payments;

	}

	@RequestMapping(value = "/sale/payment/{year}/{pkSale}", method = RequestMethod.POST)
	public String submitPaymentSale(ModelMap model, SaleOrder saleOrder) {

		saleOrder.getPayments().removeIf(payment -> (payment.getAmountMXN() == 0 && payment.getPk() == 0));

		saleOrder.setUser(SecurityContextHolder.getContext().getAuthentication().getName());
		saleOrderService.saveAllPaymentById(saleOrder);

		return "redirect:../attach/" + saleOrder.getYear() +"/"+ saleOrder.getPkSale();

	}

	// ***************AttachSales********************
	// **********************************************
	@RequestMapping(value = "/sale/payment/attach/{year}/{pkSale}", method = RequestMethod.GET)
	public String paymentAttachSale(ModelMap model, SaleOrder saleOrder) {
		
		List<Payment> payments = saleOrderService.getAllPaymentById(saleOrder.getPkSale(), saleOrder.getYear());
		Set<String> existFile = new HashSet<String>();
		for (Payment item : payments) {
			String fileName = item.getPayNumber() + ".pdf";
			File fileToDownload = new File(filePathToBeServed+saleOrder.getYear()+"/" + fileName);

			if (fileToDownload.exists()) {
				existFile.add("" + item.getPayNumber());
			}
		}
		model.addAttribute("existFilePayment", existFile);
		saleOrder.setPayments(payments);
		model.addAttribute("saleOrder", saleOrder);

		return "paymentAttachSaleOrder";

	}

	@RequestMapping(value = "/sale/payment/attach/{year}/{pkSale}", method = RequestMethod.POST)
	public String submitPaymentAttachSale(ModelMap model, 
			@PathVariable("year") int year, 
			@PathVariable("pkSale") long pkSale,
			@RequestParam("files") MultipartFile[] files,
			RedirectAttributes redirectAttributes, SaleOrder saleOrder) {

		System.out.println("subiendo archivos");
		StringJoiner sj = new StringJoiner(" , ");
		int indexFile = 0;

		String directory = filePathToBeServed+Integer.toString(year)+"/";		
		File pathFile = new File(directory);
		
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		
		directory = pathFile.getPath() + "/";
		
		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue;
			}

			try {
				byte[] bytes = file.getBytes();
				File fileDoc = new File(directory, file.getOriginalFilename());

				// FileUtils.writeByteArrayToFile(fileDoc, file.getBytes());

				String name = fileDoc.getName();
				String extension = FilenameUtils.getExtension(name);

				System.out.println("extension-- " + extension + "index: " + indexFile);

				if (!extension.equals(fileFormat)) {
					model.put("formatError", "only PDF");
					return "paymentAttachSaleOrder";

				}

				int indexItem = 0;
				for (Payment payment : saleOrder.getPayments()) {
					if (name.equals(payment.getAttach())) {
						Path path = Paths.get(directory + payment.getPayNumber() + "." + extension);
						Files.write(path, bytes);
					}
					indexItem++;

				}

				sj.add(file.getOriginalFilename());

			} catch (IOException e) {
				e.printStackTrace();
			}

			indexFile++;
		}

		String uploadedFileName = sj.toString();
		if (StringUtils.isEmpty(uploadedFileName)) {
			model.put("error", "Please select a file to upload");
			return "paymentAttachSaleOrder";

		} else {
			model.put("msg", "Carga exitosa '" + uploadedFileName + "'");
			return "redirect:../../../detail/" + saleOrder.getYear() + "/" +saleOrder.getPkSale();
		}

	}

	/********************************
	 * CLOSE SALE ORDER
	 ************************************/

	@RequestMapping(value = "/sale/close/{year}/{pkSale}", method = RequestMethod.GET)
	public String closeSale(
					ModelMap model, 
					@PathVariable("year") int year,
					@PathVariable("pkSale") long pkSale) {

		SaleOrder saleOrder = (SaleOrder) saleOrderService.getSaleOrderById(pkSale, year);
		List<Payment> payments = saleOrderService.getAllPaymentById(pkSale, year);
		// Se requiere recuperar la purchaseorder
		long pkClient = saleOrder.getClient().getPk();
		// System.out.println("pkClient"+pkClient);
		Client client = (Client) catalogService.findRow(pkClient, Client.class);

		saleOrder.setClient(client);
		saleOrder.setPayments(payments);

		model.addAttribute("action", "CLOSE");
		model.addAttribute("saleOrder", saleOrder);

		return "detailSaleOrder";

	}

	@RequestMapping(value = "/sale/close/{year}/{pkSale}", method = RequestMethod.POST)
	public String submitCloseSale(ModelMap model, SaleOrder saleOrder) {

		saleOrderService.updateStatus(saleOrder.getPkSale(), saleOrder.getYear(), StatusOrder.CLOSE.getpk());

		saleOrder.setStatus(StatusOrder.CLOSE);
		model.addAttribute("action", "READ");
		model.addAttribute("saleOrder", saleOrder);

		return "detailSaleOrder";

	}

	/********************************
	 * DETAIL SALE ORDER
	 ************************************/

	@RequestMapping(value = "/sale/detail/{year}/{pkSale}", method = RequestMethod.GET)
	public String detailSale(
								ModelMap model, 
								@PathVariable("year") int year,
								@PathVariable("pkSale") long pkSale) {

		SaleOrder saleOrder;
		saleOrder = (SaleOrder) saleOrderService.getSaleOrderById(pkSale, year);

		Client client = (Client) catalogService.findRow(saleOrder.getClient().getPk(), Client.class);

		saleOrder.setClient(client);
		List<Payment> payments = saleOrderService.getAllPaymentById(pkSale, year);
		saleOrder.setPayments(payments);
		Set<String> existFile = new HashSet<String>();
		for (Payment item : payments) {
			String fileName = item.getPayNumber() + ".pdf";
			File fileToDownload = new File(filePathToBeServed+year+"/" + fileName);
			System.out.println(filePathToBeServed+year+"/" + fileName);
			if (fileToDownload.exists()) {
				existFile.add("" + item.getPayNumber());
			}
		}
		model.addAttribute("existFilePayment", existFile);
		model.addAttribute("saleOrder", saleOrder);

		return "detailSaleOrder";

	}

	@RequestMapping(value = "/sale/payment/download/{year}/{lotNumber}")
	public void getLogFile(HttpSession session, HttpServletResponse response,
			@PathVariable("year") String year,@PathVariable("lotNumber") String pkPurchase) throws Exception {
		try {
			String fileName = pkPurchase + ".pdf";

			File fileToDownload = new File(filePathToBeServed+year+"/" + fileName);
			InputStream inputStream = new FileInputStream(fileToDownload);
			response.setContentType("application/force-download");
			response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
			IOUtils.copy(inputStream, response.getOutputStream());
			response.flushBuffer();
			inputStream.close();
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
		}

	}

	/********************************
	 * HISTORY PURCHASE ORDER
	 ************************************/

	@RequestMapping(value = "/sale/history", method = RequestMethod.GET)
	public String historySale(ModelMap model) {

		int year = Calendar.getInstance().get(Calendar.YEAR);

		return "redirect:history/" + year;

	}

	@RequestMapping(value = "/sale/history/{year}", method = RequestMethod.GET)
	public String searchHistorySale(ModelMap model, @PathVariable("year") int year) {

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		List<? extends ViewModel> listRows = handleView.getHistory("saleOrder", year);

		model.addAttribute("year", year);
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("action", "HISTORY");
		model.addAttribute("listRows", listRows);

		return "viewSaleOrder";

	}
	
	
	/************************************************/
	/***********SEND MAIL SALE ORDER*****************/
	/************************************************/
	
	@ResponseBody
	@RequestMapping(value = "/sale/mailing", method = RequestMethod.GET)
	public JSONResponse sendMailSaleOrder(ModelMap model, SaleOrder saleOrder) {

		JSONResponse response;
		response = new JSONResponse();
		
		try{
			Email email = new Email();
			email.setSubject("Sale Order");
			email.setTo(saleOrder.getClient().getEmail());
			email.setBody(SaleOrderTemplate.getSaleOrderEmail(saleOrder));
			emailService.sendEmail(email);	
		}
		catch(Exception ex){
			response.setStatus(StatusResponse.ERROR);
			ex.printStackTrace();
		}

		return response;

	}	
	
	/********************************
	 * PRINT NOTE
	 ************************************/
	@ResponseBody
	@RequestMapping(value = "/sale/printing", method = RequestMethod.GET)
	public String printNote(SaleOrder saleOrder) {

		String templateSaleOrder;
		templateSaleOrder = SaleOrderNoteTemplate.getSaleOrderNote(saleOrder);

		return templateSaleOrder;

	}

}
