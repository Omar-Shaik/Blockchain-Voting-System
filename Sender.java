import java.net.MulticastSocket;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.*;

public class Sender implements Runnable{
	private String address;
	private int port;
	private MulticastSocket s;
	private BlockingQueue<String> queue = new ArrayBlockingQueue<>(256);
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
			// String msg;
			// Thread.sleep(3000);
			// while(!(msg = queue.take()).equals("stop")){
			// 	sendMessage(msg);
			// }
			System.out.println("hey");
		} catch (Exception e) {
			e.printStackTrace();
		}
		// catch(IOException e){
		// 	e.printStackTrace();
		// }
		// catch (InterruptedException eie) {
		// 	eie.printStackTrace();
		// }
	}
}
