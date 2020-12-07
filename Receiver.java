import java.net.MulticastSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.*;

public class Receiver implements Runnable{
	private int port;
	private MulticastSocket s;
	private String message;
	// private BlockingQueue<String> queue = new ArrayBlockingQueue<>(3);
	
	public Receiver(int port) throws IOException{
		this.port = port;
		// this.queue = queue;
		
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
	
	/**public static void main(String args[]) throws IOException{
		Receiver r = new Receiver(5000);
		r.join("226.4.5.6");
		String message;
		while((message = r.receiveMessage()) != null){
			System.out.println(message);
		}			
	}**/
}
