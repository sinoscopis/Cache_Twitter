package com.twitter.jdbc.cache_server;

import java.io.File;
import java.util.Map;

/**
 * Handles business logic of cache application.
 */
public class Cache_BusinessLogic {

	public String processInput(String clientRequest) {
		String reply = null;
		String peticion[];
		try {
			if(clientRequest != null && clientRequest.equalsIgnoreCase("exit")) {
				return "exit";
			}
			else if(clientRequest != null && clientRequest.startsWith("Consumir,")) {
				peticion = clientRequest.split(",", 2);
				reply = consumirContenido(peticion[1]);
			}
			else {
				reply = "Esperando peticiones ...";
			}
		} catch(Exception e) {
			System.out.println("input process falied: " + e.getMessage());
			return "exit";
		}

		return reply;
	}
	
	public static String consumirContenido(String url){
		Cache_Server.peticiones ++;
		if (Cache_Server.c.inCache(url)){
			//hit++;
			//System.out.println("ESTA EN CACHE - > "+hit);
		}
		else{
			int next = Cache_Server.c.usedEntries()+1;
			Cache_Server.c.put (Integer.toString(next), url);
			System.out.println(+Cache_Server.peticiones+ "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! - > "+url);
			for (Map.Entry<String, String> e : Cache_Server.c.getAll())
			      System.out.println (e.getKey() + " : " + e.getValue());
		}
		return "";
	}
}