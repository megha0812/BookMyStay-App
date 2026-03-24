//version 4.0
//usecase 4:  Room Search & Availability Check
import java.util.*;

class SearchService {

    public void searchAvailableRooms(RoomInventory inventory, List<Room> rooms) {

        System.out.println("\n=== Available Rooms ===");

        for (Room room : rooms) {
            int available = inventory.getAvailability(room.type);

            if (available > 0) {
                room.displayDetails();
                System.out.println("Available: " + available);
                System.out.println("-------------------");
            }
        }
    }
}

public class UseCase4App {
    public static void main(String[] args) {

        RoomInventory inventory = new RoomInventory();

        List<Room> rooms = new ArrayList<>();
        rooms.add(new SingleRoom());
        rooms.add(new DoubleRoom());
        rooms.add(new SuiteRoom());

        SearchService searchService = new SearchService();

        searchService.searchAvailableRooms(inventory, rooms);
    }
}