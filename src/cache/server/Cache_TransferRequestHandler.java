package cache.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cache_TransferRequestHandler implements Runnable{
		
		static String server_ip;
		static int Cache_num;
	
		private Socket _socket = null;
		private DataOutputStream dout = null;
		private DataInputStream din = null;
 
		public Cache_TransferRequestHandler(Socket socket) {
			_socket = socket;
		}
 
		public void run() {
			System.out.println("Client connected to cache socket: " + _socket.toString());
 
			try {
				din=new DataInputStream(_socket.getInputStream());
				dout=new DataOutputStream(_socket.getOutputStream());
 
				System.out.println("FTP Client Connected ...");
				while(true)
				{
					try
					{
					System.out.println("Waiting for Command Cache...");
					String Command=din.readUTF();
					if(Command.compareTo("GET")==0)
					{
						System.out.println("\tGET Command Received ...");
						SearchinCache(din,dout);
						break;
					}
					else if(Command.compareTo("DISCONNECT")==0)
					{
						System.out.println("\tDisconnect Command Received ...");
						System.exit(1);
					}
					}
					catch(Exception e)
					{
						System.out.println("////////////////////////////");
						//e.printStackTrace();
						break;
					}
				}
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}		
		}
 
		public static String SearchinCache(DataInputStream din, DataOutputStream dout) throws Exception{
			String peticion=din.readUTF();
			String[] args = peticion.split("/", 3);
			Cache_num=Integer.parseInt(args[0]);
			String file = args[1];
			boolean del = false;
			int[] petitionFrindsByCache = new int [5];
			String[] caches = args[2].split(",");
			petitionFrindsByCache[0]=Integer.parseInt(caches[1]);
			petitionFrindsByCache[1]=Integer.parseInt(caches[3]);
			petitionFrindsByCache[2]=Integer.parseInt(caches[5]);
			petitionFrindsByCache[3]=Integer.parseInt(caches[7]);
			petitionFrindsByCache[4]=Integer.parseInt(caches[9]);
			
			Cache_Server.peticiones ++;
			del = cache_stats(file,dout,petitionFrindsByCache,del);
			
			Cache_Server.porc = (100 * (double) Cache_Server.hit)/(double) Cache_Server.peticiones;
			Cache_Server.porc_bytes = (100 * (double) Cache_Server.hits_bytes)/(double) Cache_Server.peticiones_bytes;
			
			Cache_Server.porc2 = (100 * (double) Cache_Server.hit2)/(double) Cache_Server.peticiones;
			Cache_Server.porc_bytes2 = (100 * (double) Cache_Server.hits_bytes2)/(double) Cache_Server.peticiones_bytes;
			SendFile(file,dout);
			
			if (del==true){
				DeleteFile(file);
			}
			Cache_Server.frame.refresh_stats();
			return "Procesado";
		}
		
		private static void RegistrarFile(String file, String cache_type) {
			Socket socket = null;
			PrintWriter out = null;
			BufferedReader in = null;
			try {
				socket = new Socket(Cache_TransferRequestHandler.server_ip, 55555);
	 
				out = new PrintWriter(socket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	 
				String fromServer;
				String fromUser = null;
				
				
				while ((fromServer = in.readLine()) != null) {
					//System.out.println("Server - " + fromServer);
					if (fromServer.equals("exit"))
						break;
					else if (fromServer.startsWith("......")){						
						if (cache_type == "LRU")
							fromUser = "fileInLRUCache," + file+","+ Cache_Server.cache_num;
						else 
							fromUser = "fileInECOCache," + file+","+ Cache_Server.cache_num;						
							
						if (fromUser != null) {
							//System.out.println("Client - " + fromUser);
							synchronized (socket){
								out.println(fromUser);
							}
						}
					}
				}
			} catch (UnknownHostException e) {
				System.err.println("Cannot find the host: " + Cache_TransferRequestHandler.server_ip);
				System.exit(1);
			} catch (IOException e) {
				System.out.println("Couldn't read/write from the connection: " +e.toString() );
				e.printStackTrace();
				System.exit(1);
			} finally { //Make sure we always clean up	
				try {
					out.close();
					in.close();
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		}

		private static void DeleteFile(String file) {
			try{
				String sSistemaOperativo = System.getProperty("os.name");
				String file_path = null;
				if(sSistemaOperativo.startsWith("Win")){
					file_path = "C:\\Users\\Alberto\\Desktop\\Cache_eco_Content\\"+file;
					//file_path = ".\\Cache_eco_Content\\"+file;
				}
				else {
					file_path = "./Cache_eco_Content/"+file;
				}
				File f=new File(file_path);
				if(f.delete()){
	    			System.out.println(f.getName() + " is deleted!");
	    		}else{
	    			System.out.println("Delete operation is failed.");
	    		}
			}catch (Exception e){
				e.printStackTrace();
			}
		}

		private static boolean cache_stats(String file, DataOutputStream dout, int[] petitionFrindsByCache, boolean del) throws Exception {
			if (Cache_Server.c.inCache(file)){
				String key = Cache_Server.c.getval(file);
				Cache_Server.c.get(key);
				long peticion_size=Procesarbytes(file,"LRU");
				Cache_Server.hit++;
				Cache_Server.hits_bytes=Cache_Server.hits_bytes+peticion_size;
				Cache_Server.peticiones_bytes=Cache_Server.peticiones_bytes+peticion_size;
			}
			else{
				copyFromServer(file,"LRU");
				long peticion_size=Procesarbytes(file,"LRU");
				Cache_Server.peticiones_bytes=Cache_Server.peticiones_bytes + peticion_size;
				
				Cache_Server.next1=Cache_Server.next1+1;
				Cache_Server.c.put (Integer.toString(Cache_Server.next1), file);
				RegistrarFile(file,"LRU");
			}
			if (Cache_Server.c2.inCache(file)){
				String key2 = Cache_Server.c2.getval2(file);
				Cache_Server.c2.get(key2);
				long peticion_size=Procesarbytes(file,"eCousin");
				Cache_Server.hit2++;
				Cache_Server.hits_bytes2=Cache_Server.hits_bytes2+peticion_size;
			}
			else{
				copyFromServer(file,"eCOUSIN");
				del = true;
				int umbral =(int) (Cache_Server.users_by_cache*60)/100;
				
				if (petitionFrindsByCache[Cache_num-1] > umbral) {
					Cache_Server.next2=Cache_Server.next2+1;
					Cache_Server.c2.put (Integer.toString(Cache_Server.next2), file);
					del = false;
					RegistrarFile(file,"eCOUSIN");
				}
			}
			return del;
		}

		private static long Procesarbytes(String file, String cache) {
			String sSistemaOperativo = System.getProperty("os.name");
			String file_path = null;
			if (cache == "LRU")
				if(sSistemaOperativo.startsWith("Win")){
					file_path = "C:\\Users\\Alberto\\Desktop\\Cache_Content\\"+file;
					//file_path = ".\\Cache_Content\\"+file;
				}
				else {
					
					file_path = "./Cache_Content/"+file;
				}
			else
				if(sSistemaOperativo.startsWith("Win")){
					file_path = "C:\\Users\\Alberto\\Desktop\\Cache_eco_Content\\"+file;
					//file_path = ".\\Cache_Content\\"+file;
				}
				else {
					
					file_path = "./Cache_eco_Content/"+file;
				}
			File f=new File(file_path);
			return f.length();
		}

		static void SendFile(String filename, DataOutputStream dout) throws Exception
		{		
			String sSistemaOperativo = System.getProperty("os.name");
			String file_path = null;
			if(sSistemaOperativo.startsWith("Win")){
				file_path = "C:\\Users\\Alberto\\Desktop\\Cache_Content\\"+filename;
				//file_path = ".\\Cache_Content\\"+filename;
			}
			else {
				file_path = "./Cache_Content/"+filename;
			}
			File f=new File(file_path);
			if(!f.exists())
			{
				dout.writeUTF("File Not Found");
			}
			else
			{
				try{
					dout.writeUTF("READY");
					System.out.println("Sending File ...");
					FileInputStream fin=new FileInputStream(f);
					int ch;
					do
					{
						ch=fin.read();
						dout.writeUTF(String.valueOf(ch));
					}
					while(ch!=-1);
					fin.close();	
					dout.writeUTF("File Send Successfully");
				}catch (Exception e){
					System.out.println("................."+filename+".............");
					e.printStackTrace();
				}
			}
		}
		
		private static void copyFromServer(String file, String cache) {
			Socket ClientSoc = null;

			DataInputStream din;
			DataOutputStream dout;
			//BufferedReader br;
			try{
			
				//InetAddress host = InetAddress.getLocalHost();
				ClientSoc = new Socket(server_ip, 55055);
				
				din=new DataInputStream(ClientSoc.getInputStream());
				dout=new DataOutputStream(ClientSoc.getOutputStream());
				//br=new BufferedReader(new InputStreamReader(System.in));
				
				dout.writeUTF("GET");
				if (cache == "LRU")
					ReceiveFile(din,dout,file);
				else 
					ReceiveFile2(din,dout,file);
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		public static void ReceiveFile(DataInputStream din, DataOutputStream dout, String filename) throws Exception
		{
			String sSistemaOperativo = System.getProperty("os.name");
			String file_path = null;
			if(sSistemaOperativo.startsWith("Win")){
				file_path = "C:\\Users\\Alberto\\Desktop\\Cache_Content\\"+filename;
				//file_path = ".\\Cache_Content\\"+filename;
			}
			else {
				file_path = "./Cache_Content/"+filename;
			}
			dout.writeUTF(filename);
			String msgFromServer=din.readUTF();
			
			if(msgFromServer.compareTo("File Not Found")==0)
			{
				System.out.println("File not found on Server ...");
				return;
			}
			else if(msgFromServer.compareTo("READY")==0)
			{
				System.out.println("Receiving File ...");
				File f=new File(file_path);
				if(f.exists())
				{
					dout.flush();
					return;					
				}
				FileOutputStream fout=new FileOutputStream(f);
				int ch;
				String temp;
				do
				{
					temp=din.readUTF();
					ch=Integer.parseInt(temp);
					if(ch!=-1)
					{
						fout.write(ch);					
					}
				}while(ch!=-1);
				fout.close();
				System.out.println(din.readUTF());	
			}
		}
		
		public static void ReceiveFile2(DataInputStream din, DataOutputStream dout, String filename) throws Exception
		{
			String sSistemaOperativo = System.getProperty("os.name");
			String file_path = null;
			if(sSistemaOperativo.startsWith("Win")){
				file_path = "C:\\Users\\Alberto\\Desktop\\Cache_eco_Content\\"+filename;
				//file_path = ".\\Cache_eco_Content\\"+filename;
			}
			else {
				file_path = "./Cache_eco_Content/"+filename;
			}
			dout.writeUTF(filename);
			String msgFromServer=din.readUTF();
			
			if(msgFromServer.compareTo("File Not Found")==0)
			{
				System.out.println("File not found on Server ...");
				return;
			}
			else if(msgFromServer.compareTo("READY")==0)
			{
				System.out.println("Receiving File ...");
				File f=new File(file_path);
				if(f.exists())
				{
					dout.flush();
					return;					
				}
				FileOutputStream fout=new FileOutputStream(f);
				int ch;
				String temp;
				do
				{
					temp=din.readUTF();
					ch=Integer.parseInt(temp);
					if(ch!=-1)
					{
						fout.write(ch);					
					}
				}while(ch!=-1);
				fout.close();
				System.out.println(din.readUTF());	
			}
		}
		
	}