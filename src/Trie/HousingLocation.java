/** House Class
 * CSCI 201 Final Project - Troho 
 * Description: Combines all relevant data for a housing location.
 */

package Trie;

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
	public String[] gpsCoordinates;
	public String distanceToCampus;
	public int overallScore;
	public int managementScore;
	public int amenitiesScore;
	public int locationScore;
	public int noiseScore;
	public int communityChillFactorScore;
	public double averageRent;
	
	/** Reviews for this location */
	public Review[] reviews;
	
	/** Overrides toString
	 * @Override
	 * @return String representation of HousingLocation object
	 */
	@Override
	public String toString() {
		return "HousingLocation: name: " + locationName + " type: " + type;
	}
}



