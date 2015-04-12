/** Review Class
 * CSCI 201 Final Project - Troho 
 * Description: Combines all relevant data for a review.
 */

package sql;

public class Review {
	/** The SQL key for this review and the user who wrote it */
	public int reviewKey;
	public int userKey;
	
	/** The five review sections */
	public int managementScore;
	public int amenitiesScore;
	public int locationScore;
	public int noiseScore;
	public int communityChillFactorScore;
	
	/** Other relevant information about this review */
	public String comment;
	public int rentPaid;
	public String timeWritten;
	
	Review(int reviewKey, int userKey, int managementScore, int amenitiesScore, int locationScore,
			int noiseScore, int communityChillFactorScore, String comment, int rentPaid,
			String timeWritten) {
		this.reviewKey = reviewKey;
		this.userKey = userKey;
		this.managementScore = managementScore;
		this.amenitiesScore = amenitiesScore;
		this.locationScore = locationScore;
		this.noiseScore = noiseScore;
		this.communityChillFactorScore = communityChillFactorScore;
		this.comment = comment;
		this.rentPaid = rentPaid;
		this.timeWritten = timeWritten;
	}
}
