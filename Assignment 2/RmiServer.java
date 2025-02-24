import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class RmiServer {
    public static void main(String[] args) {
        try {
            // Create the remote object
            StringConcatImpl obj = new StringConcatImpl();
            
            // Start the RMI registry on port 1099
            LocateRegistry.createRegistry(1099);
            
            // Bind the object in the RMI registry
            Naming.rebind("StringConcatService", obj);
            
            System.out.println("RMI Server ready...");
        } catch (Exception e) {
            System.out.println("Server failed: " + e);
        }
    }
}
