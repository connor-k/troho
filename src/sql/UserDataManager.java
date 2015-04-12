/** UserDataManager Class
 * CSCI 201 Final Project - Troho
 * Description: Interface between our User SQL data and JSP
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
	 * @return the User created or that already existed in the db
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
						reviews.add(new Review(rs.getInt("reviewID"), user.facebookID,
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
	public static void setLocation(int facebookID, String location) {

	}

	/** 
	 * @param facebookID SQL database key for this user
	 * @param surveyPoints the survey points between 0 and 10 allocated by the user for the 5 categories
	 */
	public static void setSurveyPoints(int facebookID, String[] surveyPoints) {

	}

}
