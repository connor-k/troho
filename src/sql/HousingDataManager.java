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
	/** Create a new housing location
	 * @param housingType The type of location (dorm, etc)
	 * @param name The name of this location
	 * @param address The text address of this location
	 * @param description A short text description of this location
	 * @param iconURL The URL of the background image for this location
	 * @param floorplanURL The URL to the floorplan image for this location
	 * @param gpsLatitude GPS coord for map
	 * @param gpsLongitude GPS coord for map
	 * @param distance The distance of this location from campus (walking & biking)
	 * @param minutesWalking How long it takes to walk to campus from this location
	 * @param minutesBiking How long it takes to bike to campus from this location
	 */
	public void createHousingLocation(HousingType type, String name, String address, 
			String description, String iconURL, String floorplanURL, String gpsLatitude,
			String gpsLongitude, String minutesWalking, String minutesBiking) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			st = conn.createStatement();
			ps = conn.prepareStatement("INSERT INTO HousingLocations (housingType, locationName, "
					+ "textAddress, description, imageURLs, floorplanURLs, gpsLatitude, "
					+ "gpsLongitude, minutesWalking, minutesBiking) VALUES (?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?);");
			switch(type) {
			case APARTMENT:
				ps.setInt(1, 1);
				break;
			case DORM:
				ps.setInt(1, 2);
				break;
			default:
				ps.setInt(1, 3);
				break;
			}
			ps.setString(2, name);
			ps.setString(3, address);
			ps.setString(4, description);
			ps.setString(5, iconURL);
			ps.setString(6, floorplanURL);
			ps.setString(7, gpsLatitude);
			ps.setString(8, gpsLongitude);
			ps.setString(9, minutesWalking);
			ps.setString(10, minutesBiking);
			ps.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println ("UserDataManager SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("UserDataManager ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) { /* Do nothing */ }
			try {
				st.close();
			} catch (SQLException e) { /* Do nothing */ }
			try {
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}
	}

	/** 
	 * @param housingKey The database key for this housing location
	 * @return A HousingLocation object with all the housing data
	 * @see HousingLocation
	 */
	public static HousingLocation getHousingLocation(int housingKey) {
		HousingLocation hl = null;
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			st = conn.createStatement();
			ps = conn.prepareStatement("SELECT * FROM HousingLocations WHERE housingKey=?");
			ps.setInt(1, housingKey);
			rs = ps.executeQuery();
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
				
				// Get reviews for this location
				try {
					rs.close();
				} catch (SQLException e) { /* Do nothing */ }
				try {
					ps.close();
				} catch (SQLException e) { /* Do nothing */ }
				try {
					st.close();
				} catch (SQLException e) { /* Do nothing */ }
				st = conn.createStatement();
				ps = conn.prepareStatement("Select * FROM Reviews WHERE housingKey=?");
				ps.setInt(1, housingKey);
				rs = ps.executeQuery();
				List<Review> reviews = new LinkedList<Review>();
				int count = 0;
				while (rs.next()) {
					reviews.add(ReviewDataManager.getReview(rs.getInt("reviewID")));
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
		} catch (SQLException sqle) {
			System.out.println ("UserDataManager SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("UserDataManager ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				rs.close();
			} catch (SQLException e) { /* Do nothing */ }
			try {
				ps.close();
			} catch (SQLException e) { /* Do nothing */ }
			try {
				st.close();
			} catch (SQLException e) { /* Do nothing */ }
			try {
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}

		return hl;
	}
	
	/** 
	 * @param housingName The name of this housing location
	 * @return A HousingLocation object with all the housing data
	 * @see HousingLocation
	 */
	public static HousingLocation getHousingLocation(String housingName) {
		HousingLocation hl = null;
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			st = conn.createStatement();
			ps = conn.prepareStatement("SELECT * FROM HousingLocations WHERE locationName=?");
			ps.setString(1, housingName);
			ResultSet rs = ps.executeQuery();
			// If house for this key exists
			if (rs.next()) {
				hl = getHousingLocation(rs.getInt("housingKey"));
			}
			rs.close();
			ps.close();
			st.close();
			conn.close();
		} catch (SQLException sqle) {
			System.out.println ("UserDataManager SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("UserDataManager ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) { /* Do nothing */ }
			try {
				st.close();
			} catch (SQLException e) { /* Do nothing */ }
			try {
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}

		return hl;
	}

}
