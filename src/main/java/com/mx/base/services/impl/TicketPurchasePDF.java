package com.mx.base.services.impl;

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

@Service
public class TicketPurchasePDF {

	private String filePathtTickets = "/home/milla/purchase/tickets";
	
	public void download(HttpServletResponse response,TicketPDF ticketPDF, int year) {
		try {
			String fileName = "Ticket_"+ticketPDF.getLotNumber()+".pdf";
			System.out.println(filePathtTickets+"/"+year+"/" + fileName);
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
		this.makeFile(ticketFile, ticketPDF);
		//this.openPDFFIle(ticketFile);				
	}

	private void makeFolderIfNotExist(int year){
		File pathFile = new File(filePathtTickets +"/"+year);			
		if (!pathFile.exists()) {
			pathFile.mkdirs();
		}		
	}

	private void makeFile(File ticketFile, TicketPDF ticketPDF){
		
		if(ticketFile.exists()) {
			ticketFile.delete();
			System.out.println("Delete file: " + ticketFile.getName());
		}
		
		try(PDDocument documentTicket = new PDDocument()){
 			
            PDPage page = new PDPage(new PDRectangle(95/*estos son los mm de largo*/ * ( 1 / (10 * 2.54f) *72), 66/*estos son los mm de alto*/ * ( 1 / (10 * 2.54f) *72)));
            //PDPage page = new PDPage(PDRectangle.A6);
            documentTicket.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(documentTicket, page);	      
        	String imagePath = getClass().getClassLoader().getResource("logo.png").getFile();
			PDImageXObject image = PDImageXObject.createFromFile(imagePath, documentTicket);
			contentStream.drawImage(image, 10, page.getMediaBox().getHeight() - 50, image.getWidth() / 3, image.getHeight() / 3);
       
			contentStream.beginText();	      
	        contentStream.setFont(PDType1Font.TIMES_BOLD, 10);
	        contentStream.newLineAtOffset( 05, page.getMediaBox().getHeight() - 70);
	        contentStream.showText("orib12@hotmail.com");	            
	        contentStream.endText();
	            
	        contentStream.beginText();	      
	        contentStream.setFont(PDType1Font.TIMES_BOLD, 10);
	        contentStream.newLineAtOffset( 110, page.getMediaBox().getHeight() - 70);
	        contentStream.showText("(722) 338 6438");	            
	        contentStream.endText();
	            
	            
	        contentStream.beginText();	      
	        contentStream.setFont(PDType1Font.TIMES_BOLD, 10);
	        contentStream.newLineAtOffset( 190, page.getMediaBox().getHeight() - 70);
	        contentStream.showText("(722) 361 4669");	            
	        contentStream.endText();
            
	        contentStream.beginText();	      
            contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
            contentStream.newLineAtOffset( 05, page.getMediaBox().getHeight() - 80);
            contentStream.showText("________________________________");	            
            contentStream.endText();

            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
            contentStream.newLineAtOffset( 05, page.getMediaBox().getHeight() - 95);
            contentStream.showText("PART # :");
            contentStream.endText();
            
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
            contentStream.newLineAtOffset( 75, page.getMediaBox().getHeight() - 95);
            contentStream.showText(""+ticketPDF.getPartNumber());
            contentStream.endText();
            
            
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.newLineAtOffset( 05, page.getMediaBox().getHeight() - 110);
            contentStream.showText("Description :");
            contentStream.endText();
           
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.newLineAtOffset( 75, page.getMediaBox().getHeight() - 110);
            contentStream.showText(""+ticketPDF.getDescription());
            contentStream.endText();
            
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.newLineAtOffset( 05, page.getMediaBox().getHeight() - 125);
            contentStream.showText("LOC :");
            contentStream.endText();
            
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.newLineAtOffset( 75, page.getMediaBox().getHeight() - 125);
            contentStream.showText( ""+ticketPDF.getLoc());
            contentStream.endText();
            
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.newLineAtOffset( 05, page.getMediaBox().getHeight() - 140);
            contentStream.showText("PO #:" + "");
            contentStream.endText();
      
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.newLineAtOffset( 75, page.getMediaBox().getHeight() - 140);
            contentStream.showText(""+ticketPDF.getPurchseOrder());
            contentStream.endText();
            
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.newLineAtOffset( 05, page.getMediaBox().getHeight() - 155);
            contentStream.showText("Receive :" );
            contentStream.endText();

            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.newLineAtOffset( 75, page.getMediaBox().getHeight() - 155);
            contentStream.showText(ticketPDF.getReceive());
            contentStream.endText();
//	            
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.newLineAtOffset( 05, page.getMediaBox().getHeight() - 170);
            contentStream.showText("QTY :");
            contentStream.endText();
         
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 12);
            contentStream.newLineAtOffset( 75, page.getMediaBox().getHeight() - 170);
            contentStream.showText(""+ticketPDF.getQty());
            contentStream.endText();
            
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
            contentStream.newLineAtOffset( 05, page.getMediaBox().getHeight() - 185);
            contentStream.showText("LOT #:");
            contentStream.endText();
            
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 14);
            contentStream.newLineAtOffset( 75, page.getMediaBox().getHeight() - 185);
            contentStream.showText(""+ticketPDF.getLotNumber());
            contentStream.endText();
            
            contentStream.close();
			documentTicket.save(ticketFile);			       
		 }
		  catch(Exception ex){
			ex.printStackTrace();
		}		
	}	
	
}
