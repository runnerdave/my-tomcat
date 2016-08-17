package net.runnerdave.my_tomcat.servlet.container;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class HttpServer1 {
	private static final Logger logger = LogManager.getLogger(HttpServer1.class);

	public static void main(String[] args) {
		HttpServer1 httpServer = new HttpServer1();
		logger.info("value of webroot:" + Constants.WEB_ROOT);
		httpServer.await();
	}

	private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
	
	
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
			System.exit(1);
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
				// check if this is a request for a servlet or a static resource
		        // a request for a servlet begins with "/servlet/"
		        if (request.getUri().startsWith("/servlet/")) {
		          ServletProcessor1 processor = new ServletProcessor1();
		          processor.process(request, response);
		        }
		        else {
		          StaticResourceProcessor processor = new StaticResourceProcessor();
		          processor.process(request, response);
		        }
				
				
				socket.close();
				shutdown = request.getUri().equals(SHUTDOWN_COMMAND);
			} catch (IOException e) {
				logger.error(e.getMessage());
				continue;
			}
		}
	}
}
