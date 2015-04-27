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

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SendUserVerificationEmail
 */
@WebServlet("/SendUserVerificationEmail")
public class SendUserVerificationEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fbID =request.getParameter("fbID"); 
		String uscEmail = request.getParameter("uscEmail");
		
		UserDataManager.setEmail(fbID, uscEmail);
		
		try {
			System.out.println("In confirm");
			emailconfirm confirm = new emailconfirm(uscEmail, fbID);
		} catch (EmailException e) {
			e.printStackTrace();
		}
	}

}
