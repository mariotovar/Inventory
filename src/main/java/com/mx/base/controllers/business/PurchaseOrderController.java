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
import org.springframework.security.core.Authentication;
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
import com.mx.base.component.PurchaseConverter;
import com.mx.base.models.catalog.Email;
import com.mx.base.models.catalog.Expense;
import com.mx.base.models.catalog.InputReceived;
import com.mx.base.models.catalog.Item;
import com.mx.base.models.catalog.Payment;
import com.mx.base.models.catalog.Product;
import com.mx.base.models.catalog.Provider;
import com.mx.base.models.catalog.Purchase;
import com.mx.base.models.catalog.PurchaseExpensetEntity;
import com.mx.base.models.catalog.PurchaseItemLot;
import com.mx.base.models.catalog.PurchaseOrder;
import com.mx.base.models.catalog.PurchasePaymentEntity;
import com.mx.base.models.catalog.Shipto;
import com.mx.base.repository.PurchasePaymentRepository;
import com.mx.base.services.CatalogService;
import com.mx.base.services.PurchaseService;
import com.mx.base.services.impl.TicketPurchasePDF;
import com.mx.base.util.response.ExpenseType;
import com.mx.base.util.response.JSONResponse;
import com.mx.base.util.response.PieceCondition;
import com.mx.base.util.response.StatusOrder;
import com.mx.base.util.response.StatusResponse;
import com.mx.base.util.services.EmailService;
import com.mx.base.util.velocity.PurchaseOrderTemplate;

@Controller
@RequestMapping("/order")
@SessionAttributes("purchaseOrder")
public class PurchaseOrderController {

	private String filePathToBeServed = "/home/milla/purchase/inputs/";
	private String filePathPurchasePayments = "/home/milla/purchase/payments/";
	
	private String fileFormat = "pdf";
	
	@Autowired
	HandleView handleView;
	
	@Autowired
	HandleComponent handleComponent;
	
	@Autowired
	private CatalogService catalogService;
	
	@Autowired
	private PurchaseConverter purchaseConverter;

	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private PurchasePaymentRepository purchasePaymentRepository;

	@Autowired
	private TicketPurchasePDF ticketPurchasePDF;
	
	@Autowired
	private EmailService emailService;

	/************************** PURCHASE ORDER ******************************/
	
	@RequestMapping(value = "/purchase/start", method = RequestMethod.GET)
	public String loadMaps(HttpSession session) {

		handleComponent.loadSessionMap(session, Product.class);
		handleComponent.loadSessionMap(session, Provider.class);

		return "redirect:entity";

	}	

	@RequestMapping(value = "/purchase/entity", method = RequestMethod.GET)
	public String purchaseEntity(ModelMap model, HttpSession session) {

		model.addAttribute("purchaseOrder", new PurchaseOrder());

		return "purchaseEntity";

	}

	@RequestMapping(value = "/purchase/entity", method = RequestMethod.POST)
	public String submitPurchaseEntity(ModelMap model, PurchaseOrder purchaseOrder, RedirectAttributes redirect) {

		long pkProvider = purchaseOrder.getProvider().getPk();

		Provider provider = (Provider) catalogService.findRow(pkProvider, Provider.class);

		purchaseOrder.setProvider(provider);
		redirect.addFlashAttribute("purchaseOrder", purchaseOrder);

		return "redirect:cart";

	}

	@RequestMapping(value = "/purchase/cart", method = RequestMethod.GET)
	public String purchaseCart(PurchaseOrder purchaseOrder, ModelMap model) {

		String redirect;
		redirect = "redirect:entity";
		if (purchaseOrder.getProvider().getPk() != 0) {
			redirect = "purchaseCart";
			model.addAttribute("conditions", PieceCondition.values());
			model.addAttribute("purchaseOrder", purchaseOrder);
		}

		return redirect;

	}

	@ResponseBody
	@RequestMapping(value = "/purchase/items", method = RequestMethod.GET)
	public List<Item> getItems(PurchaseOrder purchaseOrder) {

		List<Item> items;
		items = purchaseOrder.getItems();

		return items;

	}

