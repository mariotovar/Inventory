package com.mx.base.services.impl;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;

import com.mx.base.models.catalog.TicketPDF;
import com.mx.base.util.services.DesktopAPI;

@Service
public class TicketPurchasePDF {

	private String filePathtTickets = "/home/milla/purchase/tickets";
	
	public void download(HttpServletResponse response,TicketPDF ticketPDF, int year) {
		try {
			String fileName = "Ticket_"+ticketPDF.getLotNumber()+".pdf";
			
			File fileToDownload = new File(filePathtTickets+"/"+year+"/" + fileName);

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
	public void makeTicketPDF(TicketPDF ticketPDF, int year){				
		String fileName = "Ticket_"+ticketPDF.getLotNumber()+".pdf";
		File ticketFile = new File(filePathtTickets +"/"+year+"/"+ fileName);	
		this.makeFolderIfNotExist(year);
		this.makeFileIfNotExist(ticketFile, ticketPDF);
		this.openPDFFIle(ticketFile);				
	}

	private void makeFolderIfNotExist(int year){
		File pathFile = new File(filePathtTickets +"/"+year);			
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}		
	}

	private void makeFileIfNotExist(File ticketFile, TicketPDF ticketPDF){				
		if (!ticketFile.exists()) {
			try(PDDocument documentTicket = new PDDocument()){
	 			
				//PDPage page = new PDPage(PDRectangle.A6);
	            PDPage page = new PDPage(new PDRectangle(180/*estos son los mm de largo*/ * ( 1 / (10 * 2.54f) *72), 50/*estos son los mm de alto*/ * ( 1 / (10 * 2.54f) *72)));

				documentTicket.addPage(page);

				PDPageContentStream contentStream = new PDPageContentStream(documentTicket, page);
				
				String imagePath = getClass().getClassLoader().getResource("logo.png").getFile();
				PDImageXObject image = PDImageXObject.createFromFile(imagePath, documentTicket);
				contentStream.drawImage(image, 300, page.getMediaBox().getHeight() - 50, image.getWidth() / 3, image.getHeight() / 3);            

				contentStream.beginText();	      
				contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
				contentStream.newLineAtOffset( 400, page.getMediaBox().getHeight() - 120);
				contentStream.showText("Quantity: " + ticketPDF.getQty());	            
				contentStream.endText();
	           
				contentStream.beginText();	            
				contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
				contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 40);
				contentStream.showText("Lot Number: " + ticketPDF.getLotNumber());
				contentStream.endText();
	           
				contentStream.beginText();	            
				contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
				contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 120);
				contentStream.showText("Part Number: " + ticketPDF.getValue());
				contentStream.endText();
	                       
				contentStream.close();		
				documentTicket.save(ticketFile);				       
			 }
			  catch(Exception ex){
				ex.printStackTrace();
			}		
		}		
	}	
	
	private void openPDFFIle(File ticketFile){	
		/*
		try {
			if (ticketFile.exists()) {
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(ticketFile);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
	  }		
	  */
	//	DesktopAPI.open(ticketFile);
	}
	
}
