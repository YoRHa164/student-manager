package edu.njupt.springmvc.web.listener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;

/**
 * Application Lifecycle Listener implementation class ContextFinalizer
 *
 */
@WebListener
public class ContextFinalizer implements ServletContextListener {
	private static Logger logger = Logger.getLogger(ContextFinalizer.class);
	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	Enumeration<Driver> drivers = DriverManager.getDrivers();
    	while(drivers.hasMoreElements()) {
    		Driver d = null;
    		try {
				d = drivers.nextElement();
				DriverManager.deregisterDriver(d);
				logger.warn(String.format("Driver : %s has been deregistered", d));
			} catch (SQLException e) {
				logger.warn(String.format("Driver : %s deregister error", d), e);
			}
    		
    	}
    	AbandonedConnectionCleanupThread.uncheckedShutdown();
    }

	
}
