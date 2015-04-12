package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HousingRedirectServlet
 */
@WebServlet("/HousingRedirectServlet")
public class HousingRedirectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HousingRedirectServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Hi");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Made it");
		response.setStatus(HttpServletResponse.SC_FOUND);
		String housingName=request.getParameter("housingname"); 
//		System.out.println("Name is" + housingName);
//		response.setContentType( "text/html" );
//		response.sendRedirect("arush.jsp?name=" + housingName);
		response.sendRedirect("/Troco");
//		response.setHeader("Location", "/Troco");
//		ServletContext sc = getServletContext();
//		request.setAttribute("name", housingName);
//		RequestDispatcher rd = sc.getRequestDispatcher("/Troco/arush.jsp");
//		rd.forward(request, response);
//		System.out.println("Got here3");
	     response.setHeader("Location", "/Troco");    
	     System.out.println("Made it to End");
	}

}