	@RequestMapping(value = "/purchase/cart", method = RequestMethod.POST)
	public String submitPurchaseCart(ModelMap model, PurchaseOrder purchaseOrder, RedirectAttributes redirect) {

		purchaseOrder.getItems().removeIf(item -> (item.getQty() == 0));
		redirect.addFlashAttribute("purchaseOrder", purchaseOrder);

		return "redirect:shipto";

	}

	@RequestMapping(value = "/purchase/shipto", method = RequestMethod.GET)
	public String shipto(ModelMap model, PurchaseOrder purchaseOrder) {

		String redirect;
		List<Shipto> lstShipto;
		redirect = "redirect:cart";
		
		if (purchaseOrder.getItems() != null) {
			redirect = "purchaseShipto";						
			lstShipto = (List<Shipto>) catalogService.getListRows(Shipto.class);
			model.addAttribute("lstShipto", lstShipto);
			model.addAttribute("purchaseOrder", purchaseOrder);
		}

		return redirect;

	}

	@RequestMapping(value = "/purchase/shipto", method = RequestMethod.POST)
	public String submitshipto(ModelMap model, PurchaseOrder purchaseOrder, RedirectAttributes redirect) {

		model.addAttribute("purchaseOrder", purchaseOrder);
		redirect.addFlashAttribute("purchaseOrder", purchaseOrder);
		return "redirect:checkout";

	}

	@RequestMapping(value = "/purchase/checkout", method = RequestMethod.GET)
	public String purchaseCheckout(ModelMap model, PurchaseOrder purchaseOrder) {
		
		String redirect;
		Shipto shipto = null;
		redirect = "redirect:entity";
		if (purchaseOrder.getItems() != null) {
			redirect = "purchaseCheckout";
			purchaseOrder.setPurchaseDate(new Date());
			shipto = (Shipto) catalogService.findRow(purchaseOrder.getShipto().getPk(), Shipto.class);
			purchaseOrder.setShipto(shipto);
			model.addAttribute("purchaseOrder", purchaseOrder);
		}

		return redirect;

	}

	@RequestMapping(value = "/purchase/checkout", method = RequestMethod.POST)
	public String submitPurchaseCheckout(ModelMap model, PurchaseOrder purchaseOrder) {

		Purchase purchaseBD = purchaseConverter.convertModel2Entity(purchaseOrder);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		purchaseBD.setUser(name);
		purchaseBD = purchaseService.savePurchase(purchaseBD);

		model.remove("purchaseOrder");

		return "redirect:detail/" + purchaseBD.getYear()+"/"+purchaseBD.getPk();

	}

	
	@ResponseBody
	@RequestMapping(value = "/purchase/mailing", method = RequestMethod.GET)
	public JSONResponse printPurchase(ModelMap model, PurchaseOrder purchaseOrder) {

		JSONResponse response;
		response = new JSONResponse();
		
		try{
			Email email = new Email();
			email.setSubject("New Purchase Order");
			email.setTo(purchaseOrder.getProvider().getEmail());
			email.setCc1(purchaseOrder.getProvider().getEmailCC1());
			email.setCc2(purchaseOrder.getProvider().getEmailCC2());
			email.setBody(PurchaseOrderTemplate.getPurchaseOrderEmail(purchaseOrder));
			emailService.sendEmail(email);	
		}
		catch(Exception ex){
			response.setStatus(StatusResponse.ERROR);
		}

		return response;

	}
	
	/******************************** PAYMENTS ************************************/

	@RequestMapping(value = "/purchase/payment/{year}/{pkPurchase}", method = RequestMethod.GET)
	public String paymentPurchase(	ModelMap model, 
									@PathVariable("year") int year,
									@PathVariable("pkPurchase") Long pkPurchase) {

		PurchaseOrder purchaseOrder = new PurchaseOrder();
		purchaseOrder=purchaseService.getPurchaseOrderById(pkPurchase, year);
	
		model.addAttribute("purchaseOrder", purchaseOrder);

		return "paymentPurchaseOrder";

	}

	@ResponseBody
	@RequestMapping(value = "/purchase/payments", method = RequestMethod.GET)
	public List<Payment> getPayments(PurchaseOrder purchaseOrder) {

		List<Payment> payments;
		payments = purchaseOrder.getPayments() != null ? purchaseOrder.getPayments() : new ArrayList<Payment>();

		return payments;

	}

