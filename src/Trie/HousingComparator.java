package Trie;

import java.util.Comparator;

//comparator class that compares houses based on user preferences, used for sorting
class HousingComparator implements Comparator<HousingLocation> {
	private int managementScore;
	private int amenitiesScore;
	private int locationScore;
	private int communityChillFactorScore;
	//passes in user importance scores
	HousingComparator(int managementScore, int amenitiesScore, int locationScore,
			int noiseScore, int communityChillFactorScore) {
		this.managementScore = managementScore;
		this.amenitiesScore = amenitiesScore;
		this.locationScore = locationScore;
		this.communityChillFactorScore = communityChillFactorScore;
	}
	//returns -1 if house 1 is less than house 2
	//returns 0 if house 2 and house 1 are equal
	//returns 1 if house 2 is less than house 1
	public int compare(HousingLocation h1, HousingLocation h2) {
		int h1Score = scoreCalc(h1);
		int h2Score = scoreCalc(h2);
		if(h1Score < h2Score) {
			return -1;
		} else if(h1Score == h2Score) {
			return 0;
		}
		return 1;
	} 
	//calculates score for a single house based on user preference
	private int scoreCalc(HousingLocation h) {
		int score = 0;
		score += managementScore*h.managementScore;
		score += amenitiesScore*h.amenitiesScore;
		score += locationScore*h.locationScore;
		score += communityChillFactorScore*h.communityChillFactorScore;
		
		return score;
		
	}
	
}