package Trie;

import java.util.Vector;

class HousingPref {
	void setPref(HousingLocation house, int preference) {
		this.house = house;
		pref = preference;
	}
	HousingLocation house;
	int pref =  -1;
}

public class PreferenceCalculator {
	HousingPref first = new HousingPref();
	HousingPref second = new HousingPref();
	HousingPref third = new HousingPref();
	int [] preferences = new int[3];
	
	//takes as arguments user preferences and a vector of housing Locations
	//returns a vector with the top three house choices for the user
	Vector<HousingLocation> findPreferences(User user, Vector<HousingLocation> houses) {
		this.preferences = preferences;
		Vector<HousingLocation> topHouses= new Vector<HousingLocation>(3);
		int [] topHousePrefs = new int[3];
		if(houses.size() < 3) {
			return houses;
		}
		
		for(int i = 0; i < houses.size(); i++) {
			int score = houseHelper(houses.elementAt(i), user);
			if(score > topHousePrefs[0]) {
				third = second;
				second = first;
				first.setPref(houses.elementAt(i), score);
			} 
			if (score > topHousePrefs[1]) {
				third = second;
				second.setPref(houses.elementAt(i), score);
			}
			if (score > topHousePrefs[0]) {
				third.setPref(houses.elementAt(i), score);
			}
		}
		topHouses.add(0, first.house);
		topHouses.add(1, second.house);
		topHouses.add(2, third.house);
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
