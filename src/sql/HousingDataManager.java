/** UserDataManager Class
 * CSCI 201 Final Project - Troho
 * Description: Interface between our Housing SQL data and JSP
 */

package sql;

public class HousingDataManager {
	/** 
	 * @param name the name of this location
	 * @param they HousingType for this location (dorm, etc)
	 * @param description a short text description of this location
	 * @param iconURL the URL of the background image for this location
	 * @param floorplanURL the URL to the floorplan image for this location
	 * @param address the text address of this location
	 * @param distance the distance of this location from campus (walking & biking)
	 */
	public void createHousingLocation(String name, HousingType type, String descrpition, String iconURL, String floorplanURL, String address, String distance) {
		//TODO get/set gps location from text
	}
	
	/** 
	 * @param housingKey the database key for this housing location
	 * @return a HousingLocation object with all the housing data
	 * @see HousingLocation 
	 */
	public static HousingLocation getHousingLocation(int housingKey) {
		
	}
	
}