	@RequestMapping(value = "/purchase/payment/{year}/{pkPurchase}", method = RequestMethod.POST)
	public String submitPaymentPurchase(ModelMap model, PurchaseOrder purchaseOrder) {

		purchaseOrder.getPayments().removeIf(payment -> (payment.getAmountUSD() == 0 && payment.getPk()==0));
		
		String user = SecurityContextHolder.getContext().getAuthentication().getName();
		List<PurchasePaymentEntity> lstPaymentsBD = purchaseConverter.paymentFromModel2Entity(purchaseOrder, user);
		purchasePaymentRepository.savePayments(purchaseOrder.getPkPurchase(), purchaseOrder.getYear(), lstPaymentsBD);		
		
		return "redirect:../attach/" + purchaseOrder.getYear() + "/" + + purchaseOrder.getPkPurchase();

	}

	
	@RequestMapping(value = "/purchase/payment/attach/{year}/{pkPurchase}", method = RequestMethod.GET)
	public String paymentAttachSale(ModelMap model, PurchaseOrder purchaseOrder) {
		List<Payment> payments = new ArrayList<Payment>();
		List<PurchasePaymentEntity> lstPayment = purchasePaymentRepository.getByPurchaseId(purchaseOrder.getPkPurchase(), purchaseOrder.getYear());
		Set<String> existFile = new HashSet<String>();
		for (PurchasePaymentEntity itemPayment : lstPayment) {
			Payment payment = new Payment();
			payment.setPk(itemPayment.getPk());
			payment.setYear(itemPayment.getYear());
			payment.setAmountMXN(itemPayment.getAmountMXN());
			payment.setAmountUSD(itemPayment.getAmountUSD());
			payment.setNotes(itemPayment.getNotes());
			payment.setPaymentDate(itemPayment.getPaymentDate());
			payments.add(payment);
			String fileName = payment.getPayNumber() + ".pdf";
			File fileToDownload = new File(filePathPurchasePayments+purchaseOrder.getYear()+"/" + fileName);
			
			if (fileToDownload.exists()) {
				existFile.add("" + payment.getPayNumber());
			}
		}
		
		model.addAttribute("PurchaseexistFilePayment", existFile);
		purchaseOrder.setPayments(payments);
		model.addAttribute("purchaseOrder", purchaseOrder);

		return "paymentAttachPurchaseOrder";

	}
	

	@RequestMapping(value = "/purchase/payment/attach/{year}/{pkPurchase}", method = RequestMethod.POST)
	public String submitPaymentAttachSale(ModelMap model, @PathVariable("year") int year,  @RequestParam("files") MultipartFile[] files, RedirectAttributes redirectAttributes, PurchaseOrder purchaseOrder) {

		System.out.println("subiendo archivos");
		List<Item> items = purchaseOrder.getItems();
		StringJoiner sj = new StringJoiner(" , ");
		int indexFile = 0;
		
		String directory = filePathPurchasePayments+Integer.toString(year)+"\\";		
		System.out.println("directory:"+directory);
		File pathFile = new File(directory);
		
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}		
		
		directory = pathFile.getPath() + "\\";

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue;
			}

			try {
				byte[] bytes = file.getBytes();
				File fileDoc = new File(directory, file.getOriginalFilename());

//              FileUtils.writeByteArrayToFile(fileDoc, file.getBytes());

				String name = fileDoc.getName();
				String extension = FilenameUtils.getExtension(name);

				System.out.println("extension-- " + extension + "index: " + indexFile);
				
				if (!extension.equals(fileFormat)) {
					model.put("formatError", "only PDF");
					return "paymentAttachPurchaseOrder";
					
				}
				
				int indexItem = 0;				
				for(Payment payment : purchaseOrder.getPayments()){
//					if (indexFile == indexItem) {

					System.out.println("directory-- " + directory+ payment.getPayNumber());
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
			return "paymentAttachPurchaseOrder";			
		} else {
			model.put("msg", "Carga exitosa '" + uploadedFileName + "'");
			return "redirect:../../../detail/" + purchaseOrder.getYear()+"/"+purchaseOrder.getPkPurchase();
		}
		
	
	}


	/******************************** EXPENSES ************************************/
