/** UserDataManager Class
 * CSCI 201 Final Project - Troho
 * Description: Interface between our User SQL data and JSP
 * TODO: go through and validate data everywhere, i.e. users exist etc
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

public class UserDataManager {
	/** Create a new entry in the Users table
	 * @param name The user's name
	 * @param email The user's email address (@usc.edu)
	 * @return the User created or that already existed in tphe db
	 */
	public static User createUser(String name, String email, String location, String facebookID) {
		User user = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			// Make sure the email isn't already registered to someone
			Statement st = conn.createStatement();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users WHERE email=?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				// Safe, create new user
				st = conn.createStatement();
				ps = conn.prepareStatement("INSERT INTO Users (userName, housingKey, email, facebookID) VALUES (?, ?, ?, ?);");
				ps.setString(1, name);
				ps.setInt(2, Integer.parseInt(location)); //TODO location must be housing key, validate
				ps.setString(3, email);
				ps.setString(4, facebookID);
				ps.executeUpdate();
				user = new User();
				user.facebookID = facebookID;
				user.name = name;
				user.email = email;
				user.currentLocation = HousingDataManager.getHousingLocation(Integer.parseInt(location)); //TODO
				
				// Create an entry for their survey with default values
				rs.close();
				ps.close();
				st = conn.createStatement();
				ps = conn.prepareStatement("INSERT INTO Surveys (facebookID, managementSurveyScore, "
						+ "amenitiesSurveyScore, locationSurveyScore, noiseSurveyScore, "
						+ "communityChillFactorSurveyScore) VALUES (?, 5, "
						+ "5, 5, 5, 5);");
				ps.setString(1, facebookID);
				ps.executeUpdate();
				user.managementSurveyScore = 5;
				user.amenitiesSurveyScore = 5;
				user.locationSurveyScore = 5;
				user.noiseSurveyScore = 5;
				user.communityChillFactorSurveyScore = 5;
			} else {
				if (!rs.getString("userName").equals(name)) {
					System.out.println("Invalid login! Name did not match name associated with email.");
				} else {
					System.out.println("Retrieving existing user from db.");
					user = new User();
					user.facebookID = facebookID;
					user.name = name;
					user.email = email;
					user.currentLocation = HousingDataManager.getHousingLocation(rs.getInt("housingKey"));
					st = conn.createStatement();
					ps = conn.prepareStatement("SELECT * FROM Surveys WHERE facebookID=?");
					ps.setString(1, user.facebookID);
					rs = ps.executeQuery();
					if (rs.next()) {
						user.managementSurveyScore = rs.getInt("managementSurveyScore");
						user.amenitiesSurveyScore = rs.getInt("amenitiesSurveyScore");
						user.locationSurveyScore = rs.getInt("locationSurveyScore");
						user.noiseSurveyScore = rs.getInt("noiseSurveyScore");
						user.communityChillFactorSurveyScore = rs.getInt("communityChillFactorSurveyScore");
					}
					st = conn.createStatement();
					ps = conn.prepareStatement("SELECT * FROM Reviews WHERE facebookID=?");
					ps.setString(1, user.facebookID);
					rs = ps.executeQuery();
					List<Review> reviews = new LinkedList<Review>();
					while (rs.next()) {
						reviews.add(new Review(rs.getInt("reviewID"), rs.getInt("housingKey"), user.facebookID,
								rs.getInt("managementScore"), rs.getInt("amenitiesScore"),
								rs.getInt("locationScore"), rs.getInt("noiseScore"),
								rs.getInt("communityChillFactorScore"), rs.getString("textComment"),
								rs.getInt("rentPaid"), rs.getString("timeWritten")));
					}
					if (!reviews.isEmpty()) {
						user.reviewsWritten = reviews.toArray(new Review[reviews.size()]);
					}
					//TODO friends!
				}
			}
			rs.close();
			st.close();
			conn.close();
		} catch (SQLException sqle) {
			System.out.println ("UserDataManager SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("UserDataManager ClassNotFoundException: " + cnfe.getMessage());
		}

		return user;
	}

	/** 
	 * @param facebookID SQL database key for this user
	 * @param location The location this user lives
	 */
	public static void setLocation(String facebookID, String location) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			Statement st = conn.createStatement();
			PreparedStatement ps = conn.prepareStatement("UPDATE Users SET housingKey=? WHERE facebookID=?");
			ps.setInt(1, Integer.parseInt(location)); //TODO
			ps.setString(2, facebookID);
			ps.executeUpdate();
			st.close();
			conn.close();
		} catch (SQLException sqle) {
			System.out.println ("UserDataManager SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("UserDataManager ClassNotFoundException: " + cnfe.getMessage());
		}
	}

	/** 
	 * @param facebookID SQL database key for this user
	 * @param surveyPoints the survey points between 0 and 10 allocated by the user for the 5 categories
	 */
	public static void setSurveyPoints(String facebookID, String[] surveyPoints) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			st = conn.createStatement();
			ps = conn.prepareStatement("UPDATE Surveys SET managementSurveyScore=?, "
					+ "amenitiesSurveyScore=?, locationSurveyScore=?, noiseSurveyScore=?, "
					+ "communityChillFactorSurveyScore=? WHERE facebookID=?");
			ps.setInt(1, Integer.parseInt(surveyPoints[0]));
			ps.setInt(2, Integer.parseInt(surveyPoints[1]));
			ps.setInt(3, Integer.parseInt(surveyPoints[2]));
			ps.setInt(4, Integer.parseInt(surveyPoints[3]));
			ps.setInt(5, Integer.parseInt(surveyPoints[4]));
			ps.setString(6, facebookID);
			int result = ps.executeUpdate();
			if (result == 0) {
				System.out.println("UserDataManager.setSurveyPoints: FacebookID " + facebookID + " not in database, no changes made.");
			}
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

}
