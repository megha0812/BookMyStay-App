//version 3.0
//usecase 3:  Centralized Room Inventory (HashMap)
import java.util.HashMap;
import java.util.Map;

class RoomInventory {
    private Map<String, Integer> inventory;

    public RoomInventory() {
        inventory = new HashMap<>();
git add
        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    public void updateAvailability(String roomType, int change) {
        int current = inventory.getOrDefault(roomType, 0);
        int updated = current + change;

        if (updated < 0) {
            System.out.println("⚠ Cannot reduce below 0 for " + roomType);
            return;
        }

        inventory.put(roomType, updated);
    }

    public void displayInventory() {
        System.out.println("\n=== Inventory ===");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + " -> Available: " + entry.getValue());
        }
    }
}

public class UseCase3App {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        inventory.displayInventory();

        // Simulate booking
        inventory.updateAvailability("Single Room", -1);

        // Simulate cancellation
        inventory.updateAvailability("Suite Room", +1);

        inventory.displayInventory();
    }
}