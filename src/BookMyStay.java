//version 7.0
//usecase 7:  Add-On Service Selection
import java.util.*;

class AddOnService {
    String name;
    int cost;

    public AddOnService(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }
}

class AddOnServiceManager {
    private Map<String, List<AddOnService>> serviceMap = new HashMap<>();

    public void addService(String reservationId, AddOnService service) {
        serviceMap.putIfAbsent(reservationId, new ArrayList<>());
        serviceMap.get(reservationId).add(service);

        System.out.println("Added service: " + service.name);
    }

    public int calculateTotal(String reservationId) {
        int total = 0;
        List<AddOnService> services = serviceMap.get(reservationId);

        if (services != null) {
            for (AddOnService s : services) {
                total += s.cost;
            }
        }
        return total;
    }

    public void showServices(String reservationId) {
        List<AddOnService> services = serviceMap.get(reservationId);

        System.out.println("\nServices for " + reservationId + ":");
        if (services != null) {
            for (AddOnService s : services) {
                System.out.println(s.name + " - " + s.cost);
            }
        }
    }
}

public class UseCase7 {
    public static void main(String[] args) {
        AddOnServiceManager manager = new AddOnServiceManager();

        String reservationId = "R101";

        manager.addService(reservationId, new AddOnService("Breakfast", 500));
        manager.addService(reservationId, new AddOnService("Spa", 1500));

        manager.showServices(reservationId);

        System.out.println("Total Add-on Cost: " +
                manager.calculateTotal(reservationId));
    }
}