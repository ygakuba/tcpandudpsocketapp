/**
 * 
 */
package mum.net.tcp;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Implementation of a TCP Server
 * 
 * @author Yves Gakuba
 */
public class TCPServer {

	public static Integer SERVER_PORT = 12000;

	public static void main(String[] args) throws IOException {
		ServerSocket sSocket = new ServerSocket(SERVER_PORT);

		boolean waitMsgFromClient = true;
		// wait for the message from the client
		while(waitMsgFromClient){
			Socket connectionSocket = sSocket.accept();
			BufferedReader br = new BufferedReader(new InputStreamReader(connectionSocket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(connectionSocket.getOutputStream());
			String clientName = br.readLine();
			
			if(clientName.trim().compareToIgnoreCase("") != 0){
				System.out.print("Client: " + clientName);
				
				//Create the response message
				String serverMsg = "Hello " + clientName + '\n';
				System.out.println("\nServer: " + serverMsg);
				outToClient.writeBytes(serverMsg);
				waitMsgFromClient = false;
			}
		}
		sSocket.close(); //close the socket
	}
}
