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

import sql.HousingDataManager;
import sql.UserDataManager;

/**
 * Servlet implementation class VerifiedUser
 */
@WebServlet("/UserReviewedHousing")
public class UserReviewedHousing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserReviewedHousing() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject object = requestParamsToJSON(request);
		JSONArray elements = object.names();
		object = new JSONObject(elements.getString(0));
		String houseName =object.getString("houseName"); 
		String fbID =object.getString("fbID");
		boolean hasReviewed = HousingDataManager.hasReviewedLocation(fbID, houseName);
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();     
		boolean authBool = UserDataManager.getUser(fbID).verifiedEmail;
		//Create String to send in response to get request
		String jsonObject = "{\"reviewBool\": \"" + hasReviewed + "\", \"authBool\":\"" + authBool + "\"}";
		System.out.println(jsonObject);
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
