import java.util.*;

public class EVChargingApp {
    private static Scanner scanner = new Scanner(System.in);
    private static List<ChargingStation> stations = new ArrayList<>();
    private static List<UserProfile> users = new ArrayList<>();

    public static void main(String[] args) {
        populateStations();
        System.out.println("Welcome to the EV Charging Station Finder and Slot Booking System!");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Search Charging Stations");
            System.out.println("2. Book a Charging Slot");
            System.out.println("3. Cancel a Booking");
            System.out.println("4. View All Charging Stations");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    searchStations();
                    break;
                case 2:
                    bookSlot();
                    break;
                case 3:
                    cancelBooking();
                    break;
                case 4:
                    viewAllStations();
                    break;
                case 5:
                    System.out.println("Exiting the system. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void populateStations() {
        stations.add(new ChargingStation("Station 1", "New York", "Type 1", 5, 0.20, 4.5));
        stations.add(new ChargingStation("Station 2", "Los Angeles", "Type 2", 3, 0.25, 4.2));
        stations.add(new ChargingStation("Station 3", "San Francisco", "Type 1", 2, 0.22, 4.8));
    }

    private static void searchStations() {
        System.out.print("Enter location: ");
        String location = scanner.nextLine();
        System.out.print("Enter charger type: ");
        String chargerType = scanner.nextLine();
        System.out.print("Enter maximum price per kWh: ");
        double maxPrice = scanner.nextDouble();
        System.out.print("Enter minimum station rating: ");
        double minRating = scanner.nextDouble();

        ChargingStationFinder finder = new ChargingStationFinder(stations);
        List<ChargingStation> availableStations = finder.findStations(location, chargerType, maxPrice, minRating);

        if (availableStations.isEmpty()) {
            System.out.println("No stations found with the given criteria.");
        } else {
            System.out.println("Available stations:");
            for (ChargingStation station : availableStations) {
                System.out.println(station.getName() + " - Location: " + station.getLocation() + ", Available Slots: " + station.getAvailableSlots() +
                                   ", Price: $" + station.getPricePerKWh() + "/kWh, Rating: " + station.getRating());
            }
        }
    }

    private static void bookSlot() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        UserProfile user = getUser(username);

        System.out.print("Enter the name of the station to book: ");
        String stationName = scanner.nextLine();
        ChargingStation station = getStationByName(stationName);

        if (station != null && station.bookSlot()) {
            user.bookStation(station);
            System.out.println("Slot booked successfully at " + station.getName());
        } else {
            System.out.println("Booking failed. No slots available or station not found.");
        }
    }

    private static void cancelBooking() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        UserProfile user = getUser(username);

        System.out.print("Enter the name of the station to cancel booking: ");
        String stationName = scanner.nextLine();
        ChargingStation station = getStationByName(stationName);

        if (station != null && user.getBookedStations().contains(station)) {
            user.cancelBooking(station);
            System.out.println("Booking cancelled successfully for " + station.getName());
        } else {
            System.out.println("Cancellation failed. No booking found for the station.");
        }
    }

    private static void viewAllStations() {
        ChargingStationFinder finder = new ChargingStationFinder(stations);
        List<ChargingStation> allStations = finder.getAllStations();

        System.out.println("All Charging Stations:");
        for (ChargingStation station : allStations) {
            System.out.println(station.getName() + " - Location: " + station.getLocation() + ", Available Slots: " + station.getAvailableSlots() +
                               ", Price: $" + station.getPricePerKWh() + "/kWh, Rating: " + station.getRating());
        }
    }

    private static UserProfile getUser(String username) {
        for (UserProfile user : users) {
            if (user.getUsername().equalsIgnoreCase(username)) {
                return user;
            }
        }
        UserProfile newUser = new UserProfile(username);
        users.add(newUser);
        return newUser;
    }

    private static ChargingStation getStationByName(String name) {
        for (ChargingStation station : stations) {
            if (station.getName().equalsIgnoreCase(name)) {
                return station;
            }
        }
        return null;
    }
}
