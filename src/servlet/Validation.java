package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import sql.UserDataManager;
import emailconfirm.emailconfirm; 

@WebServlet("/Validation")
public class Validation extends HttpServlet {

	public Validation()
	{
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		//get parameters passed into url 
		String key = request.getParameter("key");	//random UUID code 
		String id = request.getParameter("id");		//unique user fb ID
		
		//cross reference parameters with those stored in user database
		boolean valid = emailconfirm.emailConfirmed(key, id);	
		
		response.setContentType("text/html");
	    PrintWriter out = response.getWriter();
	    
	    //Display whether or not email was confirmed
	    String title; 
	    if(valid)
	    	title = "Email confirmed!"; 
	    else
	    	title = "Email or verification key not valid."; 
	    out.println("<h1>"+title+"</h1>"); 		//prints page header 
	    
	    //hyperlink to help redirect user back to home page 
	    String urlstring = "href=http://localhost:8080/troho/index.jsp"; 
		String hyperlink = "<a " + urlstring + ">click here</a>";
	   
		String redir = "You will be redirected shortly"; 
	    redir += "<br>If not, please " + hyperlink; 
	    out.println(redir); 
	    
	    //after 6 seconds, redirects user to home page 
		response.setHeader("Refresh", "6; URL=http://localhost:8080/troho/index.jsp");
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{ 
		
	}


}