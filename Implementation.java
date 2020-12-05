import java.io.*;
import java.rmi.*;
import java.rmi.server.RemoteServer;
import java.rmi.server.UnicastRemoteObject;
import java.awt.Desktop;
import java.util.ArrayList;

public class Implementation implements Interface {
    private String name;

    public Implementation() throws RemoteException {
        super();
    }

    public boolean vote(String driversID) {
        try {
            String name = "BlockServer";
            // File file = new File(fileName);
            // byte buffer[] = new byte[(int) file.length()];
            // boolean vote =
            // BufferedInputStream input = new BufferedInputStream(new
            // FileInputStream(fileName));
            // input.read(buffer, 0, buffer.length);
            // input.close();

            // Block genesisBlock = new Block(driversID, "0");
            // System.out.println("Hash for block 1 : " + genesisBlock.hash);

            System.out.println("Received: " + driversID);
            return (true);
        } catch (Exception e) {
            System.out.println("Implementation: " + e.getMessage());
            e.printStackTrace();
            return (false);
        }
    }
}
