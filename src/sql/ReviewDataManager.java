/** UserDataManager Class
 * CSCI 201 Final Project - Troho
 * Description: Interface between our Review SQL data and JSP
 */

package sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ReviewDataManager {
	/** Store a review in the db
	 * @param housingKey The location this review is for
	 * @param facebookID The user who's writing this review
	 * @param comment The text comment associated with the review
	 * @param ratings The integer scores given to each review category
	 * @param rent The rent paid by the user (currently optional)
	 */
	public static void createReview(int housingKey, String facebookID, String comment, String[] ratings, String rent) {
		Connection conn = null;
		Statement st = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			st = conn.createStatement();
			ps = conn.prepareStatement("INSERT INTO Reviews (housingKey, facebookID, textComment, "
					+ "managementScore, amenitiesScore, locationScore, noiseScore, "
					+ "communityChillFactorScore, rentPaid, timeWritten) VALUES (?, ?, ?, ?, ?, ?,"
					+ " ?, ?, ?, now());");
			ps.setInt(1, housingKey);
			ps.setString(2, facebookID);
			ps.setString(3, comment);
			for (int i = 4; i <= 8; ++i) {
				ps.setString(4 + i, ratings[4 - i]);
			}
			ps.setInt(9, Integer.parseInt(rent));
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
		//TODO handle overwriting an existing review if wanted
	}
	
	/** 
	 * @param reviewKey the database key to this review
	 * @return a Review object with all the Review data
	 * @see Review 
	 */
	public static Review getReview(int reviewKey) {
		Review review = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			// Make sure the email isn't already registered to someone
			Statement st = conn.createStatement();
			PreparedStatement ps = conn.prepareStatement("SELECT * FROM Reviews WHERE reviewID=?");
			ps.setInt(1, reviewKey);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				review = new Review (reviewKey, rs.getInt("housingKey"), rs.getString("facebookID"),
						rs.getInt("managementScore"), rs.getInt("amenitiesScore"),
						rs.getInt("locationScore"), rs.getInt("noiseScore"),
						rs.getInt("communityChillFactorScore"), rs.getString("textComment"),
						rs.getInt("rentPaid"), rs.getString("timeWritten"));
			}
			ps.close();
			st.close();
			conn.close();
		} catch (SQLException sqle) {
			System.out.println ("UserDataManager SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("UserDataManager ClassNotFoundException: " + cnfe.getMessage());
		}
		
		return review;
	}
	
}
