package net.runnerdave.my_tomcat.servlet.container;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class StaticResourceProcessor {
	private static final Logger logger = LogManager.getLogger(StaticResourceProcessor.class);
	public void process(Request request, Response response) {
	    try {
	      response.sendStaticResource();
	    }
	    catch (IOException e) {
	      logger.error(e.toString());
	    }
	  }
}
