package com.pluralsight;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private Dealership dealership;

    private Scanner scanner = new Scanner(System.in);

    public UserInterface() {

    }

    private void init() {

        FileManager dfm =
                new FileManager();

        dealership = dfm.getDealership();
    }

    public void display() {

        init();

        int command;

        do {

            System.out.println("\n=== Dealership Menu ===");

            System.out.println("1 - Find vehicles within a price range");
            System.out.println("2 - Find vehicles by make/model");
            System.out.println("3 - Find vehicles by year range");
            System.out.println("4 - Find vehicles by color");
            System.out.println("5 - Find vehicles by mileage range");
            System.out.println("6 - Find vehicles by type");
            System.out.println("7 - List ALL vehicles");
            System.out.println("8 - Add a vehicle");
            System.out.println("9 - Remove a vehicle");
            System.out.println("99 - Quit");

            System.out.print("Enter command: ");

            command = scanner.nextInt();
            scanner.nextLine();

            switch (command) {

                case 1:
                    processGetByPriceRequest();
                    break;

                case 2:
                    processGetByMakeModelRequest();
                    break;

                case 3:
                    processGetByYearRequest();
                    break;

                case 4:
                    processGetByColorRequest();
                    break;

                case 5:
                    processGetByMileageRequest();
                    break;

                case 6:
                    processGetByVehicleTypeRequest();
                    break;

                case 7:
                    processGetAllVehiclesRequest();
                    break;

                case 8:
                    processAddVehicleRequest();
                    break;

                case 9:
                    processRemoveVehicleRequest();
                    break;

                case 99:
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid command");
            }

        } while (command != 99);
    }

    private void displayVehicles(List<Vehicle> vehicles) {

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }
    }

    public void processGetByPriceRequest() {

        System.out.print("Minimum price: ");
        double min = scanner.nextDouble();

        System.out.print("Maximum price: ");
        double max = scanner.nextDouble();

        List<Vehicle> vehicles =
                dealership.getVehiclesByPrice(min, max);

        displayVehicles(vehicles);
    }

    public void processGetByMakeModelRequest() {

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        List<Vehicle> vehicles =
                dealership.getVehiclesByMakeModel(make, model);

        displayVehicles(vehicles);
    }

    public void processGetByYearRequest() {

        System.out.print("Minimum year: ");
        int min = scanner.nextInt();

        System.out.print("Maximum year: ");
        int max = scanner.nextInt();

        List<Vehicle> vehicles =
                dealership.getVehiclesByYear(min, max);

        displayVehicles(vehicles);
    }

    public void processGetByColorRequest() {

        scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        List<Vehicle> vehicles =
                dealership.getVehiclesByColor(color);

        displayVehicles(vehicles);
    }

    public void processGetByMileageRequest() {

        System.out.print("Minimum mileage: ");
        int min = scanner.nextInt();

        System.out.print("Maximum mileage: ");
        int max = scanner.nextInt();

        List<Vehicle> vehicles =
                dealership.getVehiclesByMileage(min, max);

        displayVehicles(vehicles);
    }

    public void processGetByVehicleTypeRequest() {

        scanner.nextLine();

        System.out.print("Vehicle type: ");
        String type = scanner.nextLine();

        List<Vehicle> vehicles =
                dealership.getVehiclesByType(type);

        displayVehicles(vehicles);
    }

    public void processGetAllVehiclesRequest() {

        List<Vehicle> vehicles =
                dealership.getAllVehicles();

        displayVehicles(vehicles);
    }

    public void processAddVehicleRequest() {

        System.out.print("VIN: ");
        int vin = scanner.nextInt();

        System.out.print("Year: ");
        int year = scanner.nextInt();

        scanner.nextLine();

        System.out.print("Make: ");
        String make = scanner.nextLine();

        System.out.print("Model: ");
        String model = scanner.nextLine();

        System.out.print("Vehicle Type: ");
        String type = scanner.nextLine();

        System.out.print("Color: ");
        String color = scanner.nextLine();

        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();

        System.out.print("Price: ");
        double price = scanner.nextDouble();

        Vehicle vehicle =
                new Vehicle(
                        vin,
                        year,
                        make,
                        model,
                        type,
                        color,
                        odometer,
                        price
                );

        dealership.addVehicle(vehicle);

        FileManager dfm =
                new FileManager();

        dfm.saveDealership(dealership);

        System.out.println("Vehicle added.");
    }

    public void processRemoveVehicleRequest() {

        System.out.print("Enter VIN to remove: ");
        int vin = scanner.nextInt();

        Vehicle foundVehicle = null;

        for (Vehicle vehicle : dealership.getAllVehicles()) {

            if (vehicle.getVin() == vin) {

                foundVehicle = vehicle;
                break;
            }
        }

        if (foundVehicle != null) {

            dealership.removeVehicle(foundVehicle);

            FileManager dfm =
                    new FileManager();

            dfm.saveDealership(dealership);

            System.out.println("Vehicle removed.");

        } else {

            System.out.println("Vehicle not found.");
        }
    }
}