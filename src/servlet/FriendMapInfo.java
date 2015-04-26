package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import sql.User;
import sql.UserDataManager;
@WebServlet("/FriendMapInfo")
public class FriendMapInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** Given a user facebookID, responds with a map of house names to friend profile URLs
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @Override
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, Vector<String>> hashMap = null;
		String facebookID = request.getParameter("facebookID");
		User user = UserDataManager.getUser(facebookID);
		if (user != null && user.friendIDs != null) {
			hashMap = new HashMap<String, Vector<String>>();
			for (int i = 0; i < user.friendIDs.length; ++i) {
				User friend = UserDataManager.getUser(user.friendIDs[i]);
				Vector<String> peopleHere = hashMap.get(friend.currentLocation.locationName);
				if (peopleHere != null) {
					peopleHere.add(friend.imageURL);
				} else {
					peopleHere = new Vector<String>();
					peopleHere.add(friend.imageURL);
					hashMap.put(friend.currentLocation.locationName, peopleHere);
				}
			}
		}
		
		PrintWriter out = response.getWriter();
		JSONObject jsonObject = new JSONObject(hashMap);
		System.out.println(jsonObject);
		out.print(jsonObject);
		out.flush();
	}

}
