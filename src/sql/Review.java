/** Review Class
 * CSCI 201 Final Project - Troho 
 * Description: Combines all relevant data for a review.
 */

package sql;

public class Review {
	/** The SQL key for this review and the user who wrote it */
	public int reviewKey;
	public int housingKey;
	public String facebookID;
	
	/** The five review sections */
	public int managementScore;
	public int amenitiesScore;
	public int locationScore;
	public int noiseScore;
	public int communityChillFactorScore;
	
	/** Other relevant information about this review */
	public String comment;
	public boolean[] tags;
	public int rentPaid;
	public String timeWritten;
	
	/**  
	 */
	Review(int reviewKey, int housingKey, String facebookID, int managementScore, int amenitiesScore, int locationScore,
			int noiseScore, int communityChillFactorScore, String comment, boolean[] tags, int rentPaid,
			String timeWritten) {
		this.reviewKey = reviewKey;
		this.housingKey = housingKey;
		this.facebookID = facebookID;
		this.managementScore = managementScore;
		this.amenitiesScore = amenitiesScore;
		this.locationScore = locationScore;
		this.noiseScore = noiseScore;
		this.communityChillFactorScore = communityChillFactorScore;
		this.comment = comment;
		this.tags = tags;
		this.rentPaid = rentPaid;
		this.timeWritten = timeWritten;
	}
	
	private String getTagsString() {
		String s = "";
		if (tags != null) {
			for (int i = 0; i < tags.length; ++i) {
				s += tags[i] + (i != tags.length - 1 ? "|" : "");
			}
		}
		return s;
	}
	
	/** Overrides toString
	 * @Override
	 * @return String representation of Review object
	 */
	@Override
	public String toString() {
		return "Review:\n reviewKey: " + reviewKey + "\n housing key:" + housingKey 
				+ "\n facebookID: " + facebookID + "\n review scores: " + managementScore + "|" 
				+ amenitiesScore + "|" + locationScore + "|" + noiseScore + "|" 
				+ communityChillFactorScore + "\n comment: " + comment + "\n tags: " + getTagsString() +
				"\n rent: " + rentPaid + "\n time: " + timeWritten;
	}
}
