package com.mx.base.controllers.business;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mx.base.models.view.Inventory;
import com.mx.base.models.view.InventoryLot;
import com.mx.base.services.ViewService;
import com.mx.base.util.functions.CreateExcelFile;


@Controller
@RequestMapping("/inventory")
public class InventoryController {
	
	@Autowired
	ViewService viewService;
	
	@RequestMapping(value = "/download")
    public void  downloadByProducts(HttpServletRequest request, HttpServletResponse response) {
		
		List<Inventory> inventory = viewService.getView(Inventory.class);		 
		List<InventoryLot> inventoryLot = viewService.getView(InventoryLot.class);

		
		Date date = new Date();
        String strDateFormat = "YYYYMMdd"; 
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
        
        String fecha=objSDF.format(date);
        String fileName = "Inventario"+fecha+".xlsx";
        
        CreateExcelFile excelFile = new CreateExcelFile();	   
 	   	XSSFWorkbook workbook = excelFile.createXslx(inventory, inventoryLot); 
		
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
		response.setHeader("Content-Disposition", "attachment; filename="+fileName);
		ServletOutputStream outputStream = null;
	   
		try {
			outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	  
    }

    
}
