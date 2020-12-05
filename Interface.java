import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
   public boolean vote(String sin) throws
   RemoteException;
}
