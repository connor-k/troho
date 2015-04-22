/** User Class
 * CSCI 201 Final Project - Troho 
 * Description: Combines all relevant data for a user.
 */

package sql;

public class User {
	/** The SQL key for this user */
	public String facebookID;
	
	/** Data about this user */
	public String name;
	public String email;
	public boolean verifiedEmail;
	public boolean isAdmin;
	public HousingLocation currentLocation;
	public int managementSurveyScore;
	public int amenitiesSurveyScore;
	public int locationSurveyScore;
	public int noiseSurveyScore;
	public int communityChillFactorSurveyScore;
	public Review[] reviewsWritten;
	public String[] friendIDs;
	
	/** Overrides toString
	 * @Override
	 * @return String representation of User object
	 */
	@Override
	public String toString() {
		return "User:\n name: " + name + "\n email: " + email + "\n facebookID: " + facebookID
				+ "\n admin: " + isAdmin + "\n verified: " + verifiedEmail + "\n survey scores: "
				+ managementSurveyScore + "|" + amenitiesSurveyScore + "|" + locationSurveyScore 
				+ "|" + noiseSurveyScore + "|" + communityChillFactorSurveyScore + "\n reviews: " 
				+ reviewsWritten + "\n friends: " + friendIDs + "\n location: " 
				+ currentLocation.locationName;
	}
	
}
