import java.util.ArrayList;
import java.util.List;

class UserProfile {
    private String username;
    private List<ChargingStation> bookedStations;

    public UserProfile(String username) {
        this.username = username;
        this.bookedStations = new ArrayList<>();
    }

    public String getUsername() { return username; }
    public List<ChargingStation> getBookedStations() { return bookedStations; }

    public void bookStation(ChargingStation station) {
        bookedStations.add(station);
    }

    public void cancelBooking(ChargingStation station) {
        bookedStations.remove(station);
        station.cancelBooking();
    }
}
