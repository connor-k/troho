package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.UserDataManager;

@WebServlet("/SetUserPreferences")
public class SetUserPreferences extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String fbID = request.getParameter("fbID");
		String location = request.getParameter("location"); 
		String chillFactor =request.getParameter("chillFactor"); 
		String management =request.getParameter("management"); 
		String amenities = request.getParameter("amenities");
		String noise = request.getParameter("noise");
		
		String surveyPoints[] = {management, amenities, location, noise, chillFactor}; 
		
		UserDataManager.setSurveyPoints(fbID, surveyPoints);
	}

}
