public abstract class Room {
    protected String id;
    protected double temperature;
    protected boolean heatingEnabled;
    protected boolean coolingEnabled;

    public Room(String id) {
        this.id = id;
        this.temperature = 10 + Math.random() * 30; // random 10 to 40
        this.heatingEnabled = false;
        this.coolingEnabled = false;
    }

    public String getId() {
        return id;
    }

    public double getTemperature() {
        return temperature;
    }

    public boolean isHeatingEnabled() {
        return heatingEnabled;
    }

    public boolean isCoolingEnabled() {
        return coolingEnabled;
    }

    public void setHeatingEnabled(boolean heatingEnabled) {
        this.heatingEnabled = heatingEnabled;
    }

    public void setCoolingEnabled(boolean coolingEnabled) {
        this.coolingEnabled = coolingEnabled;
    }

    @Override
    public String toString() {
        return String.format("%s: Temp=%.2f, Heating=%b, Cooling=%b",
                id, temperature, heatingEnabled, coolingEnabled);
    }
}