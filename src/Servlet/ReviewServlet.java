package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;


import sql.Review;
import Trie.ReviewHelper;
import sql.HousingDataManager;
import sql.Review;
import sql.User;
import sql.UserDataManager;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println(request);
		response.setContentType("application/json");
		JSONObject object = requestParamsToJSON(request);
		System.out.println(object);
		JSONArray elements = object.names();
		object = new JSONObject(elements.getString(0));

		String houseName = object.getString("houseName");
		JSONArray tagArray = object.getJSONArray("tags");
		
		boolean [] tags = new boolean[6];
		
		for(int i = 0; i < tagArray.length(); i++) {
			tags[i] = tagArray.getBoolean(i);
		}
		
		PrintWriter out = response.getWriter();
		Vector<Review> myReviews = ReviewHelper.pruneReviews(houseName, tags);
		JSONArray reviewArray = new JSONArray();
		
		for(int i = 0; i < myReviews.size(); i++) {
			JSONObject reviewObject = new JSONObject();		
			Review currReview = myReviews.elementAt(i);
			User user = UserDataManager.getUser(currReview.facebookID);
			reviewObject.put("name", user.name);
			reviewObject.put("userImg", user.imageURL);		
			reviewObject.put("review", currReview.comment);	
			reviewArray.put(i, reviewObject);
			System.out.println(currReview);
		}	

		JSONObject obj = new JSONObject();
		obj.put("reviews", reviewArray);
		out.print(obj);
		out.flush();
	}
	
	public JSONObject requestParamsToJSON(HttpServletRequest request) {
		  JSONObject jsonObj = new JSONObject();
		  Map<String,String[]> params = request.getParameterMap();
		  for (Map.Entry<String,String[]> entry : params.entrySet()) {
		    String v[] = entry.getValue();
		    Object o = (v.length == 1) ? v[0] : v;
		    jsonObj.put(entry.getKey(), o);
		  }
		  return jsonObj;
	}

}
