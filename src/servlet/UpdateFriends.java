package servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import sql.UserDataManager;

/**
 * Servlet implementation class UpdateFriends
 */
@WebServlet("/UpdateFriends")
public class UpdateFriends extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateFriends() {
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
		JSONObject object = translateToJSON(request);
		System.out.println(object);
		JSONArray elements = object.names();
		object = new JSONObject(elements.getString(0));
		JSONArray friendsJSON = object.getJSONArray("friends");
		String id = object.getString("fbID");
		String[] friends = new String[friendsJSON.length()];
		for (int i = 0; i < friendsJSON.length(); i++) {
			friends[i] = friendsJSON.getString(i);
			System.out.println(friends[i]);
		}
		UserDataManager.setFriends(id, friends);
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
