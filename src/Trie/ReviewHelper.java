package Trie;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import sql.HousingDataManager;
import sql.HousingLocation;
import sql.Review;
import sql.User;
import sql.UserDataManager;

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
			Arrays.sort(reviewArray, new SortReviews());
			return reviewArray;
		}
		
		for(int i = 0; i < reviewArray.length; i++) {
			User myUser = UserDataManager.getUser(reviewArray[i].facebookID);
			System.out.println("");
			System.out.println("Printing" + myUser.name + "'s" + " reviews");
			
			if(checkValidReview(reviewArray[i], tags)) {
				reviews.add(reviewArray[i]);
			}
		}
		System.out.println("");
		SortReviews mySorter = new SortReviews();
		
		reviews.sort(mySorter);
		
		Review [] reviewResult = new Review[reviews.size()];
		for(int i = 0; i < reviewResult.length; i++) {
			reviewResult[i] = reviews.elementAt(i);
		}
		
		return reviewResult;
	}
	
	public static void sortReviews(Review [] reviews) {
		Arrays.sort(reviews, new SortReviews());
	}
	
	static boolean checkValidReview(Review review, boolean []tags) {
		boolean [] reviewTags = review.tags;
		for(int i = 0; i < 6; i++) {
			System.out.print(reviewTags[i] + " ");
			if((reviewTags[i] == true) && (tags[i] == true)) {
				return true;
			}
		}
		return false;
	}
	
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

