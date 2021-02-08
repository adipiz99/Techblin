package control;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import model.DriverManagerConnectionPool;

public class ServletListener implements ServletContextListener {
	
	public void contextInitialized(ServletContextEvent event) {
		 
    	DriverManagerConnectionPool dmcp= new DriverManagerConnectionPool();
    	System.out.println("Inizializzata connessione al DB: " + dmcp.getClass().getName());
 
    }
 

    public void contextDestroyed(ServletContextEvent arg0) {
    	
    }
}
