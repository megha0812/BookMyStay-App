//version 9.0
//usecase 9:  Error Handling & Validation
import java.util.*;

class BookingHistory {
    private List<Reservation> history = new ArrayList<>();

    public void add(Reservation r) {
        history.add(r);
    }

    public List<Reservation> getAll() {
        return history;
    }
}

class BookingReportService {
    public void generateReport(List<Reservation> history) {
        System.out.println("\nBooking Report:");

        Map<String, Integer> countByType = new HashMap<>();

        for (Reservation r : history) {
            countByType.put(r.roomType,
                    countByType.getOrDefault(r.roomType, 0) + 1);
        }

        for (String type : countByType.keySet()) {
            System.out.println(type + " -> " + countByType.get(type));
        }
    }
}

public class UseCase8 {
    public static void main(String[] args) {
        BookingHistory history = new BookingHistory();

        history.add(new Reservation("Alice", "Deluxe"));
        history.add(new Reservation("Bob", "Standard"));
        history.add(new Reservation("Charlie", "Deluxe"));

        BookingReportService reportService = new BookingReportService();

        reportService.generateReport(history.getAll());
    }
}