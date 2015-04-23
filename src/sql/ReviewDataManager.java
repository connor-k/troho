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
import java.text.DecimalFormat;

public class ReviewDataManager {
	/** Store a review in the db
	 * @param housingKey The location this review is for
	 * @param facebookID The user who's writing this review
	 * @param comment The text comment associated with the review
	 * @param ratings The integer scores given to each review category
	 * @param rent The rent paid by the user (currently optional)
	 */
	public static void createReview(int housingKey, String facebookID, String comment, String[] ratings, String rent) {
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
					+ "communityChillFactorScore, rentPaid, timeWritten) VALUES (?, ?, ?, ?, ?, ?,"
					+ " ?, ?, ?, now());");
			ps.setInt(1, housingKey);
			ps.setString(2, facebookID);
			ps.setString(3, comment);
			for (int i = 4; i <= 8; ++i) {
				ps.setString(i, ratings[i - 4]);
			}
			ps.setInt(9, Integer.parseInt(rent));
			ps.executeUpdate();

			// Now update that housinglocation's average rating fields
			try {
				ps.close();
			} catch (SQLException e) { /* Do nothing */ }
			ps = conn.prepareStatement("UPDATE HousingLocations SET averageManagement=?, "
					+ "averageAmenities=?, averageLocation=?, averageNoise=?, "
					+ "averageCommunityChillFactor=?, averageRent=? WHERE housingKey=?");
			if (hl.reviews != null) {
				ps.setString(1, new DecimalFormat("#.##").format((hl.managementScore / hl.reviews.length + Integer.parseInt(ratings[0])) * (hl.reviews.length + 1)));
				ps.setString(2, new DecimalFormat("#.##").format((hl.amenitiesScore / hl.reviews.length + Integer.parseInt(ratings[1])) * (hl.reviews.length + 1)));
				ps.setString(3, new DecimalFormat("#.##").format((hl.locationScore / hl.reviews.length + Integer.parseInt(ratings[2])) * (hl.reviews.length + 1)));
				ps.setString(4, new DecimalFormat("#.##").format((hl.noiseScore / hl.reviews.length + Integer.parseInt(ratings[3])) * (hl.reviews.length + 1)));
				ps.setString(5, new DecimalFormat("#.##").format((hl.communityChillFactorScore / hl.reviews.length + Integer.parseInt(ratings[4])) * (hl.reviews.length + 1)));
				ps.setString(6, new DecimalFormat("#.##").format((hl.averageRent + Integer.parseInt(rent)) / hl.reviews.length * (hl.reviews.length + 1)));
			} else {
				ps.setString(1, new DecimalFormat("#.##").format(hl.managementScore));
				ps.setString(2, new DecimalFormat("#.##").format(hl.amenitiesScore));
				ps.setString(3, new DecimalFormat("#.##").format(hl.locationScore));
				ps.setString(4, new DecimalFormat("#.##").format(hl.noiseScore));
				ps.setString(5, new DecimalFormat("#.##").format(hl.communityChillFactorScore));
				ps.setString(6, new DecimalFormat("#.##").format(hl.averageRent));
			}
			ps.setInt(7, housingKey);
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
				review = new Review (reviewKey, rs.getInt("housingKey"), rs.getString("facebookID"),
						rs.getInt("managementScore"), rs.getInt("amenitiesScore"),
						rs.getInt("locationScore"), rs.getInt("noiseScore"),
						rs.getInt("communityChillFactorScore"), rs.getString("textComment"),
						rs.getInt("rentPaid"), rs.getString("timeWritten"));
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

		return review;
	}

}
