import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.security.NoSuchAlgorithmException;

public class NodeServer {
    private static ArrayList<String> licenseList;
	private static BlockingQueue<Block> queueIn;
	private static BlockingQueue<Block> queueOut;
	private static BlockingQueue<String> queueClient;
	private static Blockchain bc;
    public static void main(String argv[]) {			
		queueIn = new LinkedBlockingQueue<>(256);
		queueOut = new LinkedBlockingQueue<>(256);
		queueClient = new LinkedBlockingQueue<>(256);
		bc = new Blockchain(queueOut);
		try{
		Sender s = new Sender(argv[0], 5000, queueOut);
		Receiver r = new Receiver(5000, queueIn);
		r.join(argv[1]);
		r.join(argv[2]);
		ClientServer c = new ClientServer(argv[3], queueClient);
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
			if(queueIn.size() != 0){
				try{
					Block b = queueIn.take();
					bc.addBlockWithoutNonce(b);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				catch(NoSuchAlgorithmException e){
					e.printStackTrace();
				}
			}
			try{
				String data = queueClient.take();
				bc.createBlock(data);
				System.out.println(bc.getLength());
			}
			catch(InterruptedException e){
				e.printStackTrace();
			}
		}
    } 
}
