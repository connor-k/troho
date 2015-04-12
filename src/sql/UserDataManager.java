/** UserDataManager Class
 * CSCI 201 Final Project - Troho
 * Description: Interface between our User SQL data and JSP
 */

package sql;

public class UserDataManager {
	/** 
	 * @param name The user's name
	 * @param email The user's email address (@usc.edu)
	 */
	public static void createUser(String name, String email) {
		//TODO Create a new user if they don't exist, otherwise return existing user. May need to split, determine validation.
	}

	/** 
	 * @param userKey SQL database key for this user
	 * @param location The location this user lives
	 */
	public static void setLocation(int userKey, String location) {

	}

	/** 
	 * @param userKey SQL database key for this user
	 * @param facebookID the Facebook ID for this user
	 */
	public static void setFacebookID(int userKey, String facebookID) {

	}

	/** 
	 * @param userKey SQL database key for this user
	 * @param scores the integer rating for the 5 categories.
	 * @param rentPaid the rent paid by the user (currently optional)
	 * @see Review
	 */
	public static void addReview(int userKey, String[] scores, String rentPaid) {

	}

	/** 
	 * @param userKey SQL database key for this user
	 * @param surveyPoints the survey points between 0 and 10 allocated by the user for the 5 categories
	 */
	public static void setSurveyPoints(int userKey, String[] surveyPoints) {

	}
	
	/** 
	 * @param userKey SQL database key for this user
	 * @return the current point allocations for the five survey categories
	 */
	public static int[] getSurveyPoints(int userKey) {

	}
	
	/** 
	 * @param userKey SQL database key for this user
	 * @return the name of the user
	 */
	public static String getName(int userKey) {

	}

	/** 
	 * @param userKey SQL database key for this user
	 * @return the email of the user
	 */
	public static String getEmail(int userKey) {

	}
	
	/** 
	 * @param userKey SQL database key for this user
	 * @return the Facebook ID associated with this user
	 */
	public static String getFacebookID(int userKey) {

	}

	/** 
	 * @param userKey SQL database key for this user
	 * @return the name of the housing location that this user lives
	 */
	public static String getLocation(int userKey) {

	}
	
	/** 
	 * @param userKey SQL database key for this user
	 * @return an array of the userKeys for all of this user's friends
	 */
	public static int[] getFriends(int userKey) {

	}

	/** 
	 * @param userKey SQL database key for this user
	 * @return all reviews written by this user
	 */
	public static Review[] getReviews(int userKey) {

	}

}
