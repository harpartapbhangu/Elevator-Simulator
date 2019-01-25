package iter0;

import java.io.*;
import java.net.*;
import java.util.Arrays;

/**
 * The Scheduler will send/receive from both the Floor subsystem and the Elevators.
 */
public class Scheduler {
	DatagramPacket sendPacket, receivePacket;
	DatagramSocket sendReceiveSocket, receiveSocket;
	
	public Scheduler()
	{
		try {
			receiveSocket = new DatagramSocket(23);
			sendReceiveSocket = new DatagramSocket();
		} catch (SocketException se) {
			se.printStackTrace();
		    System.exit(1);
		}
	}
	
	/**
	 * Receives and sends data packets to and from both the
	 * floor and the elevators.
	 */
	public void receiveAndSend()
	{
		//Receive from Floor
		byte data[] = new byte[100];
		receivePacket = new DatagramPacket(data, data.length);

		System.out.println("Scheduler: Waiting for packet.\n");
		//Block until a datagram packet is received.
		try {
			System.out.println("Waiting...");
			receiveSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		int floorPort = receivePacket.getPort();
		
		//Process received datagram
		System.out.println("Scheduler: Packet received");
		System.out.println("From host:   " + receivePacket.getAddress());
		System.out.println("Host port:   " + receivePacket.getPort());
		int len = receivePacket.getLength();
		System.out.println("Length: " + len);
		System.out.println("Containing: ");
		String msgReceiveFloorStr = new String(receivePacket.getData(),0,len);
		System.out.println("Packet data in bytes: " + Arrays.toString(msgReceiveFloorStr.getBytes()));
		System.out.println("Packet data as a string: " + msgReceiveFloorStr + "\n");
		
		//Send to Elevator
		sendPacket = new DatagramPacket(receivePacket.getData(), receivePacket.getLength(), receivePacket.getAddress(), 69);
		
		System.out.println("Scheduler: Sending packet");
		System.out.println("To host:   " + sendPacket.getAddress());
		System.out.println("Destination host port:   " + sendPacket.getPort());
		len = sendPacket.getLength();
		System.out.println("Length: " + len);
		System.out.println("Containing: ");
		String msgSendElevatorStr = new String(sendPacket.getData(),0,len);
		System.out.println("Packet data in bytes: " + Arrays.toString(msgSendElevatorStr.getBytes()));
		System.out.println("Packet data as a string: " + msgSendElevatorStr);
		
		//Send the datagram packet to the server via the send/receive socket
		try {
			sendReceiveSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		System.out.println("Scheduler: Packet sent\n");
		
		//Receive from Elevator
		//Already created the receivePacket, reset via setting the data array to zero
		Arrays.fill(data, (byte) 0);
		
		System.out.println("Scheduler: Waiting for Packet.\n");
		//Block until a datagram packet is received.
		try {
			System.out.println("Waiting...");
			sendReceiveSocket.receive(receivePacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//Process received datagram
		System.out.println("Scheduler: Packet received");
		System.out.println("From host:   " + receivePacket.getAddress());
		System.out.println("Host port:   " + receivePacket.getPort());
		len = receivePacket.getLength();
		System.out.println("Length: " + len);
		System.out.println("Containing: ");
		String msgReceiveElevatorStr = new String(receivePacket.getData(),0,len);
		System.out.println("Packet data in bytes: " + Arrays.toString(msgReceiveElevatorStr.getBytes()));
		System.out.println("Packet data as a string: " + msgReceiveElevatorStr + "\n");
		
		//Sending to Floor
		try {
			sendPacket = new DatagramPacket(data, receivePacket.getLength(), InetAddress.getLocalHost(), floorPort);
		} catch (UnknownHostException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		//Creates DatagramSocket to use to send to client
		DatagramSocket sendSocket = null;
		try {
			sendSocket = new DatagramSocket();
		} catch (SocketException se) {
			se.printStackTrace();
		    System.exit(1);
		}
		
		System.out.println("Scheduler: Sending packet");
		System.out.println("To host:   " + sendPacket.getAddress());
		System.out.println("Destination host port:   " + sendPacket.getPort());
		len = sendPacket.getLength();
		System.out.println("Length: " + len);
		System.out.println("Containing: ");
		String msgSendFloorStr = new String(sendPacket.getData(),0,len);
		System.out.println("Packet data in bytes: " + Arrays.toString(msgSendFloorStr.getBytes()));
		System.out.println("Packet data as a string: " + msgSendFloorStr);

		//Send the datagram packet to the client
		try {
			sendSocket.send(sendPacket);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		sendSocket.close();
		System.out.println("IntermediateHost: Packet sent\n");
	}
	
	
	public static void main(String args[])
	{
		Scheduler s = new Scheduler();
		while (true) {
			s.receiveAndSend();
		}
	}

}
