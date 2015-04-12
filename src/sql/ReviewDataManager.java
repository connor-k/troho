/** UserDataManager Class
 * CSCI 201 Final Project - Troho
 * Description: Interface between our Review SQL data and JSP
 */

package sql;

public class ReviewDataManager {
	/** 
	 * @param userKey the user who's writing this review
	 * @param housingKey the location this review is for
	 * @param ratings the integer scores given to each review category
	 * @param comment the text comment associated with the review
	 * @param rent the rent paid by the user (currently optional)
	 */
	public static void createReview(int userKey, int housingKey, String[] ratings, String comment, String rent) {
		//TODO handle overwriting an existing review if wanted
	}
	
	/** 
	 * @param reviewKey SQL database key for this review
	 * @return the key of the use who wrote this review
	 */
	public static int getUserKey(int reviewKey) {
		
	}
	
	/** 
	 * @param reviewKey the database key to this review
	 * @return a Review object with all the Review data
	 * @see Review 
	 */
	public static Review getReview(int reviewKey) {
		
	}
	
}
