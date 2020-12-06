import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.*;

public class Sender implements Runnable{
	private String address;
	private int port;
	private MulticastSocket s;
	BufferedReader stdIn;
	String input;
	
	public Sender(String address, int port) throws IOException{
		this.address = address;
		this.port = port;
		s = new MulticastSocket();
		stdIn = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public void sendMessage(String message) throws IOException{
		DatagramPacket d = new DatagramPacket(message.getBytes(), message.length(), 
			InetAddress.getByName(this.address), this.port);
		s.send(d);
	}
	
	@Override
	public void run(){
		try{
			while((input = stdIn.readLine()) != null){
				sendMessage(input);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}