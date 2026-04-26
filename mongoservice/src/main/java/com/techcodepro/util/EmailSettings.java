package com.techcodepro.util;



import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
 
@Component
public class EmailSettings {
	
	@Autowired
	EmailProperties emailProperties;
	
	@Autowired
	PasswordProperties passwordProperties;
	
public void sendEmail(String emailId, String profileId) throws IOException {
	
		Properties props = new Properties();
		props.put("mail.smtp.host", emailProperties.getHost());
		props.put("mail.smtp.socketFactory.port", emailProperties.getPort());
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", emailProperties.getAuth());
		props.put("mail.smtp.port", emailProperties.getPort());
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("admin@techcodepro.com",
							passwordProperties.getAuthPass());
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
		
			message.setFrom(new InternetAddress("admin@techcodepro.com"));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(emailId));
			
			message.setSubject("User Password Change");
			message.setText("Dear User," +
					"\n\n This is the URL to change your password! \n\n Thanks" +
					"\nTechCodePro Team");
 
			System.out.println(message.getContent());
			
			Transport.send(message);
			
			
 
			System.out.println("Done");
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}