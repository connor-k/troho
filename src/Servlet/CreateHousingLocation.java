package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sql.HousingDataManager;
import sql.HousingType;
@WebServlet("/CreateHousingLocation")
public class CreateHousingLocation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/** Handle post request for creating a new housing location (by admin user)
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * @Override
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String housingTypeString = request.getParameter("housingType");
		String housingName = request.getParameter("housingName");
		String address = request.getParameter("address");
		String description = request.getParameter("description");
		String iconURL = request.getParameter("iconURL");
		String floorplanURL = request.getParameter("floorPlanURL");
		String gpsLatitude = request.getParameter("gpsLatitude");
		String gpsLongitude = request.getParameter("gpsLongitude");
		String minutesWalking = request.getParameter("minutesWalking");
		String minutesBiking = request.getParameter("minutesBiking");
		HousingType housingType = null;
		try {
			switch (Integer.parseInt(housingTypeString)) {
			case 1:
				housingType = HousingType.APARTMENT;
				break;
			case 2:
				housingType = HousingType.DORM;
				break;
			default:
				housingType = HousingType.HOUSE;
				break;
			}
		} catch (NumberFormatException nfe) {
			System.out.println("Invalid housingType in CreateHousingLocation doPost");
		}
		HousingDataManager.createHousingLocation(housingType, housingName, address, description,
				iconURL, floorplanURL, gpsLatitude, gpsLongitude, minutesWalking, minutesBiking);
		
	}

}
