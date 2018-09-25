/* File: UDPServer.java
 * Author: Alexander Giang
 * Class: CS576, Dr. Wang
 * 
 * This is a simple UDP server file that adds a humor sentence to the received
 * message from the client and sends it back. 
 * adapted from: https://systembash.com/a-simple-java-udp-server-and-udp-client/
 */
import java.net.*;

class UDPServer {
    public static void main(String args[]) throws Exception {
        DatagramSocket serverSocket = new DatagramSocket(5432); //load in port 5432
        byte[] receiveData = new byte[1024];
        byte[] sendData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        serverSocket.receive(receivePacket);
        //getting the offset and length allows for concatenation
        String sentence = new String(receivePacket.getData(), receivePacket.getOffset(), 
        	receivePacket.getLength());        
        System.out.println("RECEIVED: " + sentence);
        InetAddress IPAddress = receivePacket.getAddress();
        int port = receivePacket.getPort();
        String humor = " - Be a smart feller, not a fart smeller ";
        String addHumorSentence = sentence + humor;
        sendData = addHumorSentence.getBytes();
        DatagramPacket sendPacket =
            new DatagramPacket(sendData, sendData.length, IPAddress, port);
        serverSocket.send(sendPacket);
        serverSocket.close();
    }
}