import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class Building {
    private List<Apartment> apartments = new ArrayList<>();
    private List<CommonRoom> commonRooms = new ArrayList<>();
    private double requestedTemperature = 20.0;

    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public Building() {}

    public Building(double requestedTemperature) {
        this.requestedTemperature = requestedTemperature;
    }

    public void addApartment(Apartment apt) {
        apartments.add(apt);
    }

    public void addCommonRoom(CommonRoom room) {
        commonRooms.add(room);
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public List<CommonRoom> getCommonRooms() {
        return commonRooms;
    }

    public double getRequestedTemperature() {
        return requestedTemperature;
    }

    public void setRequestedTemperature(double requestedTemperature) {
        this.requestedTemperature = requestedTemperature;
    }

    public void recalculateHVAC() {
        for (Apartment apt : apartments) {
            updateHVAC(apt);
        }
        for (CommonRoom cr : commonRooms) {
            updateHVAC(cr);
        }
    }

    private void updateHVAC(Room room) {
        double delta = 0.5; // "close enough" threshold
        if (room.temperature < requestedTemperature - delta) {
            room.setHeatingEnabled(true);
            room.setCoolingEnabled(false);
        } else if (room.temperature > requestedTemperature + delta) {
            room.setHeatingEnabled(false);
            room.setCoolingEnabled(true);
        } else {
            room.setHeatingEnabled(false);
            room.setCoolingEnabled(false);
        }
    }

    public void printStatus() {
        System.out.println("Building requested temperature: " + requestedTemperature);
        System.out.println("Apartments:");
        for (Apartment apt : apartments) {
            System.out.println("  " + apt);
        }
        System.out.println("Common Rooms:");
        for (CommonRoom cr : commonRooms) {
            System.out.println("  " + cr);
        }
    }

    public void startTemperatureUpdates() {
        Runnable task = () -> {
            boolean changed = false;
            for (Apartment apt : apartments) {
                changed |= updateRoomTemperature(apt);
            }
            for (CommonRoom room : commonRooms) {
                changed |= updateRoomTemperature(room);
            }
            if (changed) {
                recalculateHVAC();
            }
        };
        scheduler.scheduleAtFixedRate(task, 0, 5, TimeUnit.SECONDS);
    }

    private boolean updateRoomTemperature(Room room) {
        final double delta = 0.5;
        double oldTemp = room.getTemperature();
        if (room.isHeatingEnabled()) {
            room.temperature = Math.min(room.temperature + delta, requestedTemperature);
        } else if (room.isCoolingEnabled()) {
            room.temperature = Math.max(room.temperature - delta, requestedTemperature);
        }
        return oldTemp != room.getTemperature();
    }

    public void stopTemperatureUpdates() {
        scheduler.shutdown();
    }
}