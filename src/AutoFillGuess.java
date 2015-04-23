

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AutoFillGuess
 */
@WebServlet("/AutoFillGuess")
public class AutoFillGuess extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutoFillGuess() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Get the printwriter object from response to write the required json object to the output stream
		
		//BufferedReader br = request.getReader();
		//String userInput = br.readLine();
		BufferedReader br = request.getReader();
		String userInput = br.readLine();
		
		System.out.println(userInput);
		
		response.setContentType("application/json");
		response.getWriter().flush();      
		
		//Create String to send in response to get request
		//String jsonObject = "{\"guess\": "\"" +  + "\"}";
		
		// Assuming your json object is **jsonObject**, perform the following, it will return your json object  
		//out.print(jsonObject);
		//out.flush();
	}

}
