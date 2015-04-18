/** House Class
 * CSCI 201 Final Project - Troho 
 * Description: Combines all relevant data for a housing location.
 */

package sql;

public class HousingLocation {
	/** The SQL key for this housing location */
	public int housingKey;

	/** Data about this location */
	public HousingType type;
	public String locationName;
	public String address;
	public String description;
	public String imageURL;
	public String floorplanURL;
	public String gpsLatitude;
	public String gpsLongitude;
	public int minutesWalking;
	public int minutesBiking;
	public double overallScore;
	public double managementScore;
	public double amenitiesScore;
	public double locationScore;
	public double noiseScore;
	public double communityChillFactorScore;
	public double averageRent;

	/** Reviews for this location */
	public Review[] reviews;

	private String formatReviews() {
		String s = "\nReviews for this location:\n";
		if (reviews != null) {
			for (int i = 0; i < reviews.length; ++i) {
				s += reviews[i] + "\n";
			}
		}
		return s;
	}

	/** Overrides toString
	 * @Override
	 * @return String representation of HousingLocation object
	 */
	@Override
	public String toString() {
		return "HousingLocation:\n name: " + locationName + "\n type: " + type + "\n address: "
				+ address + "\n description: " + description + "\n imageURL: " + imageURL
				+ "\n floorplanURL: " + floorplanURL + "\n gps: " + gpsLatitude + "," 
				+ gpsLongitude + "\n distance: " + minutesWalking + "|" + minutesBiking 
				+ "\n scores: " + overallScore + "|" + managementScore + "|" + amenitiesScore
				+ "|" + locationScore + "|" + noiseScore + "|" + communityChillFactorScore
				+ "\n average rent: " + averageRent + formatReviews();
	}
}
