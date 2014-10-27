/*
 * Abre el socket de la cache esperando a la conexion, abre un hilo por cada peticion
 * 
 * Argumentos:
 * cache_num = Numero de la cache
 * cache_lines = Numero de lineas en las caches
 * Server_IP = Ip en la que se encuentra el servidor
 * users_in_cache = Numero de usuarios que tiene la cache (Definido previamente en el dataset)
 * cache_umbral = Porcentaje de followers que tiene que tener el que realiza la peticion, umbral eCousin
 * cache_type = fifo o lru
 */

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
	
	public static  LRUCache<String,String> c;
	public static double porc=0;
	public static double porc_bytes=0;
	public static int next1=0;
	
	public static  LRUCache<String,String> c2;
	public static int hit2=0;
	public static int cache_lines2;
	public static long hits_bytes2=0;
	public static double porc2=0;
	public static double porc_bytes2=0;
	public static int next2=0;
	
	final static int _transferPort = 60000;
	public static double costeLRU = 0;
	public static double costeECO = 0;
	public static double costeLRU_PUSH = 0;
	public static double costeECO_PUSH = 0;
	private Socket cache_transfersocket = null;
	private ServerSocket cachetransferSocket = null;
	public static double enhance=0;
	public static double enhance2=0;
	public static Stats frame;
	public static int cache_num;
	public static int umbral;
	public static String cache_type;
	
	public static  LRUCache<String,String> c3;
	public static int hit3=0;
	public static int cache_lines3;
	public static long hits_bytes3=0;
	public static double porc3=0;
	public static double porc_bytes3=0;
	public static int next3=0;
	
	public static  LRUCache<String,String> c4;
	public static int hit4=0;
	public static int cache_lines4;
	public static long hits_bytes4=0;
	public static double porc4=0;
	public static double porc_bytes4=0;
	public static int next4=0;

	public static void main(String[] args) {
		
		if (args.length > 0) {
		    try {
		    	cache_num = Integer.parseInt(args[0]);
		    	cache_lines = Integer.parseInt(args[1]);
		    	Cache_TransferRequestHandler.server_ip = args[2];
		    	users_by_cache = Integer.parseInt(args[3]);
		    	umbral = Integer.parseInt(args[4]);
		    	cache_type = args[5];
		    } catch (Exception e) {
		        System.err.println("Cache_Server.jar cache_number cache_lines Server_IP users_in_cache cache_umbral cache_type");
		    }
		}
		else{
			System.err.println("Cache_Server.jar cache_number cache_lines Server_IP users_in_cache cache_umbral cache_type");
			System.exit(1);
		}
		
		
		new Cache_Server().run();
	}
	public void run() {
		try {
			Socket socket = null;
			PrintWriter out = null;
			BufferedReader in = null;
			boolean LRU = true;
			if (cache_type.startsWith("fifo")){
				LRU = false;
			}
			c = new LRUCache<String, String>(cache_lines,LRU);
			c2 = new LRUCache<String, String>(cache_lines,LRU);
			c3 = new LRUCache<String, String>(cache_lines,LRU);
			c4 = new LRUCache<String, String>(cache_lines,LRU);
			
			socket = new Socket(Cache_TransferRequestHandler.server_ip, 55555);
			 
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
 
			String fromServer;
			String fromCache = null;
			
			while ((fromServer = in.readLine()) != null) {
				if (fromServer.equals("exit"))
					break;
				if (fromServer.startsWith("......")){
					fromCache = "New_Cache," + cache_num + "," + InetAddress.getLocalHost().getHostAddress() +","+ users_by_cache;
					if (fromCache != null) {
						//System.out.println("Client - " + fromUser);
						synchronized (socket){
							out.println(fromCache);
						}
					}
				}
			}
			out.close();
			in.close();
			socket.close();
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