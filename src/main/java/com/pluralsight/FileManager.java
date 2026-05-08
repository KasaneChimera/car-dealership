package com.pluralsight;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    public Dealership getDealership() {

        Dealership dealership = null;

        try {

            BufferedReader br =
                    new BufferedReader(
                            new FileReader("inventory.csv"));

            String firstLine = br.readLine();

            String[] dealershipParts =
                    firstLine.split("\\|");

            String name = dealershipParts[0];
            String address = dealershipParts[1];
            String phone = dealershipParts[2];

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

        } catch (IOException e) {
            System.out.println("Error reading file.");
        }

        return dealership;
    }

    public void saveDealership(Dealership dealership) {

    }
}