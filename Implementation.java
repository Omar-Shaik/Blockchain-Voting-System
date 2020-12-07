import java.io.*;
import java.rmi.*;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.awt.Desktop;
import java.util.ArrayList;

public class Implementation implements Interface {
    private String name;
    private static ArrayList<String> licenseList = new ArrayList<>();

    public Implementation() throws RemoteException {
        super();
    }

    public boolean vote(String driversID) {
        try {
            // String name = "BlockServer";
            
            // licenseList.add(driversID);
            NodeServer.setLicenseList(driversID);
            
            return (true);
        } catch (Exception e) {
            System.out.println("Implementation: " + e.getMessage());
            e.printStackTrace();
            return (false);
        }
    }
}
