import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
   public boolean vote(String driversID) throws RemoteException;
}
