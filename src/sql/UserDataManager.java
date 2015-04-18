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
	 * @param housingKey The key for the housing location where they live 
	 * @param facebookID The Facebook ID to identify this user
	 * @return The User created or that already existed in the db
	 */
	public static User createUser(String name, String email, int housingKey, String facebookID) {
		User user = null;
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			st = conn.createStatement();
			ps = conn.prepareStatement("INSERT INTO Users (userName, housingKey, email, facebookID) VALUES (?, ?, ?, ?);");
			ps.setString(1, name);
			ps.setInt(2, housingKey);
			ps.setString(3, email);
			ps.setString(4, facebookID);
			// Catch this executeUpdate separately, if it happens is because duplicate facebookID or invalid housingKey
			try {
				ps.executeUpdate();
				user = new User();
				user.facebookID = facebookID;
				user.name = name;
				user.email = email;
				user.currentLocation = HousingDataManager.getHousingLocation(housingKey);

				// Create an entry for their survey with default values
				try {
					ps.close();
				} catch (SQLException e) { /* Do nothing */ }
				try {
					st.close();
				} catch (SQLException e) { /* Do nothing */ }
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
			} catch (SQLException sqle) {
				System.out.println("UserDataManager.createUser: duplicate facebookID " 
						+ facebookID + " or invalid housingKey " + housingKey + ", no changes made");
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

		return user;
	}

	/** Get an existing user from the Users table
	 * @param facebookID The Facebook id for this user
	 * @return A User object that holds all relevant data
	 */
	public static User getUser(String facebookID) {
		User user = null;
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			st = conn.createStatement();
			ps = conn.prepareStatement("SELECT * FROM Users WHERE facebookID=?");
			ps.setString(1, facebookID);
			rs = ps.executeQuery();
			if (rs.next()) {
				// Set basic information
				user = new User();
				user.facebookID = facebookID;
				user.name = rs.getString("userName");
				user.email = rs.getString("email");
				user.currentLocation = HousingDataManager.getHousingLocation(rs.getInt("housingKey"));

				// Set survey preferences
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

				// Set reviews written
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
				ps = conn.prepareStatement("SELECT * FROM Reviews WHERE facebookID=?");
				ps.setString(1, user.facebookID);
				rs = ps.executeQuery();
				List<Review> reviews = new LinkedList<Review>();
				while (rs.next()) {
					reviews.add(ReviewDataManager.getReview(rs.getInt("reviewID")));
				}
				if (!reviews.isEmpty()) {
					user.reviewsWritten = reviews.toArray(new Review[reviews.size()]);
				}

				// Set friends
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
				ps = conn.prepareStatement("SELECT * FROM Friends WHERE facebookID=?");
				ps.setString(1, user.facebookID);
				rs = ps.executeQuery();
				List<String> friendIDs = new LinkedList<String>();
				while (rs.next()) {
					friendIDs.add(rs.getString("friendID"));
				}
				if (!reviews.isEmpty()) {
					user.friendIDs = friendIDs.toArray(new String[friendIDs.size()]);
				}
			} else {
				System.out.println("UserDataManager.getUser: invalid facebookID " 
						+ facebookID + ", null User will be returned.");
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

		return user;
	}

	/** 
	 * @param facebookID SQL database key for this user
	 * @param housingKey The housingKey for where this user lives. 
	 */
	public static void setLocation(String facebookID, int housingKey) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			st = conn.createStatement();
			ps = conn.prepareStatement("UPDATE Users SET housingKey=? WHERE facebookID=?");
			ps.setInt(1, housingKey);
			ps.setString(2, facebookID);
			// Catch this executeUpdate separately because, if it happens, is because invalid housingKey
			try {
				int result = ps.executeUpdate();
				if (result == 0) {
					System.out.println("UserDataManager.setLocation: FacebookID " 
							+ facebookID + " not in database, no changes made.");
				}
			} catch (SQLException sqle) {
				System.out.println("UserDataManager.setLocation: invalid housingKey " 
						+ housingKey + ", no changes made");
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
				st.close();
			} catch (SQLException e) { /* Do nothing */ }
			try {
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}
	}

	/** 
	 * @param facebookID SQL database key for this user
	 * @param surveyPoints The survey points between 0 and 10 allocated by the user for the 5 categories
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
