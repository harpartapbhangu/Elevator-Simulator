package iter0;

import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 * The Floor subsystem will send a receive from the Scheduler.
 */
public class Floor {
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket sendReceiveSocket;
	
	public Floor()
	{
		try {
			sendReceiveSocket = new DatagramSocket();
		} catch (SocketException se) {
			se.printStackTrace();
			System.exit(1);
		}
	}
	
	
	/**
	 * Sends and receives data packets to/from the scheduler.
	 * 
	 * @param inputMsg
	 */
	public void sendAndReceive(String inputMsg)
	{
		//Send to Scheduler
		//TODO:New data structure should be formatted here
		//TODO:We also need to determine when we want to validate the information entered into the data structure
		byte[] msg = inputMsg.getBytes();
		
		try {
			//TODO:Not sure if we want to use the same ports as in Assignment1?
			sendPacket  = new DatagramPacket(msg, msg.length, InetAddress.getLocalHost(), 23);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("Floor: Sending packet");
		System.out.println("To host:   " + sendPacket.getAddress());
		System.out.println("Destination host port:	" + sendPacket.getPort());
		int len = sendPacket.getLength();
		System.out.println("Length: " + len);
		System.out.println("Containing: ");
		String msgSendStr = new String(sendPacket.getData(),0,len);
		System.out.println("Packet data in bytes: " + Arrays.toString(msgSendStr.getBytes()));
		System.out.println("Packet data as a string: " + msgSendStr);
		
		//Send the datagram packet to the scheduler via the send/receive socket.
		try {
			sendReceiveSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Floor: Packet sent\n");
		
		//Receive from Scheduler
		byte data[] = new byte[100];
		receivePacket = new DatagramPacket(data, data.length);
		
		System.out.println("Floor: Waiting for packet.");
		//Block until a datagram packet is received.
		try {
			System.out.println("Waiting...");
			sendReceiveSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//Process the received datagram
		System.out.println("Floor: Packet received");
		System.out.println("From host:   " + receivePacket.getAddress());
		System.out.println("Host port:   " + receivePacket.getPort());
		len = receivePacket.getLength();
		System.out.println("Length: " + len);
		System.out.println("Containing: ");
		String msgReceiveStr = new String(receivePacket.getData(),0,len);
		System.out.println("Packet data in bytes: " + Arrays.toString(msgReceiveStr.getBytes()));
		System.out.println("Packet data as a string: " + msgReceiveStr + "\n");
		
		sendReceiveSocket.close();
	}
	

	public static void main (String args[])
	{
		Floor floorSubSystem = new Floor();
		System.out.println("Sending test message\n");
		floorSubSystem.sendAndReceive("This is a test");
	}
}
