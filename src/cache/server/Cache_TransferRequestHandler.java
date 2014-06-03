package cache.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.Socket;

public class Cache_TransferRequestHandler implements Runnable{
		
		static String server_ip;
	
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
					catch(Exception ex)
					{
						break;
						//ex.printStackTrace();
					}
				}
				
			}
			catch(Exception ex)
			{
			}		
		}
 
		public static String SearchinCache(DataInputStream din, DataOutputStream dout) throws Exception{
			String file=din.readUTF();
			Cache_Server.peticiones ++;
			if (Cache_Server.c.inCache(file)){
				
				long peticion_size=Procesarbytes(file);
				Cache_Server.hit++;
				Cache_Server.hits_bytes=Cache_Server.hits_bytes+peticion_size;
				Cache_Server.peticiones_bytes=Cache_Server.peticiones_bytes+peticion_size;	
			}
			else{
				copyFromServer(file);
				long peticion_size=Procesarbytes(file);
				Cache_Server.peticiones_bytes=Cache_Server.peticiones_bytes + peticion_size;
				
				int next = Cache_Server.c.usedEntries()+1;
				Cache_Server.c.put (Integer.toString(next), file);
			}
			Cache_Server.porc = (100 * (double) Cache_Server.hit)/(double) Cache_Server.peticiones;
			Cache_Server.porc_bytes = (100 * (double) Cache_Server.hits_bytes)/(double) Cache_Server.peticiones_bytes;
			Cache_Server.frame.refresh_stats();
			/*System.out.println("CONTENIDO DE LA CACHE");
			for (Map.Entry<String, String> e : Cache_Server.c.getAll())
			      System.out.println (e.getKey() + " : " + e.getValue());
			System.out.println("ESTADISTICAS");
			System.out.println("      PETICIONES    - > "+Cache_Server.peticiones+ "               bytes ->" + Cache_Server.peticiones_bytes);
			System.out.println("      ACIERTOS      - > "+ Cache_Server.hit + "               bytes ->" + Cache_Server.hits_bytes);
			System.out.println("      P. ACIERTOS   - > " + Cache_Server.porc + " %" + "               bytes ->" + Cache_Server.porc_bytes + " %");
			*/
			SendFile(file,dout);
			return "Procesado";
		}
		
		private static long Procesarbytes(String file) {
			String sSistemaOperativo = System.getProperty("os.name");
			String file_path = null;
			if(sSistemaOperativo.startsWith("Win")){
				file_path = ".\\Cache_Content\\"+file;
			}
			else {
				file_path = "./Cache_Content/"+file;
			}
			File f=new File(file_path);
			return f.length();
		}

		static void SendFile(String filename, DataOutputStream dout) throws Exception
		{		
			String sSistemaOperativo = System.getProperty("os.name");
			String file_path = null;
			if(sSistemaOperativo.startsWith("Win")){
				file_path = ".\\Cache_Content\\"+filename;
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
			}
		}
		
		private static void copyFromServer(String file) {
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
				ReceiveFile(din,dout,file);
			}
			catch(Exception ex){
				
			}
		}
		
		public static void ReceiveFile(DataInputStream din, DataOutputStream dout, String filename) throws Exception
		{
			String sSistemaOperativo = System.getProperty("os.name");
			String file_path = null;
			if(sSistemaOperativo.startsWith("Win")){
				file_path = ".\\Cache_Content\\"+filename;
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
	}