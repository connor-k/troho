package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.mail.EmailException;

import emailconfirm.emailconfirm;
import sql.UserDataManager;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/CreateUser")
public class CreateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the printwriter object from response to write the required json object to the output stream
		
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();      
		
		//Create String to send in response to get request
		String jsonObject = "{\"test\": \"Succesfull Ajax Call\"}";
		
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(jsonObject);
		out.flush();
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println(request);
		String name=request.getParameter("name"); 
		String imgURL =request.getParameter("url"); 
		String fbID =request.getParameter("fbID"); 
		String email = request.getParameter("email");
		System.out.println(email);
		if(UserDataManager.createUser(name, email, imgURL, fbID) != null) { 
			try {
				System.out.println("In confirm");
				emailconfirm confirm = new emailconfirm(email, fbID);
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}
	}

}
