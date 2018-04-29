/*
 * Copyright © 2018 Sarah Allen
 */
package responses;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import javax.activation.*;

public class Contact implements Response{
	
	String email;
	String number;

	
	public Contact(String e, String n){
		email = e;
		number = n;
	}
	
	public void go(){
		if(!email.equals("")){
			  // Recipient's email ID needs to be mentioned.
		      String to = email;

		      // Sender's email ID needs to be mentioned
		      String from = "";

		      // Assuming you are sending email from localhost
		      String host = "localhost";

		      // Get system properties
		      Properties properties = System.getProperties();

		      // Setup mail server
		      properties.setProperty("mail.smtp.host", host);

		      // Get the default Session object.
		      Session session = Session.getDefaultInstance(properties);

		      try {
		         // Create a default MimeMessage object.
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		         // Set Subject: header field
		         message.setSubject("Interface Alert");

		         // Now set the actual message
		         message.setText("Alert from Interface");

		         // Send message
		         Transport.send(message);
		         System.out.println("Sent message successfully....");
		      } catch (MessagingException mex) {
		    	  System.out.print("Message not sent");
		         //mex.printStackTrace();
		      }
		   
		}
		
		if(!number.equals("")){
			//call or text instructions
		}
		
	}

}
