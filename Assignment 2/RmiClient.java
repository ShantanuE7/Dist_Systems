import java.rmi.Naming;
import java.util.Scanner;

public class RmiClient {
    public static void main(String[] args) {
        try {
            // Set up the scanner to take user inputs
            Scanner scanner = new Scanner(System.in);
            
            // Ask the user for the first string
            System.out.print("Enter the first string: ");
            String str1 = scanner.nextLine();
            
            // Ask the user for the second string
            System.out.print("Enter the second string: ");
            String str2 = scanner.nextLine();
            
            // Look up the remote object from the RMI registry
            StringConcat stub = (StringConcat) Naming.lookup("//localhost/StringConcatService");
            
            // Call the remote method to concatenate strings
            String result = stub.concatStrings(str1, str2);
            
            // Output the concatenated result
            System.out.println("Concatenated result: " + result);
            
            // Close the scanner
            scanner.close();
            
        } catch (Exception e) {
            System.out.println("Client failed: " + e);
        }
    }
}
