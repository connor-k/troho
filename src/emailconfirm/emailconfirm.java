package emailconfirm;

import sql.UserDataManager;

import org.apache.commons.mail.*;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.URLDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

import java.net.MalformedURLException;
import java.net.URL;
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
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.googlemail.com");
		email.setSmtpPort(465);
		email.setAuthentication("troho.troco", "trohofor201");
		email.setSSLOnConnect(true);
		email.setFrom("troho.troco@gmail.com", "Troho");
		email.setSubject("Troho Confirmation");
		
		String urlstring = "href=http://localhost:8080/troho/Validation?key=" + key + "&id=" + facebookID; 
		String hyperlink = "<a " + urlstring + ">click here</a>";
		//hyperlink leading to localhost servlet 
		
		//HTML text message to be displayed in body of email 
		message = "<br><br>Welcome to troho by troco!"
				+ "<br><br>To confirm your email, please " + hyperlink + ".";
		
		URL url;	//this url will lead to an image of the troho logo
		try {
			url = new URL("https://bytebucket.org/troco/troco/raw/ffad4f08eb1510106b238d4e4365998a0cd25f55/WebContent/img/new-troho.png");
			String cid = email.embed(url, "Troho logo");
			//set size and source of image, as well as text message
			email.setHtmlMsg("<html><img style=width:160px height:200px src=\"cid:"+cid+"\">"+message+"</html>");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} 

		//text message is the alternative to the html message
		email.setTextMsg("There was an error vieweing this html message"); 
		email.addTo(toConfirm);
		email.send();
	}
	
	public static void sendTestEmail()
	{
		try {
			new emailconfirm("vitashubin@hotmail.com", "111");
			System.out.println(message);
		} catch (EmailException e) {
			e.printStackTrace();
		} 
	}
	
	public static boolean emailConfirmed(String code, String id)
	{
		//successfully verified email
		if (UserDataManager.verifyEmail(id, key))
		{
			System.out.println("User ID " + id + " confirmed with key " + code);
			return true; 
		}
		else
		{
			System.out.println("The confirmation could not be complete");
			return false; 
		}
	}

}
