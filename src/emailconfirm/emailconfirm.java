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
	private static String toConfirm;	 //address that needs to be confirmed 
	private static String facebookID;  	 //users unique facebook identification 
	private static String key; 			 //unique confirmation key 
	private static String message; 		 //body of email 
	
	/**generates random UUID code used for verifying given email address
	 * @param to		email address of user that needs to be verified
	 * @param fbID		unique facebook id of user 
	 * @throws EmailException	if unable to sendEmail()
	 */
	public emailconfirm(String to, String fbID) throws EmailException
	{
		toConfirm = to; 
		facebookID = fbID; 
		key = java.util.UUID.randomUUID().toString();	//generates random key for indiv user
		sendEmail();
		
		//stores the unique UUID key to the particular user in the database
		UserDataManager.setValidationKey(facebookID, key); 
	}
	
	/**Email template
	 * Sends confirmation email to user with link to validation page
	 * @throws EmailException
	 */
	public static void sendEmail() throws EmailException 
	{
		HtmlEmail email = new HtmlEmail();
		email.setHostName("smtp.googlemail.com");	//sending from gmail
		email.setSmtpPort(465);
		email.setAuthentication("troho.troco", "trohofor201");	//troho gmail account where emails are sent from
		email.setSSLOnConnect(true);
		email.setFrom("troho.troco@gmail.com", "Troho");	//set name for sender email as "Troho"
		email.setSubject("Troho Confirmation");				//set subject line
		
		//hyperlink leading to localhost validation servlet 
		//parameters passed into servlet: key = generated UUID code; id = user fb id 
		String urlstring = "href=http://localhost:8080/troho/Validation?key=" + key + "&id=" + facebookID; 
		String hyperlink = "<a " + urlstring + ">click here</a>";
		
		//HTML text message to be displayed in body of email 
		message = "<br><br>Welcome to troho by troco!"
				+ "<br><br>To confirm your email, please " + hyperlink + ".";
		
		URL url;	//this url will lead to an image of the troho logo
		try {
			url = new URL("https://bitbucket.org/troco/troco/raw/ffad4f08eb1510106b238d4e4365998a0cd25f55/WebContent/img/new-troho.png");
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
	
	
	/**Compares unique validation key and facebook ID in database
	 * to UUID code and ID passed in through validation url 
	 * Sends paramaters to datamanager 
	 * @param code		unique UUID code read in from URL param
	 * @param id		unique user ID read in from URL param
	 * @return			returns true if email was validated 
	 */
	public static boolean emailConfirmed(String code, String id)
	{
		//successfully verified email
		if (UserDataManager.verifyEmail(id, code))
		{
			return true; 
		}
		else
		{
			System.out.println("The confirmation could not be complete");
			return false; 
		}
	}

}
