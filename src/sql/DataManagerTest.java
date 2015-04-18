/** DataManagerTest Class
 * CSCI 201 Final Project - Troho
 * Description: Test the functionality of different DataManager classes and demonstrate
 * how to use the various functions
 */

package sql;

public class DataManagerTest {
	/** Test the UserDataManager
	 * Test looking up users, creating new users, and setting location & survey points
	 */
	public static void userDataTest() {
		System.out.println("\nTesting the UserDataManager class ---");
		
		// Create a new user: pass the name, email, current housing location key, and Facebook id
		System.out.println("Trying to create a new user with Facebook ID 2348hfdsu2j34f1d4");
		User alex = UserDataManager.createUser("NewUserAlex", "alex@asdf.asdf", 2, "2348hfdsu2j34f1d4");
		System.out.println(alex);

		// Get information about an existing user
		System.out.println("\nLooking up user with Facebook ID 2weuhfdsu2j34f1d4");
		User john = UserDataManager.getUser("2weuhfdsu2j34f1d4");
		System.out.println(john);

		// Update the current location: pass Facebook id and the key of the new location
		System.out.println("\nSetting their location to be 1");
		UserDataManager.setLocation("2weuhfdsu2j34f1d4", 1);
		System.out.println(john);

		// Set the survey preferences of a user: pass the Facebook id and string array of points
		System.out.println("\nSetting their survey points to be 10 10 0 0 5");
		String[] surveyPoints = {"10", "10", "0", "0", "5"};
		UserDataManager.setSurveyPoints("2weuhfdsu2j34f1d4", surveyPoints);
		System.out.println(john);
		
		System.out.println("Done testing UserDataManager class ---\n");
	}

	/** Test the HousingDataManager
	 * Test looking up houses
	 */
	public static void housingDataTest() {
		// Existing locations
		System.out.println(HousingDataManager.getHousingLocation(1));
		System.out.println(HousingDataManager.getHousingLocation(2));
		System.out.println(HousingDataManager.getHousingLocation("First House"));
		//TODO new location
	}

	public static void main(String[] args) {
		userDataTest();
		housingDataTest();
	}
}
