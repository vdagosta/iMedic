
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail{
	 
	
	/* WARNING: This class will work only if the javax.mail.jar file has been imported   
	 * 
	 * The SendEmail class allows the system to send automatic email using a Gmail server. A system email account has been 
	 * created on gmail.com to send email.   
	 */
	
	// Properties necessary to send email
	static Properties mailProp;
	static Session mailSess;
	static MimeMessage mailMessage;
	
	// To check if email was sent 
	private boolean sent;
	
	// Data necessary to access server and account to send email.
	private String systemSess = "smtp.gmail.com";				/* ====> Gmail server */
	private String systemUser = "imedic.system";				/* ====> System's Gmail account username */
	private String systemPass = "4VGLDT47";						/* ====> System's' Gmail account password */
	
	
	// Empty Constructor to create class
	SendEmail() {	
	}
	
	// The method send(String,String) sends an email to an email address if the user is connected to the Internet
	public void send(String emailBody, String emailAddress) throws AddressException, MessagingException {
		
		
		// ==== STEP 1 ==== MAIL SERVER PROPERTIES
		mailProp = System.getProperties();									/* ====> Retrieve current System properties */
		mailProp.put("mail.smtp.port", "587");								/* ====> Add smtp port to mail properties */
		mailProp.put("mail.smtp.auth", "true");								/* ====> Set authentication to true on mail properties */
		mailProp.put("mail.smtp.starttls.enable", "true");					/* ====> Switch to TLS protected connection */
	
				
		// ==== STEP 2 ==== MAIL SESSION									   ====> Implements Transport
		mailSess = Session.getDefaultInstance(mailProp, null);				/* ====> Get default Session */
		
		mailMessage = new MimeMessage(mailSess);							/* ====> Create MIME style email */
		mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddress));		/*====> Add rceiver's recipient */
		mailMessage.setSubject("I-Medic Automated Message - Do Not Reply.");						/*====> Set subject */
		mailMessage.setContent(emailBody, "text/html");												/*====> Set email body */
 
		// ==== STEP 3 ==== SEND MESSAGE
		Transport transport = mailSess.getTransport("smtp");				/* ====> Get a Transport object that implements the smtp protocol. */
		
		
		// Try to send message, if succeds than sent is set to true, else is set to false.
		try{
		transport.connect(systemSess, systemUser, systemPass);
		transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
		transport.close();
			sent = true;
		}
		catch(Exception blocked){
			sent = false;
		}
	}
	
	
	// The isSent() method returns a boolean that specifies if the email was sent succesfully or not
	public boolean isSent(){	
		return sent;
	}
	
} // END CLASS
