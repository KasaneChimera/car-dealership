package com.pluralsight;

public class Program {

    public static void main(String[] args) {

        FileManager fileManager =
                new FileManager();

        Dealership dealership =
                fileManager.getDealership();

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            System.out.println(vehicle);
        }
    }
}