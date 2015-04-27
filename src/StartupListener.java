import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import sql.HousingDataManager;
import sql.HousingLocation;
import sql.Startup;
import Trie.SearchHelper;

/**
 * Application Lifecycle Listener implementation class FirstListener
 *
 */
@WebListener
public class StartupListener implements ServletContextListener {
	private ServletContext context = null;
	private Startup startupThread;
	
    /**
     * Default constructor. 
     */
    public StartupListener() {
        startupThread = new Startup();
        startupThread.start();
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  {
    	System.out.println("Server stopped");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // Join with startupThread now to ensure db is initialized
    	try {
			startupThread.join();
		} catch (InterruptedException ie) {
			System.out.println("StartupListener startupThread ie: " + ie.getMessage());
		}
    	
    	context = arg0.getServletContext();
        try {
        	HousingLocation[] hlocations = HousingDataManager.getAllHousingLocations();
        	Vector<HousingLocation> hlv = new Vector<HousingLocation>();
        	for (int i = 0; i < hlocations.length; i++) {
        		hlv.add(hlocations[i]);
        	}
        	SearchHelper searchHelper = new SearchHelper();   	
            context.setAttribute("searchHelper", searchHelper); 
        } catch (Exception ex) {
            System.out.println("Couldn’t create database: " + ex.getMessage());
        }
    	
    	System.out.println("Server Started");
    }
	
}
