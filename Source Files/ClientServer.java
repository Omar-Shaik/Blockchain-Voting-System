import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.io.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class ClientServer implements Runnable{
	private String name;
	private static BlockingQueue<String> q;
	
	public ClientServer(String name, BlockingQueue<String> q){
		this.name = name;
		this.q = q;
	}
	
	@Override
	public void run(){
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new SecurityManager());
        }
		try{
            Interface fi = new Implementation();
            Interface stub = (Interface) UnicastRemoteObject.exportObject(fi, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void setLicenseList(String driversID) throws InterruptedException {
        q.put(driversID);
    }
}