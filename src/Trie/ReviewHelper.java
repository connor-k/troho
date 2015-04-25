package Trie;

import java.util.Vector;

import sql.HousingDataManager;
import sql.HousingLocation;
import sql.Review;

public class ReviewHelper {
	public static Vector<Review> pruneReviews(String houseName, boolean [] tags) {
		HousingLocation location = HousingDataManager.getHousingLocation(houseName);
		Review [] reviewArray = location.reviews;
		Vector<Review> reviews = new Vector<Review>();
		
		for(int i = 0; i < reviewArray.length; i++) {
			if(checkValidReview(reviewArray[i], tags)) {
				reviews.add(reviewArray[i]);
			}
		}
		return reviews;
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
