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
import sql.Review;
import Trie.ReviewHelper;
import Trie.SearchHelper;

/**
 * Servlet implementation class SearchFilter
 */
@WebServlet("/SearchFilter")
public class SearchFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("application/json");
		JSONObject object = requestParamsToJSON(request);
		String searchWords = object.getString("searchWords");
		int managementScore = object.getInt("managementScore");
		int amenitiesScore = object.getInt("amenitiesScore");
		int locationScore = object.getInt("locationScore");
		int noiseScore = object.getInt("noiseScore");
		int communityChillFactorScore = object.getInt("communityChillFactorScore");
		int maxPrice = object.getInt("maxPrice");
		int maxDistance = object.getInt("maxDistance");
		String housingType =  object.getString("housingType");
		int minRating = object.getInt("minRating");
		
		SearchHelper mySearch = new SearchHelper();
		
		//APARTMENT, DORM, HOUSE
		boolean isHouse = false;
		boolean isApartment = false;
		boolean isDorm = false;
		//ousingType.valueOf(Apartment)
		
		if(housingType.equals("Apartment")) {
			isApartment = true;
		} else if (housingType.equals("House")) {
			isHouse = true;
		} else { 
			isDorm = true;
		}
		
		HousingLocation [] myHouses;
		myHouses = mySearch.sortPruneHouses(managementScore, amenitiesScore, locationScore, noiseScore, 
				communityChillFactorScore, searchWords, maxPrice, maxDistance, isHouse, isDorm, 
				isApartment, minRating);
		
		
		
		PrintWriter out = response.getWriter();
		
		JSONObject searchObject = new JSONObject();
		JSONArray searchArray = new JSONArray();
		
		for(int i = 0; i < myHouses.length; i++) {
			JSONObject currObject = new JSONObject();
			{
				currObject.put("locationName", myHouses[i].locationName);
				currObject.put("imageURL", myHouses[i].imageURL);
				currObject.put("housingAddress", myHouses[i].address);
				currObject.put("price", "$" + myHouses[i].averageRent);
				currObject.put("distance", "" + myHouses[i].distanceToCampus);
				currObject.put("housingType", housingType);
				currObject.put("rating", myHouses[i].overallScore);
				
			}
			searchArray.put(i, currObject);
		}
		
		searchObject.put("searchArray", searchArray);
		
		out.print(searchObject);
		out.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
