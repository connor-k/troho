package servlet;
import org.json.*;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.ReviewDataManager;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/SubmitReview")
public class SubmitReview extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubmitReview() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request.getParameter("housingname"));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		System.out.println("HOUSING NAME "request.getParameter("housingname"));
		JSONObject object = requestParamsToJSON(request);
		
//		System.out.println(request);
//		String name=request.getParameter("name"); 
//		String imgURL =request.getParameter("url"); 
//		String fbID =request.getParameter("fbID"); 
//		String email = request.getParameter("email");
//		System.out.println(email);
//		UserDataManager.createUser(name, email, imgURL, fbID);
		
		
		JSONArray elements = object.names();
		for(int i = 0; i < elements.length(); i++) {
			System.out.println(elements.getString(i) + i);
		}
		object = new JSONObject(elements.getString(0));
		JSONArray ratings = object.getJSONArray("ratings");
		String[] ratingsStringArray = new String[5];
		for(int i = 0; i < ratings.length(); i++) {
			ratingsStringArray[i] = Integer.toString(ratings.getInt(i));
		}
		boolean [] tags = new boolean[6];
		for (int i = 0; i < 6; i++) {
			tags[i] = true;
		}
		
		//TODO:
		// Add tag functionality
		ReviewDataManager.createReview(object.getString("housingname"), object.getString("fbID"), object.getString("review"), ratingsStringArray, tags, Integer.toString(object.getInt("rent")));

	}
	//	public static void createReview(String housingName, String facebookID, String comment, String[] ratings, String rent) {

	
	public JSONObject requestParamsToJSON(HttpServletRequest req) {
		  JSONObject jsonObj = new JSONObject();
		  Map<String,String[]> params = req.getParameterMap();
		  for (Map.Entry<String,String[]> entry : params.entrySet()) {
		    String v[] = entry.getValue();
		    Object o = (v.length == 1) ? v[0] : v;
		    jsonObj.put(entry.getKey(), o);
		  }
		  return jsonObj;
	}

}
