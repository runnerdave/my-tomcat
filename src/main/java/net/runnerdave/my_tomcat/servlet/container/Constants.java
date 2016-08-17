package net.runnerdave.my_tomcat.servlet.container;

import java.io.File;

public class Constants {
	public static final String WEB_ROOT =
		    System.getProperty("user.dir") + File.separator  + "webroot";
	
	public static final int BUFFER_SIZE = 1024;
}
