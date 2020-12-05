import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Interface extends Remote {
   public byte[] downloadFile(String fileName) throws
   RemoteException;
}
