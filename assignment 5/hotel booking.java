import java.rmi.*;
import java.util.Scanner;

// Remote Interface
interface HotelBooking extends Remote {
    String bookRoom(String guestName) throws RemoteException;
    String cancelBooking(String guestName) throws RemoteException;
    String viewBookings() throws RemoteException;
}

// Remote Implementation
class HotelServerImpl extends UnicastRemoteObject implements HotelBooking {
    private Map<String, String> bookings;

    protected HotelServerImpl() throws RemoteException {
        super();
        bookings = new HashMap<>();
    }

    @Override
    public synchronized String bookRoom(String guestName) throws RemoteException {
        if (bookings.containsKey(guestName)) {
            return "Guest already has a booked room.";
        }
        String roomNumber = "Room-" + (bookings.size() + 1);
        bookings.put(guestName, roomNumber);
        return "Room booked successfully: " + roomNumber;
    }

    @Override
    public synchronized String cancelBooking(String guestName) throws RemoteException {
        if (!bookings.containsKey(guestName)) {
            return "No booking found for the guest.";
        }
        bookings.remove(guestName);
        return "Booking cancelled successfully.";
    }

    @Override
    public synchronized String viewBookings() throws RemoteException {
        return bookings.isEmpty() ? "No bookings available." : bookings.toString();
    }

    public static void main(String[] args) {
        try {
            HotelBooking server = new HotelServerImpl();
            Naming.rebind("rmi://localhost/HotelService", server);
            System.out.println("Hotel Booking Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// Client Implementation
class HotelClient {
    public static void main(String[] args) {
        try {
            HotelBooking hotelService = (HotelBooking) Naming.lookup("rmi://localhost/HotelService");
            Scanner scanner = new Scanner(System.in);
            
            while (true) {
                System.out.println("\n1. Book Room\n2. Cancel Booking\n3. View Bookings\n4. Exit");
                System.out.print("Choose an option: ");
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.print("Enter guest name: ");
                        String guestName = scanner.nextLine();
                        System.out.println(hotelService.bookRoom(guestName));
                        break;
                    case 2:
                        System.out.print("Enter guest name to cancel: ");
                        guestName = scanner.nextLine();
                        System.out.println(hotelService.cancelBooking(guestName));
                        break;
                    case 3:
                        System.out.println("Current Bookings: " + hotelService.viewBookings());
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid option. Try again.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
