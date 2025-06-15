public class Apartment extends Room {
    private String ownerName;

    public Apartment(String id, String ownerName) {
        super(id);
        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public String toString() {
        return String.format("Apartment %s (Owner: %s), Temp=%.2f, Heating=%b, Cooling=%b",
                id, ownerName, temperature, heatingEnabled, coolingEnabled);
    }
}