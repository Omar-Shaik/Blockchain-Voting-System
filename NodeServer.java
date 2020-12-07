import java.io.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class NodeServer {
    // private static String s;
    private static ArrayList<String> licenseList;
    private static boolean poncho;

    public static BlockingQueue<String> queue;

    public static void main(String argv[]) {
        String senderIP = "226.0.0.0", receiverIP = "226.0.0.1";
        licenseList = new ArrayList<>();
        queue = new ArrayBlockingQueue<>(256);

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            System.out.println("Server has started");
            String server_name = "BlockServer";
            Interface fi = new Implementation();
            Interface stub = (Interface) UnicastRemoteObject.exportObject(fi, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(server_name, stub);

            startThread(senderIP, receiverIP, queue);

        } catch (Exception e) {
            System.out.println("NodeServer: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void startThread(String senderIP, String receiverIP, BlockingQueue<String> queue) {
        try {
            Sender s = new Sender(senderIP, 5000, queue);
            Receiver r = new Receiver(5000);

            r.join(receiverIP);

            Thread t1 = new Thread(s);
            Thread t2 = new Thread(r);

            t1.start();
            t2.start();
            // Process process1 = Runtime.getRuntime().exec("java StartMulticast " + senderIP + " " + receiverIP);
            // Process process2 = Runtime.getRuntime().exec("java StartMulticast " + receiverIP + " " + senderIP);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setLicenseList(String driversID) throws InterruptedException {
        licenseList.add(driversID);

        for (String id : licenseList) {
            queue.put(id);
        }
        Thread.sleep(500);
        System.out.println("Queue is: " + queue);
    }
}
