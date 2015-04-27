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
		User alex = UserDataManager.createUser("NewUserAlex", "alex@asdf.asdf", "http://sparksc.org/img/coreteam/NeelBhoopalam.jpg", "2348hfdsu2j34f1d4");
		System.out.println(alex);
		// Verify that user (email valid).
		if (alex != null) {
			UserDataManager.setValidationKey(alex.facebookID, "321refasdlfkja");
			// They get email with URL containing that code, servlet calls:
			if (UserDataManager.verifyEmail(alex.facebookID, "321refasdlfkja")) {
				System.out.println("Success verification!");
			} else {
				System.out.println("Failed verification!");
			}
		}

		// Add friends
		String[] friends = {"3weuhfdsu2j34f1d4", "2weuhfdsu2j34f1d4"};
		UserDataManager.setFriends("2348hfdsu2j34f1d4", friends);
		// Get updates
		alex = UserDataManager.getUser("2348hfdsu2j34f1d4");
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

		// Create a new admin user: pass the name, email, current housing location key, and Facebook id, and true
		System.out.println("Trying to create a new Admin user with Facebook ID 298o4ufjasid8ufi");
		boolean isAdmin = true;
		User adminUser = UserDataManager.createUser("NewAdminUser", "admin@asdf.asdf", "http://sparksc.org/img/coreteam/NeelBhoopalam.jpg", "298o4ufjasid8ufi", isAdmin);
		System.out.println(adminUser);
		// Verify that user (email valid)
		if (adminUser != null) {
			UserDataManager.setValidationKey(adminUser.facebookID, "A9ieFSDjfalsd");
			UserDataManager.verifyEmail(adminUser.facebookID, "A9ieFSDjfalsd");
			adminUser = UserDataManager.getUser(adminUser.facebookID);
			System.out.println(adminUser);
		}

		System.out.println("Done testing UserDataManager class ---\n");
	}

	/** Test the HousingDataManager
	 * Test looking up houses
	 */
	public static void housingDataTest() {
		System.out.println("\nTesting the HousingDataManager class ---");
		// Create a new location
		System.out.println("\nCreating a new location (Gateway Apartments)");
		HousingDataManager.createHousingLocation(HousingType.APARTMENT, "Gateway Apartments",
				"3335 S Figuroa St", "Apartments close to campus.", "* Some Amenities", "images/icons/Gateway.png",
				"images/floorplans/Gateway.png", "-118.2797771", "34.0233683", "10", "5");

		// Get an existing location
		// By name
		System.out.println("\nGetting Gateway by name");
		HousingLocation hl = HousingDataManager.getHousingLocation("Gateway Apartments");
		System.out.println(hl);
		// By key
		System.out.println("\nGetting Gateway by key");
		hl = HousingDataManager.getHousingLocation(3);
		System.out.println(hl);

		// Get another location
		System.out.println("\n Getting a different house");
		hl = HousingDataManager.getHousingLocation("First House");
		System.out.println(hl);

		// Try to get an invalid house
		System.out.println("\n Trying to get an invalid house");
		hl = HousingDataManager.getHousingLocation("asdf");

		// Select all houses
		System.out.println("\n Printing all location names");
		HousingLocation[] allLocations = HousingDataManager.getAllHousingLocations();
		for (int i = 0; i < allLocations.length; ++i) {
			System.out.println(allLocations[i].locationName);
		}
		
		// Rent over time
		System.out.println("\n Rent over time");
		Object[] data = HousingDataManager.getRentOverTimeData("The Lorenzo");
		String[] years = (String[])data[0];
		Double[] averageRent = (Double[])data[1];
		for (int i = 0; i < years.length && i < averageRent.length; ++i) {
			System.out.println(" Entry: " + years[i] + " " + averageRent[i]);
		}
		System.out.println("Done testing HousingDataManager class ---\n");
	}

	/** Test the ReviewDataManager
	 */
	public static void reviewDataTest() {
		System.out.println("\nTesting the ReviewDataManager class ---");
		// Create a review
		System.out.println("\nCreating a new review for Gateway Apartments");

		// Get an existing location
		System.out.println("\nGetting Gateway by name");
		HousingLocation hl = HousingDataManager.getHousingLocation("Gateway Apartments");
		System.out.println(hl);

		// Create review for that location
		String[] ratings = {"5", "4", "5", "3", "5"};
		boolean[] tags = {false, false, true, false, true, true};
		ReviewDataManager.createReview(hl.housingKey, "3weuhfdsu2j34f1d4", "Nice place to live, close to campus.", ratings, tags, "1000");
		// Update hl to hold the new data
		hl = HousingDataManager.getHousingLocation("Gateway Apartments");
		System.out.println(hl);
		
		// Check hasReviewedLocation is now true
		System.out.println(HousingDataManager.hasReviewedLocation("3weuhfdsu2j34f1d4", hl.locationName));
		System.out.println("Done testing ReviewDataManager class ---");
	}

	public static void main(String[] args) {
		userDataTest();
		housingDataTest();
		reviewDataTest();
	}
}
