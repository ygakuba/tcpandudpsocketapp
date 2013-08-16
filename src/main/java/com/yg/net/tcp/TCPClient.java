/**
 * 
 */
package com.yg.net.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Implementation of a TCP Client
 * 
 * @author Yves Gakuba
 */
public class TCPClient {

	public static String SERVER_NAME = "localhost";
	public static Integer SERVER_PORT = 12000;

	public static void main(String[] args) throws Exception {
		Socket clientSocket = new Socket(SERVER_NAME, SERVER_PORT);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		DataOutputStream oStream = new DataOutputStream(clientSocket.getOutputStream());
		
		// Ask the client user to type the name
		System.out.print("Client: ");
		String name = br.readLine();
		oStream.writeBytes(name + '\n');
		
		br = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		// wait for the response from the server
		String responseMsgFromServer = br.readLine();
		System.out.println("Server: " + responseMsgFromServer);
		
		// close client socket
		clientSocket.close();
	}
}
