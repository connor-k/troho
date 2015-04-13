/** UserDataManager Class
 * CSCI 201 Final Project - Troho
 * Description: Interface between our Housing SQL data and JSP
 */

package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class HousingDataManager {
	/** 
	 * @param name the name of this location
	 * @param they HousingType for this location (dorm, etc)
	 * @param description a short text description of this location
	 * @param iconURL the URL of the background image for this location
	 * @param floorplanURL the URL to the floorplan image for this location
	 * @param address the text address of this location
	 * @param distance the distance of this location from campus (walking & biking)
	 */
	public void createHousingLocation(String name, HousingType type, String descrpition, String iconURL, String floorplanURL, String address, String distance) {
		//TODO get/set gps location from text
	}

	/** 
	 * @param housingKey the database key for this housing location
	 * @return a HousingLocation object with all the housing data
	 * @see HousingLocation 
	 */
	public static HousingLocation getHousingLocation(int housingKey) {
		HousingLocation hl = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			// Make sure the email isn't already registered to someone
			Statement st = conn.createStatement();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM HousingLocations WHERE housingKey=?");
			ps.setInt(1, housingKey);
			ResultSet rs = ps.executeQuery();
			// If house for this key exists
			if (rs.next()) {
				hl = new HousingLocation();
				hl.housingKey = housingKey;
				switch(rs.getInt("housingType")) {
				case 0:
					hl.type = HousingType.APARTMENT;
					break;
				case 1:
					hl.type = HousingType.DORM;
					break;
				default:
					hl.type = HousingType.HOUSE;
					break;
				}

				hl.locationName = rs.getString("locationName");
				hl.address = rs.getString("textAddress");
				hl.description = rs.getString("description");
				hl.imageURL = rs.getString("imageURLs");
				hl.floorplanURL = rs.getString("floorplanURLs");
				hl.gpsLatitude = rs.getString("gpsLatitude");
				hl.gpsLongitude = rs.getString("gpsLongitude");
				hl.minutesWalking = rs.getInt("minutesWalking");
				hl.minutesBiking = rs.getInt("minutesBiking");
				rs.close();
				ps.close();
				ps = conn.prepareStatement("Select * FROM Reviews WHERE housingKey=?");
				ps.setInt(1, housingKey);
				rs = ps.executeQuery();
				List<Review> reviews = new LinkedList<Review>();
				int count = 0;
				while (rs.next()) {
					reviews.add(ReviewDataManager.getReview(rs.getInt("reviewKey")));
					hl.managementScore += reviews.get(count).managementScore;
					hl.amenitiesScore += reviews.get(count).amenitiesScore;
					hl.locationScore += reviews.get(count).locationScore;
					hl.noiseScore += reviews.get(count).noiseScore;
					hl.communityChillFactorScore += reviews.get(count).communityChillFactorScore;
					hl.averageRent += reviews.get(count).rentPaid;
					++count;
				}
				if (!reviews.isEmpty()) {
					hl.managementScore /= count;
					hl.amenitiesScore /= count;
					hl.locationScore /= count;
					hl.noiseScore /= count;
					hl.communityChillFactorScore /= count;
					hl.averageRent /= count;
					hl.reviews = reviews.toArray(new Review[reviews.size()]);
					hl.overallScore = (hl.managementScore + hl.amenitiesScore + hl.locationScore + hl.noiseScore + hl.communityChillFactorScore)/5;
				}
			}
			rs.close();
			ps.close();
			st.close();
			conn.close();
		} catch (SQLException sqle) {
			System.out.println ("UserDataManager SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("UserDataManager ClassNotFoundException: " + cnfe.getMessage());
		}

		return hl;
	}

}
