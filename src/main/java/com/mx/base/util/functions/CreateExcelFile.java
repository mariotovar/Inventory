package com.mx.base.util.functions;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

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

import com.mx.base.models.view.Inventory;
import com.mx.base.models.view.InventoryLot;

public class CreateExcelFile {
	
	public XSSFWorkbook createXslx(List<Inventory> inventory, List<InventoryLot> inventoryLot) {
		
		
		 // Blank workbook 
        XSSFWorkbook workbook = new XSSFWorkbook(); 
  
        
        String hojaProducts = "Products";
        String hojaLots = "Lots";
        
        // Create a blank sheet 
        XSSFSheet sheet = workbook.createSheet(hojaProducts);
        XSSFSheet sheet2 = workbook.createSheet(hojaLots);
  
        // This data needs to be written (Object[]) 
        Map<String, Object[]> data = new TreeMap<String, Object[]>(); 
        data.put("1", new Object[]{ "Part Number", "Part Description", "Stock Max.", "Stock Min.", "Inputs", "Outputs", "Stock"}); 
        
        Map<String, Object[]> dataLot = new TreeMap<String, Object[]>(); 
        dataLot.put("1", new Object[]{ "# Lot", "Part Number", "Part Description", "Cost USD", "Cost MXN", "List USD", "List MXN", "BIN", "Supplier", "Inputs", "Outputs", "Stock"}); 
        
        int index = 2;
        for (Inventory inventario : inventory) {
        	data.put(Integer.toString(index++), new Object[]{inventario.getValue(), inventario.getDescription(), inventario.getStockMax(), inventario.getStockMin(), inventario.getInputs(), inventario.getOutputs(), inventario.getStock() });
        }
         
        DecimalFormat formato = new DecimalFormat("#.00");
        
        for (InventoryLot lots : inventoryLot) {
        	String costUSD =  String.valueOf(formato.format(lots.getCostUSD()));
        	String costMXN =  String.valueOf(formato.format(lots.getCostMXN()));
        	String listUSD =  String.valueOf(formato.format(lots.getListUSD()));
        	String listMXN =  String.valueOf(formato.format(lots.getListMXN()));
        	dataLot.put(Integer.toString(index++), new Object[]{lots.getLotNumber(), lots.getValue(), lots.getDescription(), costUSD, costMXN, listUSD, listMXN, lots.getBin(), lots.getSupplier(), lots.getInputs(), lots.getOutputs(), lots.getStock()});
        }
        
        //estilo de la hoja
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
		
		//Color amarillo
//		XSSFCellStyle cellStyle = workbook.createCellStyle();
//		cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 192, 0)));
//		cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		XSSFCellStyle cellLeftStyle = workbook.createCellStyle();
		cellLeftStyle.setAlignment(HorizontalAlignment.RIGHT);
        
        // Iterate over data and write to sheet 
        Set<String> keyset = data.keySet(); 
        int rownum = 0; 
        for (String key : keyset) { 
            // this creates a new row in the sheet 
        	sheet.setColumnWidth(rownum, 4800);
            Row row = sheet.createRow(rownum++);
            
            Object[] objArr = data.get(key); 
            int cellnum = 0; 
            for (Object obj : objArr) { 
                // this line creates a cell in the next column of that row 
                Cell cell = row.createCell(cellnum++); 
                
                if (rownum==1 ) {
                	System.out.println(cell.getColumnIndex());
                	System.out.println(row.getRowNum());
                	cell.setCellStyle(myStyle);	
				}
                
                if (rownum>1 && cellnum>=5 ) {
//                	cell.setCellStyle(cellStyle);	
                }
                
                System.out.println();
                if (obj instanceof String) 
                    cell.setCellValue((String)obj); 
                
                else if (obj instanceof Integer) 
                    cell.setCellValue((Integer)obj); 
            } 
        } 
        
        // Iterate over data and write to sheet 2
        Set<String> keysetLots = dataLot.keySet(); 
        int rownumLots = 0; 
        for (String key : keysetLots) { 
            // this creates a new row in the sheet 
        	sheet2.setColumnWidth(rownumLots, 4800);
            Row row = sheet2.createRow(rownumLots++);
            
            Object[] objArr = dataLot.get(key); 
            int cellnumLot = 0; 
            for (Object obj : objArr) { 
                // this line creates a cell in the next column of that row 
                Cell cell = row.createCell(cellnumLot++); 
                
                if (rownumLots==1 ) {
                	System.out.println(cell.getColumnIndex());
                	System.out.println(row.getRowNum());
                	cell.setCellStyle(myStyle);	
				}
                
//                if (rownumLots>1 && cellnumLot>=4 ) {
//                	cell.setCellStyle(cellStyle);	
//                }
                
                if (rownumLots>1 && cellnumLot>=4 && cellnumLot<8) {
                	cell.setCellStyle(cellLeftStyle);	
                }

                if (obj instanceof String) 
                    cell.setCellValue((String)obj); 
                
                else if (obj instanceof Integer) 
                    cell.setCellValue((Integer)obj); 
            } 
        }
        
        try { 

            System.out.println("xlsx written successfully on disk.");
            return workbook;
        } 
        catch (Exception e) { 
            e.printStackTrace(); 
            return null;
        } 
			
	}

	
}
