package net.runnerdave.my_tomcat.simple.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;

public class HttpServer {

	public static void main(String[] args) {
		HttpServer httpServer = new HttpServer();
		httpServer.await();
	}
	
	// the shutdown command received
	  private boolean shutdown = false;

	private void await() {
		ServerSocket mySocket = null;
		int port = 8181;
		int backlog = 1;
		
		try {
			mySocket = new ServerSocket(port, backlog, InetAddress.getByName("127.0.0.1"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(!shutdown) {
			
		}
	}

}
