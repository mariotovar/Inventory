package com.mx.base.util.functions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mx.base.models.view.AnualReport;
import com.mx.base.models.view.SaleReport;

public class CreateSaleReport {
	
	public XSSFWorkbook createSaleExcel(List<SaleReport> salesReport,List<String> tabs) {

		XSSFWorkbook workbook = new XSSFWorkbook(); 
		
		//this.anualReport(workbook, salesReport, tabs);
		this.monthlyReport(workbook, salesReport, tabs);

		 try { 
	            System.out.println("xlsx written successfully.");
	            return workbook;
	        } 
	        catch (Exception e) { 
	            e.printStackTrace(); 
	            return null;
	        }
		 
	}


	public void anualReport(XSSFWorkbook workbook, List<SaleReport> salesReport, List<String> tabs) {

		XSSFSheet anualSheet;
		anualSheet = workbook.createSheet("ANUAL");
		
		String[] anualColumns = { "Anio", "Mes", "Concepto", "MXN", "USD", "USER 1", "USER 2"};
		
		Row anualHeader = anualSheet.createRow(0);
		
		XSSFCellStyle myStyle = workbook.createCellStyle();
        myStyle.setAlignment(HorizontalAlignment.CENTER);
		myStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        myStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 32, 96)));
        myStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        
		XSSFFont font = (XSSFFont) workbook.createFont();
		font.setFontHeightInPoints((short) 11);
		font.setFontName("Calibri");
		font.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
		font.setBold(true);
		myStyle.setFont(font);
		
		XSSFCellStyle boldStyle = workbook.createCellStyle();
		XSSFFont fontbold = (XSSFFont) workbook.createFont();
		fontbold.setBold(true);
		boldStyle.setFont(fontbold);
		
		for(int i = 0; i < anualColumns.length; i++) {
            Cell cell = anualHeader.createCell(i);
            cell.setCellValue(anualColumns[i]);
            cell.setCellStyle(myStyle);
        }
		
		
		double total_in_mxn = 0, total_out_mxn = 0, totalInbyUser1MXN = 0 , totalInbyUser2MXN = 0, totalOutbyUser1MXN = 0 , totalOutbyUser2MXN = 0;
		double total_in_usd = 0, total_out_usd = 0, totalInbyUser1USD = 0 , totalInbyUser2USD = 0, totalOutbyUser1USD= 0 , totalOutbyUser2USD = 0;
		
		Set<AnualReport> ts = new TreeSet<AnualReport>();
		
		for(SaleReport sale: salesReport){
			
			ts.add(new AnualReport(sale.getYear(), sale.getMonth(), sale.getTypeOp(),sale.getAmountMXN(), sale.getAmountUSD(), sale.getUser()));
			
			if (sale.getType().equals("IN")) {
				total_in_mxn += sale.getAmountMXN();
				total_in_usd += sale.getAmountUSD();
				
				if (sale.getUser().equals("ELIA")) {
					totalInbyUser1MXN += sale.getAmountMXN();
					totalInbyUser1USD += sale.getAmountUSD();
				}else {
					totalInbyUser2MXN += sale.getAmountMXN();
					totalInbyUser2USD += sale.getAmountUSD();
				}
				
			}else{
				total_out_mxn += sale.getAmountMXN();
				total_out_usd += sale.getAmountUSD();
				
				if (sale.getUser().equals("ELIA")) {
					totalOutbyUser1MXN += sale.getAmountMXN();
					totalOutbyUser1USD += sale.getAmountUSD();
				}else {
					totalOutbyUser2MXN += sale.getAmountMXN();
					totalOutbyUser2USD += sale.getAmountUSD();
				}
			}
		}
	
		
		int rowNum = 1;
	    for (AnualReport e : ts) {	           
			 Row row = anualSheet.createRow(rowNum++);
			 row.createCell(0).setCellValue(e.getYear());
			 row.createCell(1).setCellValue( e.getMonth());
			 row.createCell(2).setCellValue( e.getType());
			 row.createCell(3).setCellValue( e.getAmountMXN());
			 row.createCell(4).setCellValue( e.getAmountUSD());
			 row.createCell(5).setCellValue( e.getUser());
			 row.createCell(6).setCellValue( e.getUser());					 
			 anualSheet.setColumnWidth(rowNum, 4900);
	     }
		
	    double totalIn = total_in_mxn + total_in_usd;
	    double totalInUser1 = totalInbyUser1MXN + totalInbyUser1USD;
	    double totalInUser2 = totalInbyUser2MXN + totalInbyUser2USD;    
	    
		String[] total_in = { "", "", "", "", "TOTAL IN: "+totalIn,  "TOTAL IN USER1: "+totalInUser1, "TOTAL IN USER2: "+totalInUser2};
		Row total_in_row = anualSheet.createRow(rowNum);
		for(int i = 0; i < total_in.length; i++) {
            Cell cell = total_in_row.createCell(i);
            cell.setCellValue(total_in[i]);
            cell.setCellStyle(boldStyle);
        }
		
	    double totalOut = total_out_mxn + total_out_usd;
	    double totalOutUser1 = totalOutbyUser1MXN + totalOutbyUser1USD;
	    double totalOutUser2 = totalOutbyUser2MXN + totalOutbyUser2USD;
	    
		String[] total_out = { "", "", "", "", "TOTAL OUT: "+totalOut,  "TOTAL OUT USER1: "+totalOutUser1, "TOTAL OUT USER2: "+totalOutUser2};
		Row total_out_row = anualSheet.createRow(rowNum+1);
		for(int i = 0; i < total_out.length; i++) {
            Cell cell = total_out_row.createCell(i);
            cell.setCellValue(total_out[i]);
            cell.setCellStyle(boldStyle);
        }
		
		
		double totalUser1 = totalInUser1 + totalOutUser1;
		double totalUser2 = totalInUser2 + totalOutUser2;
		
		String[] total_user = { "", "", "", "", "", "TOTAL USER1: "+totalUser1, "TOTAL USER2: "+totalUser2};
		Row total_user_row = anualSheet.createRow(rowNum+2);
		for(int i = 0; i < total_user.length; i++) {
            Cell cell = total_user_row.createCell(i);
            cell.setCellValue(total_user[i]);
            cell.setCellStyle(boldStyle);
        }
	    
	}
	
	
	public void monthlyReport(XSSFWorkbook workbook, List<SaleReport> salesReport, List<String> tabs) {
				
		XSSFSheet sheet;

        String strDateFormat = "dd-MM-yyyy HH:mm:ss"; 
        DecimalFormat formato = new DecimalFormat("#.00");
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
		
		String[] columns = { "Orden", "Concepto", "Tipo", "Fecha", "Cantidad MXN", "Cantidad USD", "Usuario"};
        
        String saleDate;
        String reporMonth;
        String reportYear;
		
		for(String hoja: tabs){
			
			String[] parts = hoja.split("-");
			String tabYear = parts[0];
			String tabMonth = parts[1];
						
			sheet = workbook.createSheet(hoja);			
			Row headerRow = sheet.createRow(0);
			
			int width = 20; // Where width is number of caracters 
			sheet.setDefaultColumnWidth(width);
			
			XSSFCellStyle myStyle = workbook.createCellStyle();
	        myStyle.setAlignment(HorizontalAlignment.CENTER);
			myStyle.setVerticalAlignment(VerticalAlignment.CENTER);
	        myStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 32, 96)));
	        myStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
			XSSFFont font = (XSSFFont) workbook.createFont();
			font.setFontHeightInPoints((short) 11);
			font.setFontName("Calibri");
			font.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
			font.setBold(true);
			myStyle.setFont(font);
			
			XSSFFont fontForTotla = (XSSFFont) workbook.createFont();
			fontForTotla.setFontHeightInPoints((short) 11);
			fontForTotla.setFontName("Calibri");
			fontForTotla.setColor(new XSSFColor(new java.awt.Color(0, 0, 0)));
			fontForTotla.setBold(true);
			
			
			XSSFCellStyle myStyleTotal = workbook.createCellStyle();
			myStyleTotal.setAlignment(HorizontalAlignment.RIGHT);
			myStyleTotal.setVerticalAlignment(VerticalAlignment.CENTER);
			
		//	myStyleTotal.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 32, 96)));
		//	myStyleTotal.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			myStyleTotal.setFont(fontForTotla);
	        
			
			XSSFCellStyle cellRightStyle = workbook.createCellStyle();
			cellRightStyle.setAlignment(HorizontalAlignment.RIGHT);
			DataFormat format = workbook.createDataFormat();
			cellRightStyle.setDataFormat(format.getFormat("#,##0.00"));			
			
			XSSFCellStyle boldStyle = workbook.createCellStyle();
			XSSFFont fontbold = (XSSFFont) workbook.createFont();
			fontbold.setBold(true);
			boldStyle.setFont(fontbold);
			
			for(int i = 0; i < columns.length; i++) {
	            Cell cell = headerRow.createCell(i);
	            
	            cell.setCellValue(columns[i]);
	            cell.setCellStyle(myStyle);
	        }
			
			double totalmxn = 0, totalbyUserMXN1 = 0 , totalbyUserMXN2 = 0;
			double totalusd = 0, totalbyUserUSD1 = 0, totalbyUserUSD2 = 0;
			
			int rowNum = 1;
			for(SaleReport sale: salesReport){
				
				reporMonth = Integer.toString(sale.getMonth());
				reportYear = Integer.toString(sale.getYear());
						
				if (reporMonth.equals(tabMonth) && reportYear.equals(tabYear)) {
					saleDate = objSDF.format(sale.getDate());					
					Row row = null;
					Cell cell = null;
					
					row = sheet.createRow(rowNum++);
					cell = row.createCell(0);
					cell.setCellValue(sale.getReportPK());
					cell = row.createCell(1);
					cell.setCellValue(sale.getTypeOp());
					cell = row.createCell(2);
					cell.setCellValue(sale.getType());
					cell = row.createCell(3);
					cell.setCellValue(saleDate);
					cell = row.createCell(4);
					cell.setCellValue(sale.getAmountMXN());
					cell.setCellStyle(cellRightStyle);
					cell = row.createCell(5);
					cell.setCellValue(sale.getAmountUSD());
					cell.setCellStyle(cellRightStyle);
					cell = row.createCell(6);
					cell.setCellValue(sale.getUser());
										
					sheet.setColumnWidth(rowNum, 8000);					
					
					if (sale.getType().equals("IN")) {
						totalmxn += sale.getAmountMXN();
						totalusd += sale.getAmountUSD();
						if (sale.getUser().equals("ELIA")) {
							totalbyUserMXN1 += sale.getAmountMXN();
							totalbyUserUSD1 += sale.getAmountUSD();
						}else {
							totalbyUserMXN2 += sale.getAmountMXN();
							totalbyUserUSD2 += sale.getAmountUSD();
						}								
					}else{
						totalmxn -= sale.getAmountMXN();
						totalusd -= sale.getAmountUSD();
						if (sale.getUser().equals("ELIA")) {
							totalbyUserMXN1 -= sale.getAmountMXN();
							totalbyUserUSD1 -= sale.getAmountUSD();
						}else {
							totalbyUserMXN2 -= sale.getAmountMXN();
							totalbyUserUSD2 -= sale.getAmountUSD();
						}								
					}
					
				}
				
			}

			Cell cellUser1 = null;
			Row totalUser1Row = sheet.createRow(rowNum++);
			totalUser1Row.setRowStyle(boldStyle);
			cellUser1 = totalUser1Row.createCell(0);
			cellUser1.setCellValue("");
			cellUser1 = totalUser1Row.createCell(1);
			cellUser1.setCellValue("");
			cellUser1 = totalUser1Row.createCell(2);
			cellUser1.setCellValue("");
			cellUser1 = totalUser1Row.createCell(3);
			cellUser1.setCellValue("TOTAL ELIA");
			cellUser1 = totalUser1Row.createCell(4);
			cellUser1.setCellValue(totalbyUserMXN1);
			cellUser1.setCellStyle(cellRightStyle);
			cellUser1 = totalUser1Row.createCell(5);
			cellUser1.setCellValue(totalbyUserUSD1);
			cellUser1.setCellStyle(cellRightStyle);
			cellUser1 = totalUser1Row.createCell(6);
			//cellUser1.setCellValue("ELIA");
			/*for(int i = 0; i < .cellIterator(); i++) {
	            Cell cell = headerRow.createCell(i);
	            cell.setCellValue(columns[i]);
	            cell.setCellStyle(myStyle);
	        }*/
			Iterator<Cell> it=	totalUser1Row.cellIterator();
			while(it.hasNext()) {
				Cell cell=it.next();
				 cell.setCellStyle(myStyleTotal);
			}
			
			Cell cellUser2 = null;
			Row totalUser2Row = sheet.createRow(rowNum++);
			totalUser2Row.setRowStyle(boldStyle);
			cellUser2 = totalUser2Row.createCell(0);
			cellUser2.setCellValue("");
			cellUser2 = totalUser2Row.createCell(1);
			cellUser2.setCellValue("");
			cellUser2 = totalUser2Row.createCell(2);
			cellUser2.setCellValue("");
			cellUser2 = totalUser2Row.createCell(3);
			cellUser2.setCellValue("TOTAL LIBORIO");
			cellUser2 = totalUser2Row.createCell(4);
			cellUser2.setCellValue(totalbyUserMXN2);
			cellUser2.setCellStyle(cellRightStyle);
			cellUser2 = totalUser2Row.createCell(5);
			cellUser2.setCellValue(totalbyUserUSD2);
			cellUser2.setCellStyle(cellRightStyle);
			cellUser1 = totalUser2Row.createCell(6);
			//cellUser1.setCellValue("LIBORIO");
			
			Iterator<Cell> itUser2=	totalUser2Row.cellIterator();
			while(itUser2.hasNext()) {
				Cell cell=itUser2.next();
				 cell.setCellStyle(myStyleTotal);
			}
			
			Cell cellTotal = null;
			Row totalRow = sheet.createRow(rowNum++);
			totalRow.setRowStyle(boldStyle);
			cellTotal = totalRow.createCell(0);
			cellTotal.setCellValue("");
			cellTotal = totalRow.createCell(1);
			cellTotal.setCellValue("");
			cellTotal = totalRow.createCell(2);
			cellTotal.setCellValue("");
			cellTotal = totalRow.createCell(3);
			cellTotal.setCellValue("TOTAL");
			cellTotal = totalRow.createCell(4);
			cellTotal.setCellValue(totalmxn);
			cellTotal.setCellStyle(cellRightStyle);
			cellTotal = totalRow.createCell(5);
			cellTotal.setCellValue(totalusd);
			cellTotal.setCellStyle(cellRightStyle);
			cellUser1 = totalRow.createCell(6);
			cellUser1.setCellValue("");			
			
			Iterator<Cell> itTotal=	totalRow.cellIterator();
			while(itTotal.hasNext()) {
				Cell cell=itTotal.next();
				 cell.setCellStyle(myStyleTotal);
			}
		}

		
	}
	
}
