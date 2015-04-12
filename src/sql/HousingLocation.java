/** House Class
 * CSCI 201 Final Project - Troho 
 * Description: Combines all relevant data for a housing location.
 */

package sql;

public class HousingLocation {
	/** The SQL key for this housing location */
	public int housingKey;
	
	/** Type of location */
	public HousingType type;
	
	/** The five review sections */
	public int managementScore;
	public int amenitiesScore;
	public int locationScore;
	public int noiseScore;
	public int communityChillFactorScore;
	
	/** Other relevant information about this review */
	public int rentPaid;
	public String timeWritten;
	
	/** Reviews for this location */
	public Review[] reviews;
}
