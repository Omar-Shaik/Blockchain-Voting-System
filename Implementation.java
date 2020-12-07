import java.io.*;
import java.rmi.*;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.awt.Desktop;
import java.util.ArrayList;

public class Implementation implements Interface {
    public Implementation() throws RemoteException {
        super();
    }

    public boolean vote(String driversID) {
        try {
            ClientServer.setLicenseList(driversID);
            return (true);
        } catch (Exception e) {
            System.out.println("Implementation: " + e.getMessage());
            e.printStackTrace();
            return (false);
        }
    }
}
