/** DataManagerTest Class
 * CSCI 201 Final Project - Troho
 * Description: Test the functionality of different DataManager classes
 */

package sql;

public class DataManagerTest {
	/** Test the UserDataManager
	 * Test looking up users, creating new users, and setting location & survey points
	 */
	public static void userDataTest() {
		// Update some data for existing users
		UserDataManager.setLocation("2weuhfdsu2j34f1d4", "1");
		String[] surveyPoints = {"10", "10", "0", "0", "5"};
		UserDataManager.setSurveyPoints("12rehfdsu2j34f1d4", surveyPoints);
		// Existing users
		System.out.println(UserDataManager.createUser("Steve", "steve@asdf.asdf", "1", "2weuhfdsu2j34f1d4"));
		System.out.println(UserDataManager.createUser("John", "john@asdf.asdf", "2", "12rehfdsu2j34f1d4"));
		// New user
		System.out.println(UserDataManager.createUser("NewUserAlex", "alex@asdf.asdf", "2", "2348hfdsu2j34f1d4"));
	}
	
	/** Test the HousingDataManager
	 * Test looking up houses
	 */
	public static void housingDataTest() {
		// Existing locations
		System.out.println(HousingDataManager.getHousingLocation(1));
		System.out.println(HousingDataManager.getHousingLocation(2));
		//TODO new location
	}

	public static void main(String[] args) {
		userDataTest();
		housingDataTest();
	}
}
