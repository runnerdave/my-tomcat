package net.runnerdave.my_tomcat.simple.http;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HttpServer {
	
	private static final Logger logger = LogManager.getLogger(HttpServer.class);

	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer();
		logger.info("value of webroot:" + WEB_ROOT);
		httpServer.await();
	}

	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";

	// the shutdown command received
	private boolean shutdown = false;

	private void await() {
		ServerSocket mySocket = null;
		int port = 8181;
		int backlog = 1;		

		try {
			mySocket = new ServerSocket(port, backlog, InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			logger.error("unknown host exception");
		} catch (IOException e) {
			logger.error(e.getMessage());
		}

		while (!shutdown) {
			Socket socket = null;
			InputStream input = null;
			OutputStream output = null;
			
			try {
				socket = mySocket.accept();
				input = socket.getInputStream();
				output = socket.getOutputStream();
				
				Request request = new Request(input);
				request.parse();
				
				Response response = new Response(output);
				response.setRequest(request);
				response.setStaticResource();
				
				
				socket.close();
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			} catch (IOException e) {
				logger.error(e.getMessage());
				continue;
			}
		}
	}

}
