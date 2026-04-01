//version 9.0
//usecase 9:  Error Handling & Validation
import java.util.*;

class InvalidBookingException extends Exception {
    public InvalidBookingException(String message) {
        super(message);
    }
}

class BookingValidator {
    private static final Set<String> VALID_ROOMS = Set.of("STANDARD", "DELUXE");

    public static void validate(String roomType, int available) throws InvalidBookingException {
        if (!VALID_ROOMS.contains(roomType)) {
            throw new InvalidBookingException("Invalid room type: " + roomType);
        }
        if (available <= 0) {
            throw new InvalidBookingException("No rooms available for: " + roomType);
        }
    }
}

public class UseCase9 {
    static Map<String, Integer> inventory = new HashMap<>();

    public static void main(String[] args) {
        inventory.put("STANDARD", 1);

        try {
            book("STANDARD");
            book("SUITE"); // invalid case
        } catch (InvalidBookingException e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    static void book(String type) throws InvalidBookingException {
        int available = inventory.getOrDefault(type, 0);

        BookingValidator.validate(type, available); // Fail-fast

        inventory.put(type, available - 1);
        System.out.println("Booking successful for " + type);
    }
}