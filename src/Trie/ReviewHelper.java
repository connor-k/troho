package Trie;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import sql.HousingDataManager;
import sql.HousingLocation;
import sql.Review;

//This class assists with only showing reviews with tags that the user
//is interested in
public class ReviewHelper {
	
	//takes as argument the name of the location and an array of boolean
	//values representing the user's selected tags
	//returns all appropriate reviews
	public static Review [] pruneReviews(String houseName, boolean [] tags) {
		HousingLocation location = HousingDataManager.getHousingLocation(houseName);
		Review [] reviewArray = location.reviews;
		Vector<Review> reviews = new Vector<Review>();
		boolean allFalse = true;
		
		//checks if all tags are set to false
		//if they are, it returns all reviews
		for(int i = 0; i < 6; i++) {
			if(tags[i] == true) {
				allFalse = false;
			}
		}
		if(allFalse) {
			Arrays.sort(reviewArray, new SortReviews());
			return reviewArray;
		}
		
		//if the review has the correct tags, it adds it to the returned array
		for(int i = 0; i < reviewArray.length; i++) {
			if(checkValidReview(reviewArray[i], tags)) {
				reviews.add(reviewArray[i]);
			}
		}
		SortReviews mySorter = new SortReviews();
		
		//returns the arrays sorted from most recent to least recent
		reviews.sort(mySorter);
		
		Review [] reviewResult = new Review[reviews.size()];
		for(int i = 0; i < reviewResult.length; i++) {
			reviewResult[i] = reviews.elementAt(i);
		}
		return reviewResult;
	}
	
	//sorts reviews from most to least recent
	public static void sortReviews(Review [] reviews) {
		Arrays.sort(reviews, new SortReviews());
	}
	
	//checks if a single review is compatible with the boolean tags
	static boolean checkValidReview(Review review, boolean []tags) {
		boolean [] reviewTags = review.tags;
		for(int i = 0; i < 6; i++) {
			if((reviewTags[i] == true) && (tags[i] == true)) {
				return true;
			}
		}
		return false;
	}
	
	//compares two reviews by when they were written
	private static class SortReviews implements Comparator<Review> {
		public int compare(Review r1, Review r2) {
			String r1Time = r1.timeWritten;
			String r2Time = r2.timeWritten;
			if(r1Time.compareTo(r2Time) < 0) {
				return 1;
			} else if (r1Time.compareTo(r2Time) > 0) {
				return -1;
			}
			return 0;
		}
	}
}

