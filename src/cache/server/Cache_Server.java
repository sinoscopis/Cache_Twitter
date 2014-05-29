package cache.server;

import java.net.*;
import java.io.*;

import cache.LRUCache;

 
/**
 * Demo Server: Contains a multi-threaded socket server sample code.
 */
public class Cache_Server extends Thread
{ //Arbitrary port number
	static int peticiones=0;
	static int hit=0;
	static int cache_lines;
	static  LRUCache<String,String> c = new LRUCache<String, String>(100);
	static long peticiones_bytes=0;
	static long hits_bytes=0;
	final static int _transferPort = 60000;
	private Socket cache_transfersocket = null;
	private ServerSocket cachetransferSocket = null;

	public static void main(String[] args) {
		
		if (args.length > 0) {
		    try {
		    	cache_lines = Integer.parseInt(args[0]);
		    	Cache_TransferRequestHandler.server_ip = args[1];
		    } catch (Exception e) {
		        System.err.println("Cache_Server.jar cache_lines Server_IP");
		    }
		}
		else{
			System.err.println("Cache_Server.jar cache_lines Server_IP");
			System.exit(1);
		}
		
		
		new Cache_Server().run();
	}
	public void run() {
		
		try {
			cachetransferSocket = new ServerSocket(_transferPort);
		
		} catch (IOException e) {
			System.err.println("Could not listen on port: " + _transferPort);
			System.exit(-1);
		}
		
		try {
			while(true){
				
				cache_transfersocket=cachetransferSocket.accept();
				
				Runnable cache_transferRequesthandler = new Cache_TransferRequestHandler(cache_transfersocket);
				
				new Thread(cache_transferRequesthandler).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally { //In case anything goes wrong we need to close our I/O streams and sockets.
			try {
				cache_transfersocket.close();
			} catch(Exception e) { 
				System.out.println("Couldn't close I/O streams");
			}
		}
	}
}