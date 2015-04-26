package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.UserDataManager;

/**
 * Servlet implementation class UpdateUserHousingLocation
 */

@WebServlet("/UpdateUserHousingLocation")
public class UpdateUserHousingLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fbID = request.getParameter("fbID");
		String currentHousingLocation = request.getParameter("currentHousingLocation");
		
		UserDataManager.setLocation(fbID, currentHousingLocation);
	}
}
