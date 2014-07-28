package cache.server;

import java.net.*;
import java.io.*;

import cache.LRUCache;
import cache.display.Stats;

 
/**
 * Demo Server: Contains a multi-threaded socket server sample code.
 */
public class Cache_Server extends Thread
{ //Arbitrary port number
	public static int peticiones=0;
	public static int hit=0;
	public static int cache_lines;
	public static int users_by_cache;
	public static long peticiones_bytes=0;
	public static long hits_bytes=0;
	public static double porc=0;
	public static double porc_bytes=0;
	public static int next1=0;
	public static  LRUCache<String,String> c;
	public static int hit2=0;
	public static int cache_lines2;
	public static long hits_bytes2=0;
	public static double porc2=0;
	public static double porc_bytes2=0;
	public static int next2=0;
	public static  LRUCache<String,String> c2;
	final static int _transferPort = 60000;
	private Socket cache_transfersocket = null;
	private ServerSocket cachetransferSocket = null;
	public static Stats frame;
	public static int cache_num;

	public static void main(String[] args) {
		
		if (args.length > 0) {
		    try {
		    	cache_num = Integer.parseInt(args[0]);
		    	cache_lines = Integer.parseInt(args[1]);
		    	Cache_TransferRequestHandler.server_ip = args[2];
		    	users_by_cache = Integer.parseInt(args[3]);
		    } catch (Exception e) {
		        System.err.println("Cache_Server.jar cache_number cache_lines Server_IP users_in_cache");
		    }
		}
		else{
			System.err.println("Cache_Server.jar cache_lines Server_IP users_in_cache");
			System.exit(1);
		}
		
		
		new Cache_Server().run();
	}
	public void run() {
		
		try {
			c = new LRUCache<String, String>(cache_lines);
			c2 = new LRUCache<String, String>(cache_lines);
			cachetransferSocket = new ServerSocket(_transferPort);
			frame = new Stats();
			frame.start();
			
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