import java.io.*;
import java.net.*;

/** 
 * Implemented the HTTP GET method. Program retrieves any valid web page on a web server.
 * Will print only the header associated with the HTTP Response.
 * CTRL + C will terminate the program.
 * 
 * @author - Sasha Jouravlev
 */
class TCPClient2 {

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
		outToServer.writeBytes("HEAD " + resource + " HTTP/1.1\n"+ "Host: " + hostname + "\n\n");

		//Print everything in the BufferedReader
		String upNext;
		while(null!=(upNext=inFromServer.readLine())){
			System.out.println(upNext);
		}

		//Close and Exit
		outToServer.close();
		inFromServer.close();
		clientSocket.close();
		System.exit(1);
	}
}