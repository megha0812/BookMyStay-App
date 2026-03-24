//version 5.0
//usecase 5:  Booking Request Queue
import java.util.*;
class Reservation {
    String guestName;
    String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }
}

class BookingRequestQueue {
    private Queue<Reservation> queue = new LinkedList<>();

    public void addRequest(Reservation r) {
        queue.offer(r);
        System.out.println("Request added for " + r.guestName);
    }

    public void showQueue() {
        System.out.println("\nBooking Queue:");
        for (Reservation r : queue) {
            System.out.println(r.guestName + " -> " + r.roomType);
        }
    }

    public Queue<Reservation> getQueue() {
        return queue;
    }
}

public class UseCase5 {
    public static void main(String[] args) {
        BookingRequestQueue queue = new BookingRequestQueue();

        queue.addRequest(new Reservation("Alice", "Deluxe"));
        queue.addRequest(new Reservation("Bob", "Standard"));
        queue.addRequest(new Reservation("Charlie", "Suite"));

        queue.showQueue();
    }
}