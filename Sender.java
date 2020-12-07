import java.net.MulticastSocket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.*;

public class Sender implements Runnable{
	private String address;
	private int port;
	private MulticastSocket s;
	private BlockingQueue<String> queue = new LinkedBlockingQueue<>(256);
	BufferedReader stdIn;
	String input;
	
	public Sender(String address, int port, BlockingQueue<String> queue) throws IOException{
		this.address = address;
		this.port = port;
		this.queue = queue;
		
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
		try {
			while(true){
				while(queue.size() == 0);
				try{
					String data = queue.take();
					sendMessage(data);
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
