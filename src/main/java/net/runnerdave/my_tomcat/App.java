package net.runnerdave.my_tomcat;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Hello world!
 *
 */
public class App {

    // Define a static logger variable so that it references the
    // Logger instance named "MyApp".
    private static final Logger logger = LogManager.getLogger(App.class);
    
	public static void main(String[] args) {
		System.out.println("Hello World!");
		logger.info("testing log4j2");
		
        logger.trace("Entering application.");
        
        logger.trace("Exiting application.");
	}
}
