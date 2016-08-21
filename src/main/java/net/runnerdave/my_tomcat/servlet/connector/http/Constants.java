package net.runnerdave.my_tomcat.servlet.connector.http;

import java.io.File;

public class Constants {
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

	public static final int BUFFER_SIZE = 1024;
	public static final String Package = "net.runnerdave.my_tomcat.servlet.connector";
	public static final int DEFAULT_CONNECTION_TIMEOUT = 60000;
	public static final int PROCESSOR_IDLE = 0;
	public static final int PROCESSOR_ACTIVE = 1;

}
