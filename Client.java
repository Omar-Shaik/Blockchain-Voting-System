import java.io.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Client {

    public static void main(String argv[]) {
        // Console is used for user input
        Console console = System.console();

        String machine_name = argv[0];
		if (argv.length != 1) {
            System.out.println("Usage: java FileClient <machine_name>");
            System.exit(0);
        }
        
        try {
            String name = "BlockServer";
            Registry registry = LocateRegistry.getRegistry(machine_name);
            Interface fi = (Interface) registry.lookup(name);
            String driversID;

            driversID = console.readLine("Driver's License #: ");
            
            boolean voted = fi.vote(driversID);

            System.out.println(voted);
        } catch (Exception e) {
            System.out.println("Client err: " + e.getMessage());
            // e.printStackTrace();
        }
    }

}
