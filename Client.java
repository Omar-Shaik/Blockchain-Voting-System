import java.io.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;

public class Client {
    private static String fromKiosk;
    private static Interface fi;

    public static void main(String argv[]) {

        String machine_name = argv[0];
		if (argv.length != 2) {
            System.out.println("Usage: java FileClient <machine_name>");
            System.exit(0);
        }
        
        try {
            Registry registry = LocateRegistry.getRegistry(machine_name);
            fi = (Interface) registry.lookup(argv[1]);            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void setUIInfo(String licenseCandidate) throws Exception {
        fromKiosk = licenseCandidate;
        boolean voted = fi.vote(fromKiosk);

        System.out.println(voted);
    }
}
