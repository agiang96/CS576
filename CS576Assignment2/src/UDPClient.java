/* File: UDPClient.java
 * Author: Alexander Giang
 * Class: CS576, Dr. Wang
 * 
 * This is a simple UDP client file that sends a Hello message to the server 
 * and receives a modified version of that message. 
 * adapted from: https://systembash.com/a-simple-java-udp-server-and-udp-client/
 */
import java.net.*;

class UDPClient {
    public static void main(String args[]) throws Exception {
        DatagramSocket clientSocket = new DatagramSocket();  //load in port 5433
        InetAddress IPAddress = InetAddress.getByName("localhost");
        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        String sentence = "Hello";
        sendData = sentence.getBytes();
        DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 5432);
        clientSocket.send(sendPacket);
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);
        //getting the offset and length allows for concatenation, if needed
        String modifiedSentence = new String(receivePacket.getData(), receivePacket.getOffset(), receivePacket.getLength());        
        System.out.println("FROM SERVER: " + modifiedSentence);
        clientSocket.close();
     }
}
