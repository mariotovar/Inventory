package com.mx.base.util.services.impl;

import java.io.IOException;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.base.models.catalog.Email;
import com.mx.base.util.functions.ParameterConfig;
import com.mx.base.util.services.EmailService;



@Service
public class EmailServiceImpl implements EmailService {

	@Autowired
	private ParameterConfig parameterConfig;
	
	@Override
	public boolean sendEmail(Email email) {

	    final String username = "aeroconsumiblesyorlib@milla40.net";
        final String password = "a_)%CTGdZuCI";

        Properties prop = new Properties();
		prop.put("mail.smtp.host", "milla40.net");
        prop.put("mail.smtp.port", "26");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
                        
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setSubject(email.getSubject());
            message.setFrom(new InternetAddress(""+username));
            message.addRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getTo()));
            if(email.getCc1()!=null&&!email.getCc1().equals("")){
            	message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(email.getCc1()));                
            }
            if(email.getCc2()!=null&&!email.getCc2().equals("")){
            	message.addRecipients(Message.RecipientType.CC, InternetAddress.parse(email.getCc2()));	
            }
            if(parameterConfig.getCCEmail()!=null) {
            	message.addRecipients(Message.RecipientType.BCC, InternetAddress.parse(parameterConfig.getCCEmail()));
            }
            	
            MimeBodyPart imagePart = new MimeBodyPart();            
			imagePart.setHeader("Content-ID", "logo");
			imagePart.setDisposition(MimeBodyPart.INLINE);
			imagePart.attachFile(getClass().getClassLoader().getResource("logo.png").getFile());				
							            
		    BodyPart htmlPart = new MimeBodyPart();
		    htmlPart.setContent( email.getBody(), "text/html; charset=utf-8");			
			
			MimeMultipart multiParte = new MimeMultipart();
			multiParte.addBodyPart(htmlPart);
			multiParte.addBodyPart(imagePart);
			
			message.setContent(multiParte);            

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }
        		
		return false;
	}

}
