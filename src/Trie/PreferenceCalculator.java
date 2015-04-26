package Trie;

import sql.HousingDataManager;
import sql.HousingLocation;
import sql.User;
import sql.UserDataManager;

class HousingPref {
	void setPref(HousingLocation house, int preference) {
		this.house = house;
		pref = preference;
	}
	
	void setPref(HousingPref pref) {
		this.house = pref.house;
		this.pref = pref.pref;
	}
	HousingLocation house;
	int pref =  -1;
}

public class PreferenceCalculator {
	
	//takes as arguments user preferences and a ArrayList of housing Locations
	//returns a ArrayList with the top four house choices for the user
	static public HousingLocation[] findPreferences(String facebookID) {
		HousingPref first = new HousingPref();
		HousingPref second = new HousingPref();
		HousingPref third = new HousingPref();
		HousingPref fourth = new HousingPref();
		
		User user = UserDataManager.getUser(facebookID);
		
		HousingLocation [] houses = HousingDataManager.getAllHousingLocations();
		HousingLocation [] topHouses = new HousingLocation[4];
		int [] topHousePrefs = new int[4];
		for (int i = 0; i < 4; i++) {
			topHousePrefs[i] = -1;
		}
		if(houses.length < 4) {
			return houses;
		}
		
		for(int i = 0; i < houses.length; i++) {
			int score = houseHelper(houses[i], user);
			System.out.println(score);
			if(score > topHousePrefs[0]) {
				fourth.setPref(third);
				third.setPref(second);
				second.setPref(first);
				first.setPref(houses[i], score);
				topHousePrefs[0] = score;
			} 
			else if (score > topHousePrefs[1]) {
				fourth.setPref(third);
				third.setPref(second);
				second.setPref(houses[i], score);
				topHousePrefs[1] = score;
			}
			else if (score > topHousePrefs[2]) {
				fourth.setPref(third);
				third.setPref(houses[i], score);
				topHousePrefs[2] = score;
			} 
			else if (score > topHousePrefs[3]) {
				fourth.setPref(houses[i], score);
				topHousePrefs[3] = score;
			}
		}

		topHouses[0] = first.house;
		topHouses[1] = second.house;
		topHouses[2] = third.house;
		topHouses[3] = fourth.house;		
		return topHouses;
	}
	
	static int houseHelper(HousingLocation house, User user) {
		int score = 0;
		score += user.managementSurveyScore*house.managementScore;
		score += user.amenitiesSurveyScore*house.amenitiesScore;
		score += user.locationSurveyScore*house.locationScore;
		score += user.noiseSurveyScore*house.noiseScore;
		score += user.communityChillFactorSurveyScore*house.communityChillFactorScore;
		
		return score;
	}
}
