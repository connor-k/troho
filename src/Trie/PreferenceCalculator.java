package Trie;

import java.util.Vector;

import sql.HousingLocation;
import sql.User;

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
	HousingPref fourth = new HousingPref();
	
	//takes as arguments user preferences and a vector of housing Locations
	//returns a vector with the top three house choices for the user
	Vector<HousingLocation> findPreferences(User user, Vector<HousingLocation> houses) {
		Vector<HousingLocation> topHouses= new Vector<HousingLocation>(4);
		int [] topHousePrefs = new int[4];
		if(houses.size() < 4) {
			return houses;
		}
		
		for(int i = 0; i < houses.size(); i++) {
			int score = houseHelper(houses.elementAt(i), user);
			if(score > topHousePrefs[0]) {
				fourth = third;
				third = second;
				second = first;
				first.setPref(houses.elementAt(i), score);
			} 
			else if (score > topHousePrefs[1]) {
				fourth = third;
				third = second;
				second.setPref(houses.elementAt(i), score);
			}
			else if (score > topHousePrefs[2]) {
				fourth = third;
				third.setPref(houses.elementAt(i), score);
			} 
			else if (score > topHousePrefs[3]) {
				third.setPref(houses.elementAt(i), score);
			}
		}
		topHouses.add(0, first.house);
		topHouses.add(1, second.house);
		topHouses.add(2, third.house);
		topHouses.add(3, fourth.house);
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
