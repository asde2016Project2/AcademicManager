package it.unical.asde.uam.model;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

	private static final String EMAIL = "academicmanager@virgilio.it";
	private static final String PASSWORD = "enterprise";
	private static final String DEAR = "Dear";
	private static final String SUBJECT_REQUEST_REGISTATION = "UAM - Request registation";
	private static final String TEXT_ACCEPTED_REGISTRATION = "your registration request has been accepted." + "\n";
	private static final String TEXT_NOT_ACCEPTED_REGISTRATION = "your registration request has been rejected." + "\n";
	private static final String SIGNATURE = "Sincerely," + "\n" + "UAM - Unical Academic Manager";
	
	public static boolean sendEmailRegistration(String firstName,String lastName,String subject,String text) {
		Session session = authentication();
		return sendMessage(session, subject,"Dear " + firstName + " " + lastName + text + SIGNATURE);
	}
	
	private static Session authentication() {
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
	
	private static boolean sendMessage(Session session,String subject,String text) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(EMAIL));
			message.setRecipients(Message.RecipientType.TO,InternetAddress.parse("amatomarco1@gmail.com"));
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