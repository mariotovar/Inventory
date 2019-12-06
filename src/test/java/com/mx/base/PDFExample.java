package com.mx.base;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class PDFExample {

	public static void main(String[] args) {

		 try{
			 
			PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A6);
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Image
            String imagePath = PDFExample.class.getClassLoader().getResource("logo.png").getFile();
            PDImageXObject image = PDImageXObject.createFromFile(imagePath, document);
            contentStream.drawImage(image, 20, page.getMediaBox().getHeight() - 50, image.getWidth() / 3, image.getHeight() / 3);            
            
            // Text
            contentStream.beginText();	      
            contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 80);
            contentStream.showText("QUANTITY: " + "2");	            
            contentStream.endText();
            
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 100);
            contentStream.showText("LOT NUMBER: " + "L706");
            contentStream.endText();
            
            contentStream.beginText();	            
            contentStream.setFont(PDType1Font.TIMES_BOLD, 16);
            contentStream.newLineAtOffset( 20, page.getMediaBox().getHeight() - 120);
            contentStream.showText("PART NUMBER: " + "MATRICULA 001");
            contentStream.endText();
                        
            contentStream.close();

            document.save("document.pdf");
        
		 }
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
