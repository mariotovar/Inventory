package com.mx.base.util.functions;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.apache.poi.ss.usermodel.Cell;
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
		XSSFSheet sheet;
		
		String[] columns = { "PK", "Type", "Date", "Amount MXN", "Amount USD", "User 1", "User 2"};
		
		
		Date date = new Date();
        String strDateFormat = "dd-MM-yyyy HH:mm:ss"; 
        SimpleDateFormat objSDF = new SimpleDateFormat(strDateFormat);
 
        DecimalFormat formato = new DecimalFormat("#.00");
        
        String saleDate;
        String amountMXN;
        String amountUSD;
        String reporMonth;
        String reportYear;
		
		for(String hoja: tabs){
			
			String[] parts = hoja.split("-");
			String tabMonth = parts[0]; // mes de la hoja
			String tabYear = parts[1]; // año de la hoja
			
			sheet = workbook.createSheet(hoja);
			
			Row headerRow = sheet.createRow(0);
			
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
			
			XSSFCellStyle cellLeftStyle = workbook.createCellStyle();
			cellLeftStyle.setAlignment(HorizontalAlignment.RIGHT);
			
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
					amountMXN =  String.valueOf(formato.format(sale.getAmountMXN()));
					amountUSD =  String.valueOf(formato.format(sale.getAmountUSD()));
					
					 Row row = sheet.createRow(rowNum++);
					 row.createCell(0).setCellValue(sale.getReportPK());
					 row.createCell(1).setCellValue( sale.getTypeOp());
					 row.createCell(2).setCellValue( saleDate);
					 row.createCell(3).setCellValue( amountMXN);
					 row.createCell(4).setCellValue( amountUSD);
					 row.createCell(5).setCellValue( sale.getUser());
					 row.createCell(6).setCellValue( sale.getUser());
					 
					 sheet.setColumnWidth(rowNum, 4900);
					if (sale.getType().equals("IN")) {
						 totalmxn += sale.getAmountMXN();
							totalusd += sale.getAmountUSD();
					}else{
						 totalmxn -= sale.getAmountMXN();
							totalusd -= sale.getAmountUSD();
					}
					
					if (sale.getUser().equals("USER1")) {
						totalbyUserMXN1 += sale.getAmountMXN();
						totalbyUserUSD1 += sale.getAmountUSD();
					}else {
						totalbyUserMXN2 += sale.getAmountMXN();
						totalbyUserUSD2 += sale.getAmountUSD();
					}					 
					
				}
				
			}
			
			String[] totales = { "", "", "", "TOTAL: "+String.valueOf(formato.format(totalmxn)) , "TOTAL: "+ String.valueOf(formato.format(totalusd)), "TOTAL MXN: "+String.valueOf(formato.format(totalbyUserMXN1)), "TOTAL MXN: "+String.valueOf(formato.format(totalbyUserMXN2))};
			Row totalRow = sheet.createRow(rowNum);
			for(int i = 0; i < totales.length; i++) {
	            Cell cell = totalRow.createCell(i);
	            cell.setCellValue(totales[i]);
	            cell.setCellStyle(boldStyle);
	        }
			
			String[] totalesUSD = { "", "", "", "" , "", "TOTAL USD: "+String.valueOf(formato.format(totalbyUserUSD1)), "TOTAL USD: "+String.valueOf(formato.format(totalbyUserUSD2))};
			Row totalRowUSD = sheet.createRow(rowNum+1);
			for(int i = 0; i < totalesUSD.length; i++) {
	            Cell cell = totalRowUSD.createCell(i);
	            cell.setCellValue(totalesUSD[i]);
	            cell.setCellStyle(boldStyle);
	        }
			
//			for(int i = 0; i < columns.length; i++) {
//	            sheet.autoSizeColumn(i);
//	        }

		}
		
		
		//HOJA ANUAL
		XSSFSheet anualSheet;
		anualSheet = workbook.createSheet("ANUAL");
		
		String[] anualColumns = { "YEAR", "MONTH", "TYPE", "MXN", "USD", "USER 1", "USER 2"};
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
		
