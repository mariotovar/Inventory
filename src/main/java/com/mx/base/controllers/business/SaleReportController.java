package com.mx.base.controllers.business;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mx.base.models.view.SaleReport;
import com.mx.base.services.ViewService;
import com.mx.base.util.functions.CreateSaleReport;

@Controller
@RequestMapping("/report")
public class SaleReportController {

	@Autowired
	ViewService viewService;
	
	
	@RequestMapping(value = "/download")
    public void  downloadSaleReport(HttpServletRequest request, HttpServletResponse response) {
		
		List<SaleReport> salesReport = viewService.getView(SaleReport.class);		 

		String monthTab;
		List<String> tabs = new ArrayList<String>(); 
		for(SaleReport sale: salesReport){
			monthTab = sale.getYear() + "-" + sale.getMonth();			
			if (!tabs.contains(monthTab)) { 
				tabs.add(monthTab); 
            } 
		}
		
		
		Date date = new Date();
        String strDateFormat = "YYYYMMdd"; 
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
 
        String fecha=objSDF.format(date);
        String fileName = "SaleReport"+fecha+".xlsx";
        
        CreateSaleReport saleReport = new CreateSaleReport();
        XSSFWorkbook workbook = saleReport.createSaleExcel(salesReport, tabs);
        
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
