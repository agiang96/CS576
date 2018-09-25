/* File: Client.java
 * Author: Alexander Giang
 * Class: CS576, Dr. Wang
 * 
 * This is a simple TCP Client file
 * adapted from: https://systembash.com/a-simple-java-tcp-server-and-tcp-client/
 */

import java.io.*;
import java.net.*;

public class Client {
	 public static void main(String argv[]) throws Exception {
		  String sentence;
		  String modifiedInput;
		  
		  //connects to client
		  BufferedReader inFromUser = new BufferedReader( new InputStreamReader(System.in));
		  Socket clientSocket = new Socket("localhost", 5678);
		  DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
		  //gets input from user
		  System.out.println("Connected! Enter a message: ");
		  BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		  sentence = inFromUser.readLine();
		  //prompts user to only input a 256 char limited message
		  while (sentence.length() > 256 ) {
			  System.out.println("Message is too long, please try again");
			  sentence = inFromUser.readLine();
		  }	
		  //sends message to server for ASCII encoding
		  outToServer.writeBytes(sentence + '\n');		  
		  modifiedInput = inFromServer.readLine();	  
		  System.out.println("Server: " + modifiedInput);
		  clientSocket.close(); 

	 }//end of main
}//end of Client
