import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NodeServer {
    private static ArrayList<String> licenseList;
	private static BlockingQueue<String> queueIn;
	private static BlockingQueue<String> queueOut;
	private static BlockingQueue<String> queueClient;
    public static void main(String argv[]) {			
		queueIn = new LinkedBlockingQueue<>(256);
		queueOut = new LinkedBlockingQueue<>(256);
		queueClient = new LinkedBlockingQueue<>(256);
		try{
		Sender s = new Sender(argv[0], 5000, queueOut);
		Receiver r = new Receiver(5000, queueIn);
		r.join(argv[1]);
		ClientServer c = new ClientServer(argv[2], queueClient);
		Thread t1 = new Thread(s);
		Thread t2 = new Thread(r);
		Thread t3 = new Thread(c);
		t1.start();
		t2.start();
		t3.start();
		}
		catch(IOException e){
			e.printStackTrace();
		}
		while(true){
			while(queueClient.size() == 0);
			try{
				String data = queueClient.take();
				System.out.println(data+" has been modified");
				queueOut.put(data);
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
    } 
}
