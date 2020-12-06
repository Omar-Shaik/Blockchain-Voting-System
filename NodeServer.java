import java.io.*;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

public class NodeServer {
    public static void main(String argv[]) {
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
        } catch (Exception e) {
            System.out.println("NodeServer: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
