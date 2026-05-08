package com.pluralsight;
import java.io.*;
import java.util.List;

public class FileManager {

    public Dealership getDealership() {

        Dealership dealership = null;

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader("inventory.csv"));

            String firstLine = br.readLine();

            String[] dealershipInfo =
                    firstLine.split("\\|");

            String name = dealershipInfo[0];
            String address = dealershipInfo[1];
            String phone = dealershipInfo[2];

            dealership =
                    new Dealership(name, address, phone);

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split("\\|");

                int vin = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String vehicleType = parts[4];
                String color = parts[5];
                int odometer = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle vehicle =
                        new Vehicle(
                                vin,
                                year,
                                make,
                                model,
                                vehicleType,
                                color,
                                odometer,
                                price
                        );

                dealership.addVehicle(vehicle);
            }

            br.close();

        } catch (Exception e) {

            System.out.println("Error loading inventory.");
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {

        try {

            PrintWriter pw =
                    new PrintWriter("inventory.csv");

            pw.println(
                    dealership.getName() + "|" +
                            dealership.getAddress() + "|" +
                            dealership.getPhone()
            );

            List<Vehicle> vehicles =
                    dealership.getAllVehicles();

            for (Vehicle vehicle : vehicles) {

                pw.println(
                        vehicle.getVin() + "|" +
                                vehicle.getYear() + "|" +
                                vehicle.getMake() + "|" +
                                vehicle.getModel() + "|" +
                                vehicle.getVehicleType() + "|" +
                                vehicle.getColor() + "|" +
                                vehicle.getOdometer() + "|" +
                                vehicle.getPrice()
                );
            }

            pw.close();

        } catch (Exception e) {

            System.out.println("Error saving inventory.");
        }
    }
}