package Trie;

import java.util.Arrays;
import java.util.Vector;
import sql.HousingLocation;
import sql.HousingType;
import sql.HousingDataManager;

public class SearchHelper {
	HousingComparator myHouseComp;
	private Trie myTrie;
	
	//default constructor, starts with all houses in trie
	public SearchHelper() {
		HousingLocation [] houseArray = HousingDataManager.getAllHousingLocations();
		Vector<HousingLocation> houses = new Vector<HousingLocation>(Arrays.asList(houseArray));
		myTrie = new Trie(houses);
	}
	
	//takes as argument a new housing location
	//runs if the user wants to add a new house
	public void addHouse(HousingLocation newHouse) {
		myTrie.add(newHouse);
	}
	
	//takes as argument user preferences and a vector of houses
	//sorts both of them based on predicted user preference
	public synchronized HousingLocation [] sortPruneHouses(int managementScore, int amenitiesScore, int locationScore,
			int communityChillFactorScore, String searchWords, 
			int maxPrice, int maxDistance, boolean isHouse, boolean isDorm, 
			boolean isApartment, int minRating) {
		
		HousingLocation [] houseArray;
		
		if(!searchWords.equals("")) {
			houseArray = findHouse(searchWords);
		} else {
			houseArray = HousingDataManager.getAllHousingLocations();
		}
		
		Vector<HousingLocation> houses = new Vector<HousingLocation>();
		for(int i = 0; i <houseArray.length; i++) {
			houses.add(houseArray[i]);
		}
		houses = pruneHouses(maxPrice, maxDistance, isHouse, isDorm, isApartment, minRating, houses);
		myHouseComp = new HousingComparator(managementScore, amenitiesScore, locationScore,
				communityChillFactorScore);
		houses.sort(myHouseComp);	
		HousingLocation [] returnHouses = new HousingLocation[houses.size()];
		for(int i = 0; i < houses.size(); i++) {
			returnHouses[i] = houses.elementAt(i);
		}
		
		return returnHouses;
	}
	
	//takes as argument cut-off preferences and all possible houses
	//returns a vector of houses with only houses that fit the description
	private synchronized Vector<HousingLocation> pruneHouses(int maxPrice, int maxDistance, 
			boolean isHouse, boolean isDorm, boolean isApartment, int minRating,
			Vector<HousingLocation> houses) {
		Vector<HousingLocation> prunedHouses = new Vector<HousingLocation>(houses);
		
		//removes houses that do not fit criteria
		for(int i = 0; i < houses.size(); i++) {
			HousingLocation house = houses.elementAt(i);
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
		} else if (house.minutesWalking > maxDistance) {
			return false;
		} else if (!(checkHouse && isHouse) && !(checkApartment && isApartment) && !(checkDorm && isDorm)) {
			return false;
		} else if (house.overallScore < minRating) {
			return false;
		}
		return true;
	}
	
	//takes as argument user search words
	//returns a vector of houses matching those words
	//the returned vector is roughly sorted by relevance
	public synchronized HousingLocation [] findHouse(String searchWords) {
		Vector<HousingLocation> resultsVector = myTrie.findPartialWord(searchWords);
		HousingLocation [] results = new HousingLocation[resultsVector.size()];
		for(int i = 0; i < results.length; i++) {
			results[i] = resultsVector.elementAt(i);
		}
		
		return results;
	}
	
	//used for autocomplete
	//takes as argument partial user search words
	//returns a likely word to complete the search 
	public synchronized String findLikely(String searchWords) {
		return myTrie.findLikely(searchWords);
	}
}