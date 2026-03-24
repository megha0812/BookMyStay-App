//version 6.0
//usecase 6:  Reservation Confirmation & Room Allocation
import java.util.*;

class InventoryService {
    Map<String, Integer> inventory = new HashMap<>();

    public InventoryService() {
        inventory.put("Deluxe", 2);
        inventory.put("Standard", 2);
    }

    public boolean isAvailable(String type) {
        return inventory.getOrDefault(type, 0) > 0;
    }

    public void reduceInventory(String type) {
        inventory.put(type, inventory.get(type) - 1);
    }
}

class BookingService {
    private Set<String> allocatedRooms = new HashSet<>();
    private Map<String, Set<String>> roomMap = new HashMap<>();
    private InventoryService inventory;

    public BookingService(InventoryService inventory) {
        this.inventory = inventory;
    }

    public void processBooking(Queue<Reservation> queue) {
        while (!queue.isEmpty()) {
            Reservation r = queue.poll();

            if (inventory.isAvailable(r.roomType)) {
                String roomId = UUID.randomUUID().toString();

                allocatedRooms.add(roomId);

                roomMap.putIfAbsent(r.roomType, new HashSet<>());
                roomMap.get(r.roomType).add(roomId);

                inventory.reduceInventory(r.roomType);

                System.out.println("Booking Confirmed for " + r.guestName +
                        " RoomID: " + roomId);
            } else {
                System.out.println("No rooms available for " + r.guestName);
            }
        }
    }
}

public class UseCase6 {
    public static void main(String[] args) {
        Queue<Reservation> queue = new LinkedList<>();

        queue.offer(new Reservation("Alice", "Deluxe"));
        queue.offer(new Reservation("Bob", "Deluxe"));
        queue.offer(new Reservation("Charlie", "Deluxe"));

        InventoryService inventory = new InventoryService();
        BookingService service = new BookingService(inventory);

        service.processBooking(queue);
    }
}