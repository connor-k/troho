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

import Trie.ReviewHelper;
import sql.Review;

/**
 * Servlet implementation class ReviewServlet
 */
@WebServlet("/ReviewServlet")
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		JSONObject object = requestParamsToJSON(request);
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
			{
				Review currReview = myReviews.elementAt(i);
				reviewObject.put("review", currReview);
			}
			reviewArray.put(i, reviewObject);
		}
		
		out.print(reviewArray);
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
