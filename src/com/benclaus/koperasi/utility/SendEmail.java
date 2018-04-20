/**
 * 
 */
package com.benclaus.koperasi.utility;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;

/**
 * @author 
 *
 */
public class SendEmail {

	private static Logger log = Logger.getLogger(SendEmail.class);
	
	public static boolean doSendEmail(String host, String from, String messageBody, String messageSubject, String messageRecip, List<String> messageCc) throws AddressException, MessagingException  {
		boolean result = false;
		Session session;
		Message mesg;
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth","false");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.port", "25");
		session = Session.getDefaultInstance(props, null);
		session.setDebug(true);
		
		//try {
			mesg = new MimeMessage(session);
			mesg.setFrom(new InternetAddress(from));
			
			InternetAddress toAddress = new InternetAddress(messageRecip);
		    mesg.addRecipient(Message.RecipientType.TO, toAddress);
		    
		    if (messageCc !=null && messageCc.size() > 0) {
		    	for(String cc : messageCc) {
			    	InternetAddress ccAddress = new InternetAddress(cc);
			    	mesg.addRecipient(Message.RecipientType.CC, ccAddress);
		    	}
		    }
		    
		    mesg.setSubject(messageSubject);
		    //mesg.setText(message_body);
		    mesg.setContent(messageBody, "text/html");
		    
		    Transport tr = session.getTransport("smtp");
		    tr.connect();
		    mesg.saveChanges();
		    tr.sendMessage(mesg, mesg.getAllRecipients());
		    result = true;
		    tr.close();
		return result;
	}
	
	public static void main(String[] args) {
		try {
			boolean result = SendEmail.doSendEmail("courier.gunungsewu.com", "noreply@gunungsewu.com", "Halo halo bandung", "Send email test", "lambok.sianturi@gunungsewu.com", null);
			System.out.println(result);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

}
