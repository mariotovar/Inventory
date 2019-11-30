package com.mx.base.controllers.business;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class CreateXslx {
	// import statements 
	public static void main(String[] args) { 
//		ExcelList();
		ExcelMap();
    }
	
	  private static void ExcelList() {
		  String nombreArchivo = "Inventory.xlsx";
	        
	        String hojaProducts = "Products";
	        String hojaLots = "Lots";
	        
	        XSSFWorkbook libro = new XSSFWorkbook();
	        
	        XSSFSheet hoja1 = libro.createSheet(hojaProducts);
	        XSSFSheet hoja2 = libro.createSheet(hojaLots);
	        
	        
	        // Cabecera de la hoja de excel
	        List listA = new ArrayList();

	        listA.add("Part Number");
	        listA.add("Part Description");
	        listA.add("Stock Max.");
	 
	        
//	        String[] header = new String[] {"NOMBRE", "TELEFONO", "EMAIL"};
	        
//	        hoja1.setColumnWidth(1, 4800);
//	        hoja1.setColumnWidth(0, 4800);
	     
	        
	        // Contenido de la hoja de excel
	        List contenido = new ArrayList();
	        contenido.add("element 0");
	        contenido.add("element 1");
	        contenido.add("element 2");
	        
	        String element0 = (String) contenido.get(0);
	        String element1 = (String) contenido.get(1);
	        String element3 = (String) contenido.get(2);

	        String[][] document = new String[][] {
	            {element0, element1, element3},
	            {"Laura L", "4324251", "laural@prueba.es"},
	            {"Juan H", "7363153", "juanh@prueba.es"}
	        };
	        
	        //estilo de la hoja
	        XSSFCellStyle myStyle = libro.createCellStyle();
	        myStyle.setAlignment(HorizontalAlignment.CENTER);
			myStyle.setVerticalAlignment(VerticalAlignment.CENTER);
	        myStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(0, 32, 96)));
	        myStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	        
			XSSFFont font = (XSSFFont) libro.createFont();
			font.setFontHeightInPoints((short) 11);
			font.setFontName("Calibri");
			font.setColor(new XSSFColor(new java.awt.Color(255, 255, 255)));
			font.setBold(true);
			myStyle.setFont(font);
	        
	        // Generar los datos para el documento
	        for(int i = 0 ; i <= document.length ; i++) {
	            XSSFRow row = hoja1.createRow(i); // Se crea la fila
	            hoja1.setColumnWidth(i, 4800);
//	            row.setHeight(( short ) 0x249);
	            for(int j = 0 ; j < listA.size() ; j++) {
	                if(i == 0) { // Para la cabecera
	                    XSSFCell cell = row.createCell(j); // Se crean las celdas pra la cabecera
	                    cell.setCellValue((String)listA.get(j)); // Se añade el contenido
	                   
	                    cell.setCellStyle(myStyle);
	                } else {
	                    XSSFCell cell = row.createCell(j); // Se crean las celdas para el contenido
	                    cell.setCellValue(document[i - 1][j]); // Se añade el contenido
	                }
	            }
	        }
	        
	        // Crear el archivo
	        try (OutputStream fileOut = new FileOutputStream(nombreArchivo)){
	            System.out.println("SE CREO EL EXCEL");
	            libro.write(fileOut);
	        } catch(IOException e) {
	            e.printStackTrace();
	        }
	  }
	  
	  private static void ExcelMap() {
		    // Blank workbook 
	        XSSFWorkbook workbook = new XSSFWorkbook(); 
	  
	        // Create a blank sheet 
	        XSSFSheet sheet = workbook.createSheet("student Details"); 
	  
	        // This data needs to be written (Object[]) 
	        Map<String, Object[]> data = new TreeMap<String, Object[]>(); 
	        data.put("1", new Object[]{ "ID", "NAME", "LASTNAME", ""}); 
	        data.put("2", new Object[]{ "hola", "Pankaj", "Kumar", "hola" }); 
	        data.put("3", new Object[]{ "hola2", "Prakashni", "Yadav" }); 
	        data.put("4", new Object[]{ "hola3", "Ayan", "Mondal" }); 
	        data.put("5", new Object[]{ "hola4", "Virat", "kohli" }); 
	  
	        
	        List contenido = new ArrayList();
	        contenido.add("element 0");
	        contenido.add("element 1");
	        contenido.add("element 2");
	        
	        String element0 = (String) contenido.get(0);
	        String element1 = (String) contenido.get(1);
	        String element3 = (String) contenido.get(2);
	        
	        int index = 5;
	        for (Object object : contenido) {
				System.out.println(object);
				
				 data.put(Integer.toString(index++), new Object[]{ index++, object, "kohli" });
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
			
			XSSFCellStyle cellStyle = workbook.createCellStyle();
			cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(255, 192, 0)));
			cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			
			
	        // Iterate over data and write to sheet 
	        Set<String> keyset = data.keySet(); 
	        int rownum = 0; 
	        for (String key : keyset) { 
	            // this creates a new row in the sheet 
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
	                
	                if (rownum>1 && cellnum==3 ) {
	                	cell.setCellStyle(cellStyle);	
	                }
	                System.out.println();
	                if (obj instanceof String) 
	                    cell.setCellValue((String)obj); 
	                
	                else if (obj instanceof Integer) 
	                    cell.setCellValue((Integer)obj); 
	            } 
	        } 
	        try { 
	            // this Writes the workbook gfgcontribute 
	            FileOutputStream out = new FileOutputStream(new File("gfgcontribute.xlsx")); 
	            workbook.write(out); 
	            out.close(); 
	            System.out.println("gfgcontribute.xlsx written successfully on disk."); 
	        } 
	        catch (Exception e) { 
	            e.printStackTrace(); 
	        } 
	    }

}