//	purchaseOrder.getPkPurchase()
	@RequestMapping(value = "/purchase/expense/{year}/{pkPurchase}", method = RequestMethod.GET)
	public String expensePurchase(
									ModelMap model, 
									@PathVariable("year") int year,
									@PathVariable("pkPurchase") Long pkPurchase) {

		PurchaseOrder purchaseOrder = new PurchaseOrder();

		List<PurchaseExpensetEntity> lstExpenses = purchaseService.getExpenseByPurchaseId(pkPurchase, year);
		List<Expense> expenses = purchaseConverter.expensesFromEntity2Model(lstExpenses);

		purchaseOrder.setExpenses(expenses);
		purchaseOrder.setPkPurchase(pkPurchase);
		purchaseOrder.setYear(year);
		model.addAttribute("purchaseOrder", purchaseOrder);
		model.addAttribute("expenseTypes", ExpenseType.values());

		return "expensePurchaseOrder";

	}

	@ResponseBody
	@RequestMapping(value = "/purchase/expenses", method = RequestMethod.GET)
	public List<Expense> getExpenses(PurchaseOrder purchaseOrder) {

		List<Expense> expenses;
		expenses = purchaseOrder.getExpenses() != null ? purchaseOrder.getExpenses() : new ArrayList<Expense>();

		return expenses;

	}

	@RequestMapping(value = "/purchase/expense/{year}/{purchaseOrder}", method = RequestMethod.POST)
	public String submitExpensePurchase(ModelMap model, PurchaseOrder purchaseOrder) {

		purchaseOrder.getExpenses().removeIf(expense -> (expense.getAmountMXN() == 0));

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); 
		
		List<PurchaseExpensetEntity> lstExpenses = purchaseConverter.expensesFromModel2Entity(purchaseOrder, name);

		purchaseService.saveExpenses(purchaseOrder.getPkPurchase(), purchaseOrder.getYear(), lstExpenses);

		model.remove("purchaseOrder");

		return "redirect:../../detail/" + purchaseOrder.getYear() + "/" + purchaseOrder.getPkPurchase();

	}

	@RequestMapping(value = "/purchase/lot/download/{year}/{lotNumber}")
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
	@RequestMapping(value = "/purchase/payment/download/{year}/{payNumber}")
	public void getLogPaymentFile(HttpSession session, HttpServletResponse response,
			@PathVariable("year") String year,@PathVariable("payNumber") String pkPurchase) throws Exception {
		try {
			String fileName = pkPurchase + ".pdf";

			File fileToDownload = new File(filePathPurchasePayments+year+"/" + fileName);

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
	 * RECEIVED PRODUCTS
	 ************************************/
	@RequestMapping(value = "/purchase/received/{year}/{pkPurchase}", method = RequestMethod.GET)
	public String receivedPurchase(	ModelMap model, 
									@PathVariable("year") int year,
									@PathVariable("pkPurchase") Long pkPurchase) {

		model.addAttribute("purchaseOrder", new PurchaseOrder());

		return "redirect:../../lotReceived/" + year +"/"+pkPurchase;

	}

	@RequestMapping(value = "/purchase/lotReceived/{year}/{pkPurchase}", method = RequestMethod.GET)
	public String loadReceivedPurchase( ModelMap model, 
										PurchaseOrder purchaseOrder,
										@PathVariable("year") int year,
										@PathVariable("pkPurchase") Long pkPurchase) {

		Purchase purchase = purchaseService.getPurchaseById(pkPurchase, year);

		purchaseOrder.setPkPurchase(pkPurchase);
		purchaseOrder.setYear(purchase.getYear());		
		purchaseOrder.setPurchaseDate(purchase.getCreationDate());
		
		if (purchaseOrder.getItems() == null || purchaseOrder.getItems().size() <= 0) {
			List<Item> items = purchaseConverter.convertItemsFromEntity2ModelForReceive(purchase);
			purchaseOrder.setItems(items);
		}
		
		model.addAttribute("purchaseOrder", purchaseOrder);

		return "receivedPurchaseOrder";

	}

	@RequestMapping(value = "/purchase/lotReceived/{year}/{pkPurchase}", method = RequestMethod.POST)
	public String receivedPurchase(ModelMap model, PurchaseOrder purchaseOrder, RedirectAttributes redirect) {

		for (Item item : purchaseOrder.getItems()) {
			item.getInputs().removeIf(input -> (input.getReceivedQty() == 0));
		}

		redirect.addFlashAttribute("purchaseOrder", purchaseOrder);

		return "redirect:../../confirmation";

	}

	@RequestMapping(value = "/purchase/confirmation", method = RequestMethod.GET)
	public String confirmPurchase(ModelMap model, PurchaseOrder purchaseOrder) {

		model.addAttribute("purchaseOrder", purchaseOrder);

		return "confirmationPurchaseOrder";

	}

	@RequestMapping(value = "/purchase/confirmation", method = RequestMethod.POST)
	public String submitConfirmPurchase(ModelMap model, PurchaseOrder purchaseOrder) {

		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		System.out.println("ORDER: " + purchaseOrder.getPkPurchase());
		for (Item item : purchaseOrder.getItems()) {
			System.out.println("value :" + item.getValue());

			System.out.println("pk :" + item.getPk());
			System.out.println("description :" + item.getDescription());
			for (InputReceived input : item.getInputs()) {
				System.out.println("lot :" + input.getLot());
				System.out.println("received qty :" + input.getReceivedQty());
				System.out.println("notes :" + input.getNotes());
			}
			System.out.println("--------------------");
		}
		System.out.println("-------------------------------------------------------");
		System.out.println("-------------------------------------------------------");
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName(); 
		List<PurchaseItemLot> lst = purchaseConverter.convertModel2EntityForReceive(purchaseOrder, name);
		purchaseService.saveReceive(lst);
		purchaseService.updateStatusPurchase(purchaseOrder.getPkPurchase(), purchaseOrder.getYear(), StatusOrder.RECEIVED.getpk());

		model.remove("purchaseOrder");

		return "redirect:detail/" + +purchaseOrder.getYear()+"/"+purchaseOrder.getPkPurchase();

	}

	/********************************
	 * UPDATE PURCHASE ORDER
	 ************************************/

	@RequestMapping(value = "/purchase/update/{year}/{pkPurchase}", method = RequestMethod.GET)
	public String updatePurchase(	ModelMap model, 
									HttpSession session,
									@PathVariable("year") int year,
									@PathVariable("pkPurchase") Long pkPurchase) {

		PurchaseOrder purchaseOrder;
		purchaseOrder = purchaseService.getPurchaseOrderById(pkPurchase, year);

		handleComponent.loadSessionMap(session, Product.class);
		
		model.addAttribute("purchaseOrder", purchaseOrder);

		return "redirect:../../cart";

	}

	
	
	/********************************
	 * CANCEL PURCHASE ORDER
	 ************************************/

	@RequestMapping(value = "/purchase/cancel/{year}/{pkPurchase}", method = RequestMethod.GET)
	public String cancelPurchase(	ModelMap model, 
									@PathVariable("year") int year,
									@PathVariable("pkPurchase") Long pkPurchase) {

		PurchaseOrder purchaseOrder;
		purchaseOrder = purchaseService.getPurchaseOrderById(pkPurchase, year);
		//purchaseOrder=purchaseService.getPurchaseOrderById(purchaseOrder.getPkPurchase(), purchaseOrder.getYear());
		model.addAttribute("action", "DELETE");
		model.addAttribute("purchaseOrder", purchaseOrder);

		return "detailPurchaseOrder";

	}

	@RequestMapping(value = "/purchase/cancel/{year}/{pkPurchase}", method = RequestMethod.POST)
	public String submitCancelPurchase(ModelMap model, PurchaseOrder purchaseOrder) {


		purchaseService.updateStatusPurchase(purchaseOrder.getPkPurchase(), purchaseOrder.getYear(), StatusOrder.CANCEL.getpk());

		purchaseOrder = purchaseService.getPurchaseOrderById(purchaseOrder.getPkPurchase(), purchaseOrder.getYear());
		model.addAttribute("action", "READ");
		model.addAttribute("purchaseOrder", purchaseOrder);

		return "detailPurchaseOrder";

	}

	/********************************
	 * CLOSE PURCHASE ORDER
	 ************************************/

	@RequestMapping(value = "/purchase/close/{year}/{pkPurchase}", method = RequestMethod.GET)
	public String closePurchase(ModelMap model, 
								@PathVariable("year") int year,
								@PathVariable("pkPurchase") Long pkPurchase) {

		PurchaseOrder purchaseOrder=purchaseService.getPurchaseOrderById(pkPurchase, year);

		model.addAttribute("action", "CLOSE");
		model.addAttribute("purchaseOrder", purchaseOrder);

		return "detailPurchaseOrder";

	}

	@RequestMapping(value = "/purchase/close/{year}/{pkPurchase}", method = RequestMethod.POST)
	public String submitClosePurchase(ModelMap model, PurchaseOrder purchaseOrder) {

		purchaseOrder.setStatus(StatusOrder.CLOSE);
		purchaseService.updateStatusPurchase(purchaseOrder.getPkPurchase(), purchaseOrder.getYear(), StatusOrder.CLOSE.getpk());
		
		purchaseOrder=purchaseService.getPurchaseOrderById(purchaseOrder.getPkPurchase(), purchaseOrder.getYear());
		
		model.addAttribute("action", "READ");
		model.addAttribute("purchaseOrder", purchaseOrder);
		
		return "detailPurchaseOrder";

	}

	/********************************
	 * DETAIL PURCHASE ORDER
	 ************************************/

	@RequestMapping(value = "/purchase/detail/{year}/{pkPurchase}", method = RequestMethod.GET)
	public String detailPurchase(	ModelMap model, 
									@PathVariable("year") int year,
									@PathVariable("pkPurchase") Long pkPurchase) {

		PurchaseOrder purchaseOrder = purchaseService.getPurchaseOrderById(pkPurchase, year);

		Set<String> existFile = new HashSet<String>();
		for (Item item : purchaseOrder.getItems()) {
			for (InputReceived lot : item.getInputs()) {
				String fileName = lot.getLotNumber() + ".pdf";
				File fileToDownload = new File(filePathToBeServed+""+year+"/" + fileName);
				if (fileToDownload.exists()) {
					existFile.add("" + lot.getLotNumber());
				}
			}
		}
		model.addAttribute("existFile", existFile);
		Set<String> PurchaseexistFilePayment = new HashSet<String>();
		for (Payment itempay : purchaseOrder.getPayments()) {
			String fileName = itempay.getPayNumber() + ".pdf";
			File fileToDownload = new File(filePathPurchasePayments+""+year+"/" + fileName);
			System.out.println(filePathPurchasePayments+""+year+"/" + fileName);
			if (fileToDownload.exists()) {
				PurchaseexistFilePayment.add("" + itempay.getPayNumber());
			}
		}
		
		model.addAttribute("PurchaseexistFilePayment", PurchaseexistFilePayment);
		model.addAttribute("purchaseOrder", purchaseOrder);
		model.addAttribute("expenseTypes", ExpenseType.values());

		return "detailPurchaseOrder";

	}

	/********************************
	 * ATTACH DOCUMENTS PURCHASE ORDER
	 ************************************/
	@RequestMapping(value = "/purchase/upload/{year}/{pkPurchase}", method = RequestMethod.GET)
	public String uploadPurchase(	ModelMap model, 
									@PathVariable("year") int year,
									@PathVariable("pkPurchase") Long pkPurchase) {

		PurchaseOrder purchaseOrder = new PurchaseOrder();

		Purchase purchase = purchaseService.getPurchaseById(pkPurchase, year);
		purchaseOrder.setPurchaseDate(purchase.getCreationDate());
		purchaseOrder.setPkPurchase(pkPurchase);
		purchaseOrder.setYear(purchase.getYear());
		List<Item> items = purchaseConverter.convertItemsFromEntity2Model(purchase);
		purchaseOrder.setItems(items);

		Set<String> existFile = new HashSet<String>();
		for (Item item : purchaseOrder.getItems()) {
			for (InputReceived lot : item.getInputs()) {
				String fileName = lot.getLotNumber() + ".pdf";
				File fileToDownload = new File(filePathToBeServed +year+"/"+ fileName);
				if (fileToDownload.exists()) {
					existFile.add("" + lot.getLotNumber());
				}
			}
		}
		model.addAttribute("existFileLot", existFile);
		
		for (Item item : items) {
//			System.out.println("itemsss" + item.getInputs());
			List<InputReceived> inputs = item.getInputs();

			for (InputReceived inputReceived : inputs) {
				System.out.println("Lots " + inputReceived.getLotNumber());
			}
		}

		model.addAttribute("purchaseOrder", purchaseOrder);

		return "uploadPurchaseOrder";

	}

	@RequestMapping(value = "/purchase/upload/{year}/{pkPurchase}", method = RequestMethod.POST)
	public String submitUploadPurchase(
										ModelMap model, 
										PurchaseOrder purchaseOrder,
										@PathVariable("year") int year,
										@PathVariable("pkPurchase") Long pkPurchase,
										@RequestParam("files") MultipartFile[] files, 
										RedirectAttributes redirectAttributes) {
		
//		System.out.println("subiendo archivos");

		List<Item> items = purchaseOrder.getItems();

		StringJoiner sj = new StringJoiner(" , ");
		int indexFile = 0;
		
		String directory = filePathToBeServed+Integer.toString(year)+"/";		
		File pathFile = new File(directory);
		
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}
		
		directory = pathFile.getPath() + "/";
		
//		System.out.println("DIRECTORIO " + directory );
		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue;
			}
			
			try {
				byte[] bytes = file.getBytes();
				File fileDoc = new File(directory, file.getOriginalFilename());

//              FileUtils.writeByteArrayToFile(fileDoc, file.getBytes());

				String name = fileDoc.getName();
				String extension = FilenameUtils.getExtension(name);

				System.out.println("name: " + name + "extension: " + extension + "index: " + indexFile + "originalName: " + file.getOriginalFilename());
				
				if (!extension.equals(fileFormat)) {
					model.put("formatError", "only PDF");
					return "uploadPurchaseOrder";
					
				}
				
				int indexItem = 0;
				for (Item item : items) {

					List<InputReceived> inputs = item.getInputs();

					for (InputReceived inputReceived : inputs) {
						System.out.println("Attach: " + inputReceived.getAttach() + "index: " + indexItem);
						System.out.println("Lotes: " + inputReceived.getLotNumber() + "index: " + indexItem);
//						if (indexFile == indexItem) {
						if (name.equals(inputReceived.getAttach())) {
							Path path = Paths.get(directory + inputReceived.getLotNumber() + "." + extension);
							Files.write(path, bytes);
						}
						indexItem++;
					}
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
			return "uploadPurchaseOrder";
			
		} else {
			model.put("msg", "Carga exitosa '" + uploadedFileName + "'");

			return "redirect:/order/purchase/detail/" + year + "/" + pkPurchase;
		}

	}

	@RequestMapping(value = "/purchase/upload/uploadStatus", method = RequestMethod.GET)
	public String uploadStatus() {
		return "uploadStatus";
	}

	/********************************
	 * HISTORY PURCHASE ORDER
	 ************************************/

	@RequestMapping(value = "/purchase/history", method = RequestMethod.GET)
	public String historyPurchase(ModelMap model) {

		int year = Calendar.getInstance().get(Calendar.YEAR);

		return "redirect:history/" + year;

	}

	@RequestMapping(value = "/purchase/history/{year}", method = RequestMethod.GET)
	public String searchHistoryPurchase(ModelMap model, @PathVariable("year") int year) {

		int currentYear = Calendar.getInstance().get(Calendar.YEAR);
		List<? extends ViewModel> listRows = handleView.getHistory("purchaseOrder", year);

		model.addAttribute("year", year);
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("action", "HISTORY");
		model.addAttribute("listRows", listRows);

		return "viewPurchaseOrder";

	}

	/********************************
	 * TICKET PURCHASE ORDER
	 ************************************/
	@ResponseBody
	@RequestMapping(value = "/purchase/printing/{ticket}", method = RequestMethod.GET)
	public String printTicket(ModelMap model, 
							  PurchaseOrder purchaseOrder, 
							  @PathVariable("ticket") long ticket) {

		int year = purchaseOrder.getYear();
		for (Item item : purchaseOrder.getItems()) {
			if(item.getPk()==ticket){
				ticketPurchasePDF.makeTicketPDF(item, year);	
			}
		}
		
		return "";

	}
	
	
}
