//version 12.0
//usecase 12:  Data Persistence & System Recovery
import java.io.*;
import java.util.*;

class HotelState implements Serializable {
    Map<String, Integer> inventory = new HashMap<>();
}

class PersistenceService {
    private static final String FILE = "hotel.dat";

    public static void save(HotelState state) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE))) {
            out.writeObject(state);
            System.out.println("State saved.");
        } catch (IOException e) {
            System.out.println("Save failed.");
        }
    }

    public static HotelState load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE))) {
            return (HotelState) in.readObject();
        } catch (Exception e) {
            System.out.println("No valid saved state. Starting fresh.");
            return new HotelState();
        }
    }
}

public class UseCase12 {
    public static void main(String[] args) {
        // Load state
        HotelState state = PersistenceService.load();

        // Initialize if empty
        state.inventory.putIfAbsent("DELUXE", 2);

        // Simulate booking
        state.inventory.put("DELUXE", state.inventory.get("DELUXE") - 1);
        System.out.println("Booked DELUXE. Remaining: " + state.inventory.get("DELUXE"));

        // Save state
        PersistenceService.save(state);
    }
}