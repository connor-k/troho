/** User Class
 * CSCI 201 Final Project - Troho 
 * Description: Combines all relevant data for a user.
 */

package sql;

public class User {
	/** The SQL key for this user */
	public int userKey;
	
	/** Data about this user */
	public String name;
	public String email;
	public HousingLocation currentLocation;
	public int[] surveyPreferences;
	public Review[] reviewsWritten;
	public User[] friends;
	//TODO may need something like verified (account/email)
	
}
