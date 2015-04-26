package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.HousingDataManager;

/**
 * Servlet implementation class VerifiedUser
 */
@WebServlet("/VerifiedUser")
public class VerifiedUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerifiedUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String houseName =request.getParameter("houseName"); 
		String fbID =request.getParameter("fbID"); 
		boolean hasReviewed = HousingDataManager.hasReviewedLocation(fbID, houseName);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();      
		
		//Create String to send in response to get request
		String jsonObject = "{\"reviewBool\": \"" + hasReviewed + "\"}";
		
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		out.print(jsonObject);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
