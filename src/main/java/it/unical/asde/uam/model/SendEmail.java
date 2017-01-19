package it.unical.asde.uam.model;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class SendEmail {

	public static final String EMAIL = "academicmanager@virgilio.it";
	public static final String PASSWORD = "enterprise";
	public static final String DEAR = "Dear";
	public static final String SUBJECT_REQUEST_REGISTATION = "UAM - Request registation";
	public static final String TEXT_ACCEPTED_REGISTRATION = "your registration request has been accepted.";
	public static final String TEXT_NOT_ACCEPTED_REGISTRATION = "your registration request has been rejected.";
	public static final String SIGNATURE = "Sincerely," + "\n" + "UAM - Unical Academic Manager";
	
	public SendEmail() {}
	
	@Async
	public boolean sendEmailRegistration(String email,String firstName,String lastName,String subject,String text) {
		Session session = authentication();
		return sendMessage(session, email, subject,
				DEAR + " " + firstName + " " + lastName + "," + "\n" + text + "\n" + SIGNATURE);
	}
	
	private Session authentication() {
		Properties props = new Properties();
		props.put("mail.smtp.host", "out.virgilio.it");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props,new javax.mail.Authenticator(){
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(EMAIL,PASSWORD);
			}
		});
		return session;
	}
	
	private boolean sendMessage(Session session, String email,String subject,String text) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EMAIL));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(email));
			message.setSubject(subject);
			message.setText(text);
			Transport.send(message);
		} 
		catch (MessagingException e) {
			return false;
		}
		return true;
	}
}