package com.twitter.jdbc.cache_server;

import java.net.*;
import java.io.*;

import cache.LRUCache;

 
/**
 * Demo Server: Contains a multi-threaded socket server sample code.
 */
public class Cache_Server extends Thread
{
	final static int _portNumber = 66666; //Arbitrary port number
	private Socket _socket = null;
	private PrintWriter _out = null;
	private BufferedReader _in = null;
	private ServerSocket serverSocket = null;
	static int peticiones=0;
	static LRUCache<String,String> c = new LRUCache<String, String>(100);

	public static void main(String[] args) {
		new Cache_Server().run();
	}
	public void run() {
		
		try {
			 serverSocket = new ServerSocket(_portNumber);
		
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + _portNumber);
			System.exit(-1);
		}
		
		try {
			while(true){
				_socket=serverSocket.accept();
				Runnable connectionRequesthandler = new Cache_ConnectionRequestHandler(_socket);
				new Thread(connectionRequesthandler).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally { //In case anything goes wrong we need to close our I/O streams and sockets.
			try {
				_out.close();
				_in.close();
				_socket.close();
			} catch(Exception e) { 
				System.out.println("Couldn't close I/O streams");
			}
		}
	}
}