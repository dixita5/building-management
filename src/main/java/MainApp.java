import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Building building = new Building(25.0);

        // Initial rooms
        building.addApartment(new Apartment("101", "Dixita"));
        building.addApartment(new Apartment("102", "Srishti"));
        building.addCommonRoom(new CommonRoom("Gym 1", CommonRoomType.GYM));
        building.addCommonRoom(new CommonRoom("Lib 1", CommonRoomType.LIBRARY));

        building.recalculateHVAC();
        building.printStatus();

        building.startTemperatureUpdates();

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Set requested building temperature");
            System.out.println("2. Add Apartment");
            System.out.println("3. Add Common Room");
            System.out.println("4. Print status");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter new requested temperature: ");
                    if (scanner.hasNextDouble()) {
                        double temp = scanner.nextDouble();
                        scanner.nextLine();
                        building.setRequestedTemperature(temp);
                        building.recalculateHVAC();
                    } else {
                        System.out.println("Invalid temperature input.");
                        scanner.nextLine();
                    }
                }
                case 2 -> {
                    System.out.print("Enter Apartment ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Owner's Name: ");
                    String owner = scanner.nextLine();
                    building.addApartment(new Apartment(id, owner));
                    building.recalculateHVAC();
                }
                case 3 -> {
                    System.out.print("Enter Common Room ID: ");
                    String id = scanner.nextLine();
                    System.out.println("Common Room types:");
                    for (CommonRoomType t : CommonRoomType.values()) {
                        System.out.println("- " + t);
                    }
                    System.out.print("Enter type: ");
                    String typeStr = scanner.nextLine().toUpperCase();
                    try {
                        CommonRoomType type = CommonRoomType.valueOf(typeStr);
                        building.addCommonRoom(new CommonRoom(id, type));
                        building.recalculateHVAC();
                    } catch (IllegalArgumentException e) {
                        System.out.println("Invalid common room type.");
                    }
                }
                case 4 -> building.printStatus();
                case 5 -> {
                    building.stopTemperatureUpdates();
                    System.out.println("Exiting.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}