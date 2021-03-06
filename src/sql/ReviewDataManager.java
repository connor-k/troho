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

public class ReviewDataManager {
	/** Store a review in the db, with housing location set by the key
	 * @param housingKey The key of the location this review is for
	 * @param facebookID The user who's writing this review
	 * @param comment The text comment associated with the review
	 * @param ratings The integer scores given to each review category
	 * @param tags An array designating any tags added by the user
	 * @param rent The rent paid by the user (currently optional)
	 */
	public static void createReview(int housingKey, String facebookID, String comment, String[] ratings, boolean[] tags, String rent) {
		// Ensure they haven't already written a review
		HousingLocation hl = HousingDataManager.getHousingLocation(housingKey);
		if (hl != null) {
			if (hl.reviews != null) {
				for (int i = 0; i < hl.reviews.length; ++i) {
					if (hl.reviews[i].facebookID.equals(facebookID)) {
						System.out.println("ReviewDataManager.createReview: facebookID " + facebookID
								+ " has already reviewed housingKey " + housingKey + ", no changes"
								+ " made");
						return;
					}
				}
			}
		} else {
			System.out.println("ReviewDataManager.createReview: invalid housingKey, no changes made");
		}
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			ps = conn.prepareStatement("INSERT INTO Reviews (housingKey, facebookID, textComment, "
					+ "managementScore, amenitiesScore, locationScore, noiseScore, "
					+ "communityChillFactorScore, tag1, tag2, tag3, tag4, tag5, tag6, rentPaid, "
					+ "timeWritten) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, now());");
			ps.setInt(1, housingKey);
			ps.setString(2, facebookID);
			ps.setString(3, comment);
			for (int i = 4; i <= 8; ++i) {
				ps.setString(i, ratings[i - 4]);
			}
			for (int i = 9; i <= 14; ++i) {
				ps.setBoolean(i, tags[i - 9]);
			}
			ps.setInt(15, Integer.parseInt(rent));
			ps.executeUpdate();
		} catch (SQLException sqle) {
			System.out.println ("ReviewDataManager SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ReviewDataManager ClassNotFoundException: " + cnfe.getMessage());
		} finally {
			try {
				ps.close();
			} catch (SQLException e) { /* Do nothing */ }
			try {
				conn.close();
			} catch (SQLException e) { /* Do nothing */ }
		}
	}
	
	/** Store a review in the db, with housing location set by the name
	 * @param housingName The name of the location this review is for
	 * @param facebookID The user who's writing this review
	 * @param comment The text comment associated with the review
	 * @param ratings The integer scores given to each review category
	 * @param tags An array designating any tags added by the user
	 * @param rent The rent paid by the user (currently optional)
	 */
	public static void createReview(String housingName, String facebookID, String comment, String[] ratings, boolean[] tags, String rent) {
		createReview(HousingDataManager.getHousingKey(housingName), facebookID, comment, ratings, tags, rent);
	}

	/** 
	 * @param reviewKey the database key to this review
	 * @return a Review object with all the Review data
	 * @see Review 
	 */
	public static Review getReview(int reviewKey) {
		Review review = null;
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/Troho?user=root");
			// Make sure the email isn't already registered to someone
			ps = conn.prepareStatement("SELECT * FROM Reviews WHERE reviewID=?");
			ps.setInt(1, reviewKey);
			rs = ps.executeQuery();
			if (rs.next()) {
				boolean tags[] = new boolean[6];
				for (int i = 0; i < 6; ++i) {
					tags[i] = rs.getBoolean("tag" + (i + 1));
				}
				review = new Review (reviewKey, rs.getInt("housingKey"), rs.getString("facebookID"),
						rs.getInt("managementScore"), rs.getInt("amenitiesScore"),
						rs.getInt("locationScore"), rs.getInt("noiseScore"),
						rs.getInt("communityChillFactorScore"), rs.getString("textComment"),
						tags, rs.getInt("rentPaid"), rs.getString("timeWritten"));
			}
		} catch (SQLException sqle) {
			System.out.println ("ReviewDataManager SQLException: " + sqle.getMessage());
		} catch (ClassNotFoundException cnfe) {
			System.out.println ("ReviewDataManager ClassNotFoundException: " + cnfe.getMessage());
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

		return review;
	}

}
