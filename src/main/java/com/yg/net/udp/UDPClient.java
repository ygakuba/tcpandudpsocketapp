/**
 * 
 */
package com.yg.net.udp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Implementation of a UDP Client
 * 
 * @author Yves Gakuba
 */
public class UDPClient {

	public static String SERVER_NAME = "localhost";
	public static Integer SERVER_PORT = 12000;

	public static void main(String[] args) throws Exception {
		byte[] data = new byte[1024];
		DatagramPacket packet;
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPServerAddress = InetAddress.getByName(SERVER_NAME);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		// Ask the client user to type the name
		System.out.print("Client: ");
		String name = br.readLine();
		
		// send message to the server
		data = name.getBytes();
		packet = new DatagramPacket(data, data.length, IPServerAddress, SERVER_PORT);
		clientSocket.send(packet);
		
		boolean waitMsgFromServer = true;
		data = new byte[1024];
		// wait for the response from the server
		while(waitMsgFromServer){
			packet = new DatagramPacket(data, data.length);
			clientSocket.receive(packet);
			String responseMsgFromServer = new String(packet.getData());
			if(responseMsgFromServer.trim().compareToIgnoreCase("") != 0){
				System.out.println("Server: " + responseMsgFromServer);
				waitMsgFromServer = false;
			}
		}
		
		// close the client socket
		clientSocket.close();
	}
}
