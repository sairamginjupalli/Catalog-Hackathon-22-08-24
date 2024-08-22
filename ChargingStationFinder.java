import java.util.ArrayList;
import java.util.List;

class ChargingStationFinder {
    private List<ChargingStation> stations;

    public ChargingStationFinder(List<ChargingStation> stations) {
        this.stations = stations;
    }

    public List<ChargingStation> findStations(String location, String chargerType, double maxPrice, double minRating) {
        List<ChargingStation> results = new ArrayList<>();
        for (ChargingStation station : stations) {
            if (station.getLocation().equalsIgnoreCase(location) &&
                station.getChargerType().equalsIgnoreCase(chargerType) &&
                station.getPricePerKWh() <= maxPrice &&
                station.getRating() >= minRating) {
                results.add(station);
            }
        }
        return results;
    }

    public List<ChargingStation> getAllStations() {
        return stations;
    }
}
