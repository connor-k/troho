package emailconfirm;

import sql.UserDataManager;
import org.apache.commons.mail.*;
import java.util.UUID; 

public class emailconfirm {
	private static String toConfirm; //address that needs to be confirmed 
	private static String facebookID;  //users unique facebook identification 
	private static String key; 		//unique confirmation key 
	private static String message; 	//body of email 
	
	public emailconfirm(String to, String fbID) throws EmailException
	{
		toConfirm = to; 
		facebookID = fbID; 
		key = java.util.UUID.randomUUID().toString();	//generates random key for indiv user
		sendEmail();
		UserDataManager.setValidationKey(facebookID, key); 
	}
	
	public static void sendEmail() throws EmailException 
	{
		Email email = new SimpleEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthentication("troho.troco", "trohofor201");
		email.setSSLOnConnect(true);
		email.setFrom("troho.troco@gmail.com");
		email.setSubject("Troho Confirmation");
		message = "Welcome to troho by troco! Please confirm your email. "
				+ "\n \n Please confirm your email here: "
				+ "\n \n localhost:8080/Validation?key=" + key + "&id=" + facebookID; 
		email.addTo(toConfirm);
		email.send();
	}
	
	public static void sendTestEmail(String code, String id)
	{
		try {
			toConfirm = "vitashubin@hotmail.com"; 
			sendEmail(); 
		} catch (EmailException e) {
			e.printStackTrace();
		} 
	}
	
	public static void emailConfirmed(String code, String id)
	{
		UserDataManager.verifyEmail(id, key);
		//for testing purposes
		System.out.println("User ID " + id + " confirmed with key " + code); 
	}
}
