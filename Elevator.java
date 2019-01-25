package iter0;

import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 * The Elevator sends and receives from the Scheduler.
 */
public class Elevator {
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket receiveSocket;
	
	public Elevator()
	{
		try {
			receiveSocket = new DatagramSocket(69);
		} catch (SocketException se) {
			se.printStackTrace();
		    System.exit(1);
		}
	}
	
	
	/**
	 * Receives and send from/to the scheduler.
	 */
	public void receiveAndSend()
	{
		//Receive from Scheduler
		byte data[] = new byte[100];
		receivePacket = new DatagramPacket(data, data.length);

		System.out.println("Elevator: Waiting for Packet.\n");
		//Block until a datagram packet is received from receiveSocket.
		try {
			System.out.println("Waiting...");
			receiveSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//Process received datagram
		System.out.println("Elevator: Packet received");
		System.out.println("From host:   " + receivePacket.getAddress());
		System.out.println("Host port:   " + receivePacket.getPort());
		int len = receivePacket.getLength();
		System.out.println("Length: " + len);
		System.out.println("Containing: ");
		String msgReceiveStr = new String(receivePacket.getData(),0,len);
		System.out.println("Packet data in bytes: " + Arrays.toString(msgReceiveStr.getBytes()));
		System.out.println("Packet data as a string: " + msgReceiveStr);
		
		//Slow things down (wait 5s)
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//Send to Scheduler
		//TODO: Reply msg - Currently sending 111 back to the Scheduler
		byte[] elevatorReceiveMsg = new byte[]{1 , 1 , 1};
		sendPacket = new DatagramPacket(elevatorReceiveMsg, elevatorReceiveMsg.length, receivePacket.getAddress(), receivePacket.getPort());
		
		System.out.println("Elevator: Sending packet");
		System.out.println("To host:   " + sendPacket.getAddress());
		System.out.println("Destination host port:   " + sendPacket.getPort());
		len = sendPacket.getLength();
		System.out.println("Length: " + len);
		System.out.println("Containing: ");
		String msgSendStr = new String(sendPacket.getData(),0,len);
		System.out.println("Packet data in bytes: " + Arrays.toString(msgSendStr.getBytes()));
		System.out.println("Packet data as a string: " + msgSendStr);
		
		//Create DatagramSocket to use just for this response
		DatagramSocket sendSocket = null;
		try {
			sendSocket = new DatagramSocket();
		} catch (SocketException se) {
			se.printStackTrace();
		    System.exit(1);
		}
		
		//Send the datagram packet to the client via the send socket
		try {
			sendSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Elevator: Packet sent\n");
		sendSocket.close(); //Close socket that was just created
	}
	

	public static void main(String args[])
	{
		Elevator e = new Elevator();
		while (true) {
			e.receiveAndSend();
		}
	}
}
