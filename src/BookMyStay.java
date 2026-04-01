//version 11.0
//usecase 11:  Concurrent Booking Simulation (Thread Safety)
import java.util.*;

class ConcurrentBooking {
    private Map<String, Integer> inventory = new HashMap<>();

    public ConcurrentBooking() {
        inventory.put("STANDARD", 1);
    }

    public synchronized void book(String user) {
        int available = inventory.get("STANDARD");

        if (available <= 0) {
            System.out.println(user + ": No rooms left");
            return;
        }

        // Critical Section
        inventory.put("STANDARD", available - 1);
        System.out.println(user + " successfully booked");
    }
}

public class UseCase11 {
    public static void main(String[] args) {
        ConcurrentBooking system = new ConcurrentBooking();

        Runnable task = () -> {
            String user = Thread.currentThread().getName();
            system.book(user);
        };

        Thread t1 = new Thread(task, "User-A");
        Thread t2 = new Thread(task, "User-B");

        t1.start();
        t2.start();
    }
}