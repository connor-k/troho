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
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class UserDataManager {
	/** Create a new entry in the Users table
	 * @param name The user's name
	 * @param email The user's email address (@usc.edu)
	 * @param profileImageURL URL to the user's profile image 
	 * @param facebookID The Facebook ID to identify this user
	 * @return The User created or that already existed in the db
	 */
	public static User createUser(String name, String email, String profileImageURL, String facebookID) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			ps = conn.prepareStatement("INSERT INTO Users (name, imageURL, email, facebookID, isAdmin, verifiedEmail) VALUES (?, ?, ?, ?, false, false);");
			ps.setString(1, name);
			ps.setString(2, profileImageURL);
			ps.setString(3, email);
			ps.setString(4, facebookID);
			// Catch this executeUpdate separately, if it happens is because duplicate facebookID or invalid housingKey
			try {
				ps.executeUpdate();
				user = new User();
				user.facebookID = facebookID;
				user.name = name;
				user.email = email;
				user.verifiedEmail = false;
				user.isAdmin = false;
				user.currentLocation = null;
				user.imageURL = profileImageURL;

				// Create an entry for their survey with default values
				try {
					ps.close();
				} catch (SQLException e) { /* Do nothing */ }


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
						+ facebookID + ", no changes made");
			}
		} catch (SQLException sqle) {
			System.out.println ("UserDataManager SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("UserDataManager ClassNotFoundException: " + cnfe.getMessage());
		}  finally {
			try {
				ps.close();
			} catch (SQLException e) { /* Do nothing */ }

			try {
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}

		return user;
	}

	/** Create a new entry in the Users table. Overloaded to take in a boolean for if they're admin
	 * @param name The user's name
	 * @param email The user's email address (@usc.edu)
	 * @param housingKey The key for the housing location where they live 
	 * @param facebookID The Facebook ID to identify this user
	 * @param isAdmin true if this is an admin user
	 * @return The User created or that already existed in the db
	 */
	public static User createUser(String name, String email, String profileImageURL, String facebookID, boolean isAdmin) {
		User user = createUser(name, email, profileImageURL, facebookID);
		if (user != null && isAdmin) {
			Connection conn = null;
			PreparedStatement ps = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
				ps = conn.prepareStatement("UPDATE Users SET isAdmin=true WHERE facebookID=?");
				ps.setString(1, facebookID);
				int result = ps.executeUpdate();
				if (result == 0) {
					System.out.println("UserDataManager.createUser|Admin: FacebookID " 
							+ facebookID + " not in database, couldn't set as admin.");
				}
				user.isAdmin = true;
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

		return user;
	}

	/** Set validation key that's emailed to the user
	 * @param facebookID SQL database key for this user
	 * @param validationKey the validation key emailed to user
	 */
	public static void setValidationKey(String facebookID, String validationKey) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			ps = conn.prepareStatement("UPDATE Users SET validationKey=? WHERE facebookID=?");
			ps.setString(1, validationKey);
			ps.setString(2, facebookID);
			int result = ps.executeUpdate();
			if (result == 0) {
				System.out.println("UserDataManager.setValidationKey: FacebookID " 
						+ facebookID + " not in database, no changes made.");
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

	/** Verify the email of a user, checking that the validation url matches the one assigned to them
	 * @param facebookID SQL database key for this user
	 * @param validationKey the validation key emailed to user
	 * @return Success boolean. True if verification was successful (keys matched).
	 */
	public static boolean verifyEmail(String facebookID, String validationKey) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			ps = conn.prepareStatement("UPDATE Users SET verifiedEmail=true WHERE facebookID=? AND validationKey=?");
			ps.setString(1, facebookID);
			ps.setString(2, validationKey);
			int result = ps.executeUpdate();
			if (result == 0) {
				System.out.println("UserDataManager.verifyEmail: FacebookID " + facebookID + " not "
						+ "in database or didn't match validationKey, no changes made.");
			} else {
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

		return false;
	}

	/** Get an existing user from the Users table
	 * @param facebookID The Facebook id for this user
	 * @return A User object that holds all relevant data
	 */
	public static User getUser(String facebookID) {
		User user = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			ps = conn.prepareStatement("SELECT * FROM Users WHERE facebookID=?");
			ps.setString(1, facebookID);
			rs = ps.executeQuery();
			if (rs.next()) {
				// Set basic information
				user = new User();
				user.facebookID = facebookID;
				user.name = rs.getString("name");
				user.imageURL = rs.getString("imageURL");
				user.email = rs.getString("email");
				user.verifiedEmail = rs.getBoolean("verifiedEmail");
				user.isAdmin = rs.getBoolean("isAdmin");
				user.currentLocation = HousingDataManager.getHousingLocation(rs.getInt("housingKey"));

				// Set survey preferences
				try {
					rs.close();
				} catch (SQLException e) { /* Do nothing */ }
				try {
					ps.close();
				} catch (SQLException e) { /* Do nothing */ }
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
				ps = conn.prepareStatement("SELECT * FROM Friends WHERE facebookID=?");
				ps.setString(1, user.facebookID);
				rs = ps.executeQuery();
				List<String> friendIDs = new LinkedList<String>();
				while (rs.next()) {
					friendIDs.add(rs.getString("friendID"));
				}
				if (!friendIDs.isEmpty()) {
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
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}

		return user;
	}

	/** Set the user's current location by location key 
	 * @param facebookID SQL database key for this user
	 * @param housingKey The housingKey for where this user lives. 
	 */
	public static void setLocation(String facebookID, int housingKey) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
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
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}
	}

	/** Set the user's email 
	 * @param facebookID SQL database key for this user
	 * @param email The email for this user. 
	 */
	public static void setEmail(String facebookID, String email) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			ps = conn.prepareStatement("UPDATE Users SET email=? WHERE facebookID=?");
			ps.setString(1, email);
			ps.setString(2, facebookID);
			int result = ps.executeUpdate();
			if (result == 0) {
				System.out.println("UserDataManager.setEmail: FacebookID " 
						+ facebookID + " not in database, no changes made.");
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

	/** Set the user's current location by location name
	 * @param facebookID SQL database key for this user
	 * @param housingName The name of their location 
	 */
	public static void setLocation(String facebookID, String housingName) {
		setLocation(facebookID, HousingDataManager.getHousingKey(housingName));
	}

	/** 
	 * @param facebookID SQL database key for this user
	 * @param friends The array of Facebook IDs of this User's friends 
	 */
	public static void setFriends(String facebookID, String[] friends) {
		User user = getUser(facebookID);
		if (user == null) {
			System.out.println("Invalid facebookID " + facebookID + ", no changes made.");
			return;
		}
		List<String> friendsToAdd = new Vector<String>();
		//TODO n^2 currently, improve
		for (int i = 0; i < friends.length; ++i) {
			boolean found = false;
			if (user.friendIDs != null) {
				for (int j = 0; j < user.friendIDs.length; ++j) {
					if (friends[i].equals(user.friendIDs[j])) {
						found = true;
						break;
					}
				}
			}
			if (!found) {
				friendsToAdd.add(friends[i]);
			}
		}
		
		Connection conn = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			for (int i = 0; i < friendsToAdd.size(); ++i) {
				ps1 = conn.prepareStatement("INSERT INTO Friends (facebookID, friendID) VALUES (?, ?);");
				ps2 = conn.prepareStatement("INSERT INTO Friends (facebookID, friendID) VALUES (?, ?);");
				ps1.setString(1, facebookID);
				ps1.setString(2, friendsToAdd.get(i));
				ps2.setString(1, friendsToAdd.get(i));
				ps2.setString(2, facebookID);
				// Catch this executeUpdate separately because, if it happens, is because invalid housingKey
				try {
					ps1.executeUpdate();
					ps2.executeUpdate();
				} catch (SQLException sqle) {
					System.out.println("UserDataManager.setFriends: invalid keys " + facebookID 
							+ "|" + friendsToAdd.get(i) + ", no changes made");
				} finally {
					ps1.close();
					ps2.close();
				}
			}
		} catch (SQLException sqle) { 
			System.out.println ("UserDataManager SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("UserDataManager ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}
	}

	/** 
	 * @param facebookID SQL database key for this user
	 * @param surveyPoints he survey points between 0 and 10 allocated by the user for the 5 categories
	 */
	public static void setSurveyPoints(String facebookID, String[] surveyPoints) {
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
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
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}
	}

}
