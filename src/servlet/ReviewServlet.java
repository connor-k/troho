package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import sql.Review;
import sql.User;
import sql.UserDataManager;
import Trie.ReviewHelper;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Requesting info");
		response.setContentType("application/json");
		JSONObject object = translateToJSON(request);
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
		Review[] myReviews = ReviewHelper.pruneReviews(houseName, tags);
		JSONArray reviewArray = new JSONArray();
		
		for(int i = 0; i < myReviews.length; i++) {
			JSONObject reviewObject = new JSONObject();		
			Review currReview = myReviews[i];
			User user = UserDataManager.getUser(currReview.facebookID);
			reviewObject.put("name", user.name);
			reviewObject.put("userImg", user.imageURL);		
			reviewObject.put("review", currReview.comment);	
			reviewArray.put(i, reviewObject);
		}	

		JSONObject obj = new JSONObject();
		obj.put("reviews", reviewArray);
		out.print(obj);
		out.flush();
	}
	
	public JSONObject translateToJSON(HttpServletRequest request) {
		  JSONObject newObject = new JSONObject();
		  Map<String,String[]> parameterMap = request.getParameterMap();
		  Iterator<Map.Entry<String,String[]>> mapIt = parameterMap.entrySet().iterator();
		  
		  while(mapIt.hasNext()) {
			  Map.Entry<String,String[]> curr = mapIt.next();
			  String [] result = curr.getValue();
			  if(result.length != 1) {
				  Object addObject = result;
				  newObject.put(curr.getKey(), addObject);
			  } else {
				  Object addObject = result[0];
				  newObject.put(curr.getKey(), addObject);
			  }
		  }
		  return newObject;
	}

}
