package Trie;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;

import sql.HousingLocation;
import sql.HousingType;
import sql.HousingDataManager;

public class SearchHelper {
	HousingComparator myHouseComp;
	private Trie myTrie;
	
	//default constructor, starts with all houses in trie
	SearchHelper() {
		HousingLocation [] houseArray = HousingDataManager.getAllHousingLocations();
		Vector<HousingLocation> houses = new Vector<HousingLocation>(Arrays.asList(houseArray));
		myTrie = new Trie(houses);
	}
	
	//takes as argument a new housing location
	//runs if the user wants to add a new house
	void addHouse(HousingLocation newHouse) {
		myTrie.add(newHouse);
	}
	
	//takes as argument user preferences and a vector of houses
	//sorts both of them based on predicted user preference
	void sortPruneHouses(int managementScore, int amenitiesScore, int locationScore,
			int noiseScore, int communityChillFactorScore, String searchWords, 
			int maxPrice, int maxDistance, boolean isHouse, boolean isDorm, 
			boolean isApartment, int minRating) {
		
		Vector<HousingLocation> houses = findHouse(searchWords);
		pruneHouses(maxPrice, maxDistance, isHouse, isDorm, isApartment, minRating, houses);
		myHouseComp = new HousingComparator(managementScore, amenitiesScore, locationScore,
				noiseScore, communityChillFactorScore);
		houses.sort(myHouseComp);	
	}
	
	//takes as argument cut-off preferences and all possible houses
	//returns a vector of houses with only houses that fit the description
	private Vector<HousingLocation> pruneHouses(int maxPrice, int maxDistance, 
			boolean isHouse, boolean isDorm, boolean isApartment, int minRating,
			Vector<HousingLocation> houses) {
		Vector<HousingLocation> prunedHouses = houses;
		
		//removes houses that do not fit criteria
		Iterator<HousingLocation> myIt = houses.iterator();
		while(myIt.hasNext()) {
			HousingLocation house = myIt.next();
			boolean isValid = isValidHouse(maxPrice, maxDistance, isHouse, isDorm, 
					isApartment, minRating, house);
			if(!isValid) {
				prunedHouses.remove(house);
			}
		}	
		return prunedHouses;	
	}
	
	//helper function that checks if a single house fulfills the requirements
	//returns true if the house is valid
	private boolean isValidHouse(int maxPrice, int maxDistance, 
			boolean isHouse, boolean isDorm, boolean isApartment, int minRating,
			HousingLocation house) {
		boolean checkHouse = (house.type == HousingType.HOUSE);
		boolean checkApartment = (house.type == HousingType.APARTMENT);
		boolean checkDorm = (house.type == HousingType.DORM);
		
		if(house.averageRent > maxPrice) {
			return false;
		} else if (Integer.parseInt(house.distanceToCampus) > maxDistance) {
			return false;
		} else if (checkHouse != isHouse) {
			return false;
		} else if (checkApartment != isApartment) {
			return false;
		} else if (checkDorm != isDorm) {
			return false;
		} else if (house.overallScore < minRating) {
			return false;
		}
		return true;
	}
	
	//takes as argument user search words
	//returns a vector of houses matching those words
	//the returned vector is roughly sorted by relevance
	Vector<HousingLocation> findHouse(String searchWords) {
		return myTrie.findPartialWord(searchWords);
	}
}