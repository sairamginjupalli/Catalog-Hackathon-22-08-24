class ChargingStation {
    private String name;
    private String location;
    private String chargerType;
    private int availableSlots;
    private int totalSlots;
    private double pricePerKWh;
    private double rating;

    public ChargingStation(String name, String location, String chargerType, int totalSlots, double pricePerKWh, double rating) {
        this.name = name;
        this.location = location;
        this.chargerType = chargerType;
        this.totalSlots = totalSlots;
        this.availableSlots = totalSlots;
        this.pricePerKWh = pricePerKWh;
        this.rating = rating;
    }

    public String getName() { return name; }
    public String getLocation() { return location; }
    public String getChargerType() { return chargerType; }
    public int getAvailableSlots() { return availableSlots; }
    public double getPricePerKWh() { return pricePerKWh; }
    public double getRating() { return rating; }

    public boolean bookSlot() {
        if (availableSlots > 0) {
            availableSlots--;
            return true;
        } else {
            return false;
        }
    }

    public void cancelBooking() {
        if (availableSlots < totalSlots) {
            availableSlots++;
        }
    }
}
