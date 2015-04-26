package Trie;

import java.util.Vector;

import sql.HousingDataManager;
import sql.HousingLocation;
import sql.Review;

public class ReviewHelper {
	public static Review [] pruneReviews(String houseName, boolean [] tags) {
		HousingLocation location = HousingDataManager.getHousingLocation(houseName);
		Review [] reviewArray = location.reviews;
		Vector<Review> reviews = new Vector<Review>();
		boolean allFalse = true;
		
		for(int i = 0; i < 6; i++) {
			if(tags[i] == true) {
				allFalse = false;
			}
		}
		if(allFalse) {
			return reviewArray;
		}
		
		for(int i = 0; i < reviewArray.length; i++) {
			if(checkValidReview(reviewArray[i], tags)) {
				reviews.add(reviewArray[i]);
			}
		}
		Review [] reviewResult = new Review[reviews.size()];
		for(int i = 0; i < reviewArray.length; i++) {
			reviewResult[i] = reviews.elementAt(i);
		}
		
		return reviewResult;
	}
	
	static boolean checkValidReview(Review review, boolean []tags) {
		boolean [] reviewTags = review.tags;
		for(int i = 0; i < 6; i++) {
			if(reviewTags[i] != tags[i]) {
				return false;
			}
		}
		return true;
	}
}
