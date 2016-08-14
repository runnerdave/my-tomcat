package net.runnerdave.my_tomcat.simple.http;

import java.io.IOException;
import java.io.InputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Request {
	private static final Logger logger = LogManager.getLogger(Request.class);

	private InputStream input;
	private String uri;

	public Request(InputStream input) {
		this.input = input;
	}

	public void parse() {
		// read a string of characters from the socket
		StringBuilder request = new StringBuilder(2048);
		int i;
		byte[] buffer = new byte[2048];
		try {
			i = input.read(buffer);
		} catch (IOException e) {
			logger.error(e.getMessage());
			i = -1;
		}
		for (int j = 0; j < buffer.length; j++) {
			request.append((char)buffer[j]);			
		}
		logger.info(request.toString());
		uri = parseUri(request.toString());
	}

	private String parseUri(String requestString) {
		String[] uriContents = requestString.split(" ");
		if (uriContents.length > 1) {
			return uriContents[1];
		} else {
			return null;
		}
	}

	public String getUri() {
		return uri;
	}
}
