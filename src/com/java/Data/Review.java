package com.java.Data;

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
	public int rentPaid;
	public String timeWritten;
}
