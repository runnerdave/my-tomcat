package net.runnerdave.my_tomcat.simple.http;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Response {
	private static final Logger logger = LogManager.getLogger(Response.class);

	private static final int BUFFER_SIZE = 1024;
	Request request;
	
	public void setRequest(Request request) {
		this.request = request;
	}

	OutputStream output;
	
	public Response(OutputStream ouStream) {
		this.output = ouStream;
	}
	
	public void setStaticResource() throws IOException {
		byte[] bytes = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try {
			File file = new File(HttpServer.WEB_ROOT, request.getUri());
			if(file.exists()) {
				fis = new FileInputStream(file);
				int ch = fis.read(bytes);
				while(ch!=-1) {
					output.write(bytes, 0, ch);
					ch = fis.read(bytes, 0, BUFFER_SIZE);
				}
			} else {
				StringBuilder errorMessage = new StringBuilder("HTTP/1.1 404 File Not Found\r\n");
				errorMessage.append("Content-Type: text/html\r\n")
							.append("Content-Length: 23\r\n\r\n")
							.append("<h1>File Not Found</h1>");
				output.write(errorMessage.toString().getBytes());
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			if(fis!=null) {
				fis.close();
			}
		}
	}
}
