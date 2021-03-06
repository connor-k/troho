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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

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
	public static void createHousingLocation(HousingType type, String name, String address, 
			String description, String amenities, String iconURL, String floorplanURL, String gpsLatitude,
			String gpsLongitude, String minutesWalking, String minutesBiking) {
		// Make sure the location doesn't exist already
		if (getHousingLocation(name) != null) {
			System.out.println("HousingLocation with name " + name + " already exists, no changes"
					+ " made.");
			return;
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			ps = conn.prepareStatement("INSERT INTO HousingLocations (housingType, locationName, "
					+ "textAddress, description, amenities, imageURLs, floorplanURLs, gpsLatitude, "
					+ "gpsLongitude, minutesWalking, minutesBiking) VALUES (?, ?, ?, ?, ?, "
					+ "?, ?, ?, ?, ?, ?);");
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
			ps.setString(5, amenities);
			ps.setString(6, iconURL);
			ps.setString(7, floorplanURL);
			ps.setString(8, gpsLatitude);
			ps.setString(9, gpsLongitude);
			ps.setInt(10, Integer.parseInt(minutesWalking));
			ps.setInt(11, Integer.parseInt(minutesBiking));
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
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			ps = conn.prepareStatement("SELECT * FROM HousingLocations WHERE housingKey=?");
			ps.setInt(1, housingKey);
			rs = ps.executeQuery();
			// If house for this key exists
			if (rs.next()) {
				hl = new HousingLocation();
				hl.housingKey = housingKey;
				switch(rs.getInt("housingType")) {
				case 1:
					hl.type = HousingType.APARTMENT;
					break;
				case 2:
					hl.type = HousingType.DORM;
					break;
				default:
					hl.type = HousingType.HOUSE;
					break;
				}
				hl.locationName = rs.getString("locationName");
				hl.address = rs.getString("textAddress");
				hl.description = rs.getString("description");
				hl.amenities = rs.getString("amenities");
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
			} else {
				System.out.println("No location with key " + housingKey + " exists.");
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
		return getHousingLocation(getHousingKey(housingName));
	}
	
	/** Get the integer key for a housing location
	 * @param housingName The name of this location
	 * @return The integer housingKey
	 */
	public static int getHousingKey(String housingName) {
		int housingKey = -1;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			ps = conn.prepareStatement("SELECT * FROM HousingLocations WHERE locationName=?");
			ps.setString(1, housingName);
			rs = ps.executeQuery();
			// If house for this key exists
			if (rs.next()) {
				housingKey = rs.getInt("housingKey");
			} else {
				System.out.println("No location with name " + housingName + " exists.");
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
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}
		return housingKey;
	}
	
	/** Get all housing locations (e.g. to display on the main page)
	 * @return An array with the locations
	 */
	public static HousingLocation[] getAllHousingLocations() {
		List<HousingLocation> locations = new LinkedList<HousingLocation>();
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			ps = conn.prepareStatement("SELECT * FROM HousingLocations");
			rs = ps.executeQuery();
			while (rs.next()) {
				locations.add(getHousingLocation(rs.getInt("housingKey")));
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
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}
		
		return locations.toArray(new HousingLocation[locations.size()]);
	}
	
	/** Prepare data for the rent over time plot
	 * @param housingName The name of this location
	 * @return Object array containing the label array and data array
	 */
	public static Object[] getRentOverTimeData(String housingName) {
		Object[] data = null;
		HousingLocation housingLocation = getHousingLocation(housingName);
		if (housingLocation != null && housingLocation.reviews != null) {
			TreeMap<String, Double> averageRentPerYear = new TreeMap<String, Double>();
			HashMap<String, Integer> entriesPerYear = new HashMap<String, Integer>();
			for (int i = 0; i < housingLocation.reviews.length; ++i) {
				int rentPaid = housingLocation.reviews[i].rentPaid;
				String yearWritten = housingLocation.reviews[i].timeWritten.substring(0, 4);
				Double averageRent = averageRentPerYear.get(yearWritten);
				if (averageRent != null) {
					averageRentPerYear.put(yearWritten, (averageRent/entriesPerYear.get(yearWritten) + rentPaid)/(entriesPerYear.get(yearWritten) + 1));
					entriesPerYear.put(yearWritten, entriesPerYear.get(yearWritten) + 1);
				} else {
					averageRentPerYear.put(yearWritten, (double)rentPaid);
					entriesPerYear.put(yearWritten, 1);
				}
			}
			
			data = new Object[2];
			ArrayList<String> years = new ArrayList<String>();
			ArrayList<Double> averageRent = new ArrayList<Double>();
			SortedSet<String> keys = new TreeSet<String>(averageRentPerYear.keySet());
			for (String key : keys) {
				years.add(key);
				averageRent.add(averageRentPerYear.get(key));
			}
			data[0] = years.toArray(new String[years.size()]);
			data[1] = averageRent.toArray(new Double[averageRent.size()]);
		}
		return data;
	}
	
	/** Check if a User has reviewed this location
	 * @param facebookID SQL database key for this user
	 * @param housingName The name of this location
	 * @return Object array containing the label array and data array
	 */
	public static boolean hasReviewedLocation(String facebookID, String housingName) {
		HousingLocation housingLocation = getHousingLocation(housingName);
		if (housingLocation != null && housingLocation.reviews != null) {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
				ps = conn.prepareStatement("SELECT * FROM Reviews WHERE facebookID=? AND housingKey=?");
				ps.setString(1, facebookID);
				ps.setInt(2, housingLocation.housingKey);
				rs = ps.executeQuery();
				if (rs.next()) {
					// Note: finally block still executes to close connections before this return
					return true;
				}
			} catch (SQLException sqle) {
				// Note if an invalid housingKey was passed in, 
				System.out.println ("UserDataManager SQLException: " + sqle.getMessage());
			} catch (ClassNotFoundException cnfe) {
				System.out.println ("UserDataManager ClassNotFoundException: " + cnfe.getMessage());
			} finally {
				try {
					ps.close();
				} catch (SQLException e) { /* Do nothing */ }
				try {
					conn.close();
				} catch (SQLException e) { /* Do nothing */ }
			}
		}
		return false;
	}

}