//		int rowNum = 1;
//		for(SaleReport sale: salesReport){
//			amountMXN =  String.valueOf(formato.format(sale.getAmountMXN()));
//			amountUSD =  String.valueOf(formato.format(sale.getAmountUSD()));
//			 Row row = anualSheet.createRow(rowNum++);
//			 row.createCell(0).setCellValue(sale.getYear());
//			 row.createCell(1).setCellValue( sale.getMonth());
//			 row.createCell(2).setCellValue( sale.getTypeOp());
//			 row.createCell(3).setCellValue( amountMXN);
//			 row.createCell(4).setCellValue( amountUSD);
//			 row.createCell(5).setCellValue( sale.getUser());
//			 row.createCell(6).setCellValue( sale.getUser());		
//			 
//			 anualSheet.setColumnWidth(rowNum, 4900);
//		}
		
		
		double total_in_mxn = 0, total_out_mxn = 0, totalInbyUser1MXN = 0 , totalInbyUser2MXN = 0, totalOutbyUser1MXN = 0 , totalOutbyUser2MXN = 0;
		double total_in_usd = 0, total_out_usd = 0, totalInbyUser1USD = 0 , totalInbyUser2USD = 0, totalOutbyUser1USD= 0 , totalOutbyUser2USD = 0;
		
		Set<AnualReport> ts = new TreeSet<AnualReport>();
		
		for(SaleReport sale: salesReport){
			ts.add(new AnualReport(sale.getYear(), sale.getMonth(), sale.getTypeOp(),sale.getAmountMXN(), sale.getAmountUSD(), sale.getUser()));
			
			if (sale.getType().equals("IN")) {
				total_in_mxn += sale.getAmountMXN();
				total_in_usd += sale.getAmountUSD();
				
				if (sale.getUser().equals("USER1")) {
					totalInbyUser1MXN += sale.getAmountMXN();
					totalInbyUser1USD += sale.getAmountUSD();
				}else {
					totalInbyUser2MXN += sale.getAmountMXN();
					totalInbyUser2USD += sale.getAmountUSD();
				}
				
			}else{
				total_out_mxn += sale.getAmountMXN();
				total_out_usd += sale.getAmountUSD();
				
				if (sale.getUser().equals("USER1")) {
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
	           System.out.println("map año" + e.getYear());
	           System.out.println("map mes" + e.getMonth());
	           System.out.println("map tipo" + e.getType());
	           System.out.println("map mxn" + e.getAmountMXN());
	           
				amountMXN =  String.valueOf(formato.format(e.getAmountMXN()));
				amountUSD =  String.valueOf(formato.format(e.getAmountUSD()));
				 Row row = anualSheet.createRow(rowNum++);
				 row.createCell(0).setCellValue(e.getYear());
				 row.createCell(1).setCellValue( e.getMonth());
				 row.createCell(2).setCellValue( e.getType());
				 row.createCell(3).setCellValue( amountMXN);
				 row.createCell(4).setCellValue( amountUSD);
				 row.createCell(5).setCellValue( e.getUser());
				 row.createCell(6).setCellValue( e.getUser());		
				 
				 anualSheet.setColumnWidth(rowNum, 4900);
	     }
		
	    double totalIn = total_in_mxn + total_in_usd;
	    double totalInUser1 = totalInbyUser1MXN + totalInbyUser1USD;
	    double totalInUser2 = totalInbyUser2MXN + totalInbyUser2USD;    
	    
		String[] total_in = { "", "", "", "", "TOTAL IN: "+String.valueOf(formato.format(totalIn)),  "TOTAL IN USER1: "+String.valueOf(formato.format(totalInUser1)), "TOTAL IN USER2: "+String.valueOf(formato.format(totalInUser2))};
		Row total_in_row = anualSheet.createRow(rowNum);
		for(int i = 0; i < total_in.length; i++) {
            Cell cell = total_in_row.createCell(i);
            cell.setCellValue(total_in[i]);
            cell.setCellStyle(boldStyle);
        }
		
	    double totalOut = total_out_mxn + total_out_usd;
	    double totalOutUser1 = totalOutbyUser1MXN + totalOutbyUser1USD;
	    double totalOutUser2 = totalOutbyUser2MXN + totalOutbyUser2USD;
	    
		String[] total_out = { "", "", "", "", "TOTAL OUT: "+String.valueOf(formato.format(totalOut)),  "TOTAL OUT USER1: "+String.valueOf(formato.format(totalOutUser1)), "TOTAL OUT USER2: "+String.valueOf(formato.format(totalOutUser2))};
		Row total_out_row = anualSheet.createRow(rowNum+1);
		for(int i = 0; i < total_out.length; i++) {
            Cell cell = total_out_row.createCell(i);
            cell.setCellValue(total_out[i]);
            cell.setCellStyle(boldStyle);
        }
		
		
		double totalUser1 = totalInUser1 + totalOutUser1;
		double totalUser2 = totalInUser2 + totalOutUser2;
		
		String[] total_user = { "", "", "", "", "", "TOTAL USER1: "+String.valueOf(formato.format(totalUser1)), "TOTAL USER2: "+String.valueOf(formato.format(totalUser2))};
		Row total_user_row = anualSheet.createRow(rowNum+2);
		for(int i = 0; i < total_user.length; i++) {
            Cell cell = total_user_row.createCell(i);
            cell.setCellValue(total_user[i]);
            cell.setCellStyle(boldStyle);
        }
	    
	    
		 try { 
	            System.out.println("xlsx written successfully.");
	            return workbook;
	        } 
	        catch (Exception e) { 
	            e.printStackTrace(); 
	            return null;
	        } 
	}

}
