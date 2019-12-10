package com.mx.base.services.impl;

import java.awt.Desktop;
import java.io.File;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;

import com.mx.base.models.catalog.Item;

@Service
public class TicketPurchasePDF {

	private String filePathtTickets = "/home/milla/purchase/tickets";
	
	public void makeTicketPDF(Item item, int year){				
		String fileName = "Ticket_"+item.getInputs().get(0).getLotNumber()+".pdf";
		File ticketFile = new File(filePathtTickets +"/"+year+"/"+ fileName);	
		this.makeFolderIfNotExist(year);
		this.makeFileIfNotExist(ticketFile, item);
		this.openPDFFIle(ticketFile);				
	}

	private void makeFolderIfNotExist(int year){
		File pathFile = new File(filePathtTickets +"/"+year);			
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}		
	}

	private void makeFileIfNotExist(File ticketFile, Item item){				
		if (!ticketFile.exists()) {
			try(PDDocument documentTicket = new PDDocument()){
	 			
				PDPage page = new PDPage(PDRectangle.A6);
				documentTicket.addPage(page);

				PDPageContentStream contentStream = new PDPageContentStream(documentTicket, page);
				
				String imagePath = getClass().getClassLoader().getResource("logo.png").getFile();
				PDImageXObject image = PDImageXObject.createFromFile(imagePath, documentTicket);
				contentStream.drawImage(image, 20, page.getMediaBox().getHeight() - 50, image.getWidth() / 3, image.getHeight() / 3);            

				contentStream.beginText();	      
				contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
				contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 80);
				contentStream.showText("QUANTITY: " + item.getQty());	            
				contentStream.endText();
	           
				contentStream.beginText();	            
				contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
				contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 100);
				contentStream.showText("LOT NUMBER: " + item.getInputs().get(0).getLotNumber());
				contentStream.endText();
	           
				contentStream.beginText();	            
				contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
				contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 120);
				contentStream.showText("PART NUMBER: " + item.getValue());
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
		try {
			if (ticketFile.exists()) {
				if (Desktop.isDesktopSupported()) {
					Desktop.getDesktop().open(ticketFile);
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
	  }		
    }
	
}
