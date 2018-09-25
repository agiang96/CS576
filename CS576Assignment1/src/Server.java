/* File: Server.java
 * Author: Alexander Giang
 * Class: CS576, Dr. Wang
 * 
 * This is a simple TCP Server file
 * adapted from: https://systembash.com/a-simple-java-tcp-server-and-tcp-client/
 */
import java.io.*;
import java.net.*;

public class Server {
	public static void main(String argv[]) throws Exception {
		String fromClient;
		String editedClientMessage;

		//connects to Client
		ServerSocket socket = new ServerSocket(5678);
		Socket connectSocket = socket.accept();
		System.out.println("Connected!");
		//gets the input from client
		BufferedReader inFromClient = new BufferedReader(
				new InputStreamReader(connectSocket.getInputStream()));
		DataOutputStream outToClient = new DataOutputStream(connectSocket.getOutputStream());
		fromClient = inFromClient.readLine();

		System.out.println("Received: " + fromClient);
		char[] asciiChange = fromClient.toCharArray();
		//ASCII offset per Assignment1 Requirements
		for( int i = 0; i < asciiChange.length; i++) {
			asciiChange[i]++;
		} 
		editedClientMessage = new String(asciiChange) + '\n';
		System.out.print("To Client: " + editedClientMessage);
		outToClient.writeBytes(editedClientMessage);
		socket.close();

	}// end of main
}// end of Server
