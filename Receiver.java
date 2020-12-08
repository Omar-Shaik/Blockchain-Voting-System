import java.util.concurrent.BlockingQueue;
import java.net.MulticastSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.io.*;

public class Receiver implements Runnable{
	private int port;
	private MulticastSocket s;
	private String message;
	private BlockingQueue<Block> b;
	
	public Receiver(int port, BlockingQueue<Block> b) throws IOException{
		this.port = port;
		this.s = new MulticastSocket(port);
		this.b = b;
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
				String[] params = message.split("-");
				Block bl = new Block(params[0], params[1], params[2], params[3], Integer.parseInt(params[4]));
				b.put(bl);
				System.out.println(bl.getData());
			}
		}
		catch(IOException e){
			e.printStackTrace();
		}
		catch(InterruptedException e){
			e.printStackTrace();
		}
	}
}
