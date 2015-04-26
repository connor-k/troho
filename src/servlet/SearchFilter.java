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

import sql.HousingLocation;
import sql.HousingType;
import Trie.SearchHelper;

/**
 * Servlet implementation class SearchFilter
 */
@WebServlet("/SearchFilter")
public class SearchFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		JSONObject object = requestParamsToJSON(request);
		JSONArray elements = object.names();
		object = new JSONObject(elements.getString(0));
		System.out.println(object);
		String searchWords = object.getString("searchWords");
		int managementScore = object.getInt("managementScore");
		int amenitiesScore = object.getInt("amenitiesScore");
		int locationScore = object.getInt("locationScore");
		int communityChillFactorScore = object.getInt("communityChillFactorScore");
		int maxPrice = object.getInt("maxPrice");
		int maxDistance = object.getInt("maxDistance");
		int housingType =  object.getInt("housingType");
		int minRating = object.getInt("minRating");
		
		SearchHelper mySearch = new SearchHelper();
		
		boolean isHouse = false;
		boolean isApartment = false;
		boolean isDorm = false;
		
		if(housingType == 0) {
			isApartment = true;
			isHouse = true;
			isDorm = true;
		} else if (housingType == 1) {
			isApartment = true;
		} else if (housingType == 2) { 
			isDorm = true;
		} else if (housingType == 3) {
			isHouse = true;
		}
		
		HousingLocation [] myHouses;
		myHouses = mySearch.sortPruneHouses(managementScore, amenitiesScore, locationScore, 
				communityChillFactorScore, searchWords, maxPrice, maxDistance, isHouse, isDorm, 
				isApartment, minRating);
		
		PrintWriter out = response.getWriter();
		
		JSONObject searchObject = new JSONObject();
		JSONArray searchArray = new JSONArray();
		
		for(int i = 0; i < myHouses.length; i++) {
			String houseName;
			if(myHouses[i].type == HousingType.APARTMENT) {
				houseName = "Apartment";
			} else if (myHouses[i].type == HousingType.DORM) {
				houseName = "Dorm";
			} else {
				houseName = "House";
			}
			JSONObject currObject = new JSONObject();
			{
				currObject.put("locationName", myHouses[i].locationName);
				currObject.put("imageURL", myHouses[i].imageURL);
				currObject.put("housingAddress", myHouses[i].address);
				currObject.put("price", "$" + myHouses[i].averageRent);
				currObject.put("distance", "" + myHouses[i].distanceToCampus);
				currObject.put("housingType", houseName);
				currObject.put("rating", myHouses[i].overallScore);
				
			}
			searchArray.put(i, currObject);
		}
		
		searchObject.put("searchArray", searchArray);
		
		out.print(searchObject);
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
