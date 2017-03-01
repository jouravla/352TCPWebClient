import java.io.*;
import java.net.*;

/**
 * 
 * 
 * CTRL + C will terminate the program
 * 
 * @author - Sasha Jouravlev
 */
class TCPClient {
	
	public static void main(String args[]) throws Exception{
		//Read in the command line arguments
		String hostname = args[0];
		int port = Integer.parseInt(args[1]);
		String resource = "";
		
		if(args.length == 3){
			resource = args[2];
		}

		//Establish a TCP connection with the mail server.
		Socket clientSocket = new Socket(hostname, port);
		DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

		
		//Send the HTTP Request
		outToServer.writeBytes("GET " + resource + " HTTP/1.1\n"+ "Host: " + hostname + "\n\n");
		
		String upNext;
		while((upNext=inFromServer.readLine())!=null){
			System.out.println(upNext);
		}
		
		inFromServer.close();
		clientSocket.close();
		System.exit(1);
	}
}