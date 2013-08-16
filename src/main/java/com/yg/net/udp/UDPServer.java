/**
 * 
 */
package com.yg.net.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Implementation of a UDP Server
 * 
 * @author Yves Gakuba
 */
public class UDPServer {

	public static Integer SERVER_PORT = 12000;

	public static void main(String[] args) throws Exception {
		DatagramSocket serverSocket = new DatagramSocket(SERVER_PORT);
		byte[] data = new byte[1024];
		DatagramPacket packet;

		boolean waitMsgFromClient = true;
		// wait for the message from the client
		while(waitMsgFromClient){
			// Receive the name from the client
			packet = new DatagramPacket(data, data.length);
			serverSocket.receive(packet);
			String clientName = new String(packet.getData());
			
			System.out.print("Client: " + clientName);
			
			if(clientName.trim().compareToIgnoreCase("") != 0){
				// Get client address information
				InetAddress IPClientAddress = packet.getAddress();
				int port = packet.getPort();
				
				//Create the response message
				String serverMsg = "Hello " + clientName;
				System.out.println("\nServer: " + serverMsg);
				data = new byte[1024];
				data = serverMsg.getBytes();
				packet = new DatagramPacket(data, data.length, IPClientAddress, port);
				serverSocket.send(packet);
				
				waitMsgFromClient = false;
			}
		}
		serverSocket.close(); //close the socket
	}

}
