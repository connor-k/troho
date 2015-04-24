package Trie;

import java.util.Vector;

import sql.HousingDataManager;
import sql.HousingLocation;
import sql.Review;

public class ReviewHelper {
	Vector<Review> pruneReviews(String houseName) {
		HousingLocation location = HousingDataManager.getHousingLocation(houseName);
		Review [] reviewArray = location.reviews;
		Vector<Review> reviews = new Vector<Review>();
		
		for(int i = 0; i < reviewArray.length; i++) {
			reviews.add(reviewArray[i]);
		}
		return reviews;
	}
	
	//boolean checkValidReview(Review review, )
}
