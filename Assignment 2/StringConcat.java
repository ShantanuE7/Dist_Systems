import java.rmi.Remote;
import java.rmi.RemoteException;

public interface StringConcat extends Remote {
    String concatStrings(String str1, String str2) throws RemoteException;
}
