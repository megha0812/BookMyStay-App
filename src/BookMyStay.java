//version 10.0
//usecase 10:  Booking Cancellation & Inventory Rollback
import java.util.*;

class BookingService {
    private Map<String, String> bookings = new HashMap<>();
    private Map<String, Integer> inventory = new HashMap<>();
    private Stack<String> rollbackStack = new Stack<>();

    public BookingService() {
        inventory.put("DELUXE", 1);
    }

    public void book(String user, String roomType) {
        if (inventory.getOrDefault(roomType, 0) <= 0) {
            System.out.println("No rooms available");
            return;
        }

        String roomId = roomType + "-101";
        bookings.put(user, roomId);
        rollbackStack.push(roomId);

        inventory.put(roomType, inventory.get(roomType) - 1);
        System.out.println("Booked: " + roomId);
    }

    public void cancel(String user) {
        if (!bookings.containsKey(user)) {
            System.out.println("Invalid cancellation request");
            return;
        }

        String roomId = bookings.remove(user);
        rollbackStack.push(roomId);

        String type = roomId.split("-")[0];
        inventory.put(type, inventory.get(type) + 1);

        System.out.println("Cancelled: " + roomId);
    }
}

public class UseCase10 {
    public static void main(String[] args) {
        BookingService service = new BookingService();

        service.book("User1", "DELUXE");
        service.cancel("User1");
        service.cancel("User2"); // invalid
    }
}