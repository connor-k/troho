

import java.util.Vector;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import sql.*;
import Trie.*;

/**
 * Application Lifecycle Listener implementation class FirstListener
 *
 */
@WebListener
public class FirstListener implements ServletContextListener {

	private ServletContext context = null;
	
    /**
     * Default constructor. 
     */
    public FirstListener() {
        // TODO Auto-generated constructor stub

    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
 
    	
        // TODO Auto-generated method stub
    	System.out.println("Server stopped");
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    	
    	context = arg0.getServletContext();
        try {
        	
        	HousingLocation[] hlocations = HousingDataManager.getAllHousingLocations();
        	
        	Vector<HousingLocation> hlv = new Vector<HousingLocation>();
        	
        	for(int i = 0; i <hlocations.length; i++ ) {
        		hlv.add(hlocations[i]);
        	}
        	
        	Trie wordsTrie = new Trie(hlv);
        	
            context.setAttribute("wordsTrie", wordsTrie);
        } catch (Exception ex) {
            System.out.println(
                "Couldn’t create database: " + ex.getMessage());
        }
    	
    	System.out.println("Server Started");
    }
	
}
