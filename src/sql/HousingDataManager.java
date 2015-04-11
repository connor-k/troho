/** UserDataManager Class
 * CSCI 201 Final Project - Troho
 * Description: Interface between our Housing SQL data and JSP
 */

package sql;

public class HousingDataManager {
	/** 
	 * @param name the name of this location
	 * @param description a short text description of this location
	 * @param iconURL the URL of the background image for this location
	 * @param floorplanURL the URL to the floorplan image for this location
	 * @param address the text address of this location
	 * @param distance the distance of this location from campus (walking & biking)
	 */
	public void createHousingLocation(String name, String descrpition, String iconURL, String floorplanURL, String address, String distance) {
		//TODO get/set gps location from text
	}
	
	/** 
	 * @param housingKey the database key for this housing location
	 * @return the name of this location
	 */
	public String getName(int housingKey) {
		
	}
	
	/** 
	 * @param housingKey the database key for this housing location
	 * @return the short text description for this location
	 */
	public String getDescription(int housingKey) {
		
	}
	
	/** 
	 * @param housingKey the database key for this housing location
	 * @return the URL for the location's background image
	 */
	public String getIconURL(int housingKey) {
		
	}
	
	/** 
	 * @param housingKey the database key for this housing location
	 * @return the URL for the location's floorplan image
	 */
	public String getFloorplanURL(int housingKey) {
		
	}
	
	/** 
	 * @param housingKey the database key for this housing location
	 * @return the gps coordinates of this location
	 */
	public String[] getGPSCoordinates(int housingKey) {
		//TODO probably need to change
	}
	
	/** 
	 * @param housingKey the database key for this housing location
	 * @return the text address of this location
	 */
	public String getAddress(int housingKey) {
		
	}
	
	/** 
	 * @param housingKey the database key for this housing location
	 * @return the distance to cmapus
	 */
	public String getDistanceToCampus(int housingKey) {
		
	}
	
	/** 
	 * @param housingKey the database key for this housing location
	 * @return the average ratings for each of the 5 categories of this location
	 */
	public double[] getAverageRatings(int housingKey) {
		
	}
	
	/** 
	 * @param housingKey the database key for this housing location
	 * @return the average rent paid by reviewers
	 */
	public double getAverageRent(int housingKey) {
		
	}
	
	/** 
	 * @param housingKey the database key for this housing location
	 * @return an array containing all reviews for this housing location
	 */
	public static Review[] getReviews(int housingKey) {
		
	}
}
