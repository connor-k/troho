package Trie;

import sql.HousingDataManager;
import sql.HousingLocation;
import sql.User;
import sql.UserDataManager;

//small class that contains a housingLocation and its score
class HousingPref {
	void setPref(HousingLocation house, int preference) {
		this.house = house;
		pref = preference;
	}
	
	void setPref(HousingPref pref) {
		this.house = pref.house;
		this.pref = pref.pref;
	}
	HousingLocation house = null;
	int pref =  -1;
}

//helps suggest new housingLocations to the user
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
		if(houses.length < 4) {
			return houses;
		}
		
		//finds the four houses with the highest predicted ratings for that user
		for(int i = 0; i < houses.length; i++) {
			int score = houseHelper(houses[i], user);
			
			if(score > first.pref) {
				fourth.setPref(third);
				third.setPref(second);
				second.setPref(first);
				first.setPref(houses[i], score);
			} 
			else if (score > second.pref) {
				fourth.setPref(third);
				third.setPref(second);
				second.setPref(houses[i], score);
			}
			else if (score > third.pref) {
				fourth.setPref(third);
				third.setPref(houses[i], score);
			} 
			else if (score > fourth.pref) {
				fourth.setPref(houses[i], score);
			}
		}
		
		HousingLocation [] topHouses = new HousingLocation[4];
		topHouses[0] = first.house;
		topHouses[1] = second.house;
		topHouses[2] = third.house;
		topHouses[3] = fourth.house;
		
		return topHouses;
	}
	
	//helper function that calculates a score for a single house
	//takes as argument the housingLocation and individual user
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
