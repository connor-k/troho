package servlets;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
/**
 * Servlet implementation class FirstServlet
 */
@WebServlet(description = "My First Servlet", urlPatterns = { "/FirstServlet" , "/FirstServlet.do"}, initParams = {@WebInitParam(name="id",value="1"),@WebInitParam(name="name",value="pankaj")})
public class FirstServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String HTML_START="<html><body>";
    public static final String HTML_END="</body></html>";
        
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FirstServlet() {
        super();
        System.out.println("Created servlet1");
        // TODO Auto-generated constructor stub
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    	//Get user_name and pass_word from the JSP page 
    	String username=request.getParameter("firstname"); 
    	String password=request.getParameter("lastname"); 
    	//Print the above got values in console 
    	System.out.println("The username is" +username); 
    	System.out.println("\nand the password is" +password); 
	}
    

 
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Date date = new Date();
        out.println(HTML_START + "<h2>Hi There!</h2><br/><h3>Date="+date +"</h3>"+HTML_END);
    }
 
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String username=request.getParameter("firstname"); 
    	String password=request.getParameter("lastname"); 
    	//Print the above got values in console 
    	System.out.println("The username is " +username); 
    	System.out.println("and the password is " +password);    
    	
    	try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			Statement st = conn.createStatement();
//			ResultSet rs = st.executeQuery("SELECT * from Student where fname='" + name + "'");
			PreparedStatement ps = conn.prepareStatement("INSERT INTO Users (userName, currentLocationID, email, facebookID) VALUES (?, 2, ?, '23rfsajkdf')");
			ps.setString(1, username);
			ps.setString(2, password);
			ps.executeUpdate();
			System.out.println("Worked?");
			st.close();
			conn.close();
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		} 
    	catch (ClassNotFoundException cnfe) {
			System.out.println ("ClassNotFoundException: " + cnfe.getMessage());
		}
    	
    }
 
}