import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.*;

public class Receiver implements Runnable{
	private int port;
	private MulticastSocket s;
	private String message;
	
	public Receiver(int port) throws IOException{
		this.port = port;
		this.s = new MulticastSocket(port);
	}
	
	public void join(String address) throws IOException{
		this.s.joinGroup(InetAddress.getByName(address));
	}
	
	public String receiveMessage()throws IOException{
		byte[] buffer = new byte[1024];
		DatagramPacket d = new DatagramPacket(buffer, 1024);
		s.receive(d);
		String st = new String(d.getData(), 0, d.getLength());
		return st;
	}
	
	@Override
	public void run(){
		try{
			while((message = receiveMessage())!=null){
				System.out.println(message);
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
