package com.pluralsight;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Vehicle> vehicles = new ArrayList<>();
    static String fileName = "src/main/resources/inventory.csv";

    public static void main(String[] args) {
        loadVehiclesToList();
        boolean exit = false;
        while (!exit) {
            System.out.println("""
                    1 - Find vehicles within a price range
                    2 - Find vehicles by make / model
                    3 - Find vehicles by year range
                    4 - Find vehicles by color
                    5 - Find vehicles by mileage range
                    6 - Find vehicles by type (car, truck, SUV, van)
                    7 - List ALL vehicles
                    8 - Add a vehicle
                    9 - Remove a vehicle
                    99 - Quit""");

            String userInput = scanner.nextLine().trim();
            int choice = Integer.parseInt(userInput);
            switch (choice) {
                case 1:
                    findVehicleByPrice();
                    break;
                case 2:
                    findVehicleByMakeModel();
                    break;
                case 3:
                    findVehicleByYear();
                    break;
                case 4:
                    findVehicleByColor();
                    break;
                case 5:
                    findVehicleByMileage();
                    break;
                case 6:
                    findVehicleByType();
                    break;
                case 7:
                    allVehicles();
                    break;
                case 8:
                    addVehicle();
                    break;
                case 9:
                    removeVehicle();
                    break;
                case 99:
                    exit = true;
                    quit();
                    break;
                default:
                    System.out.println("Wrong choice");
            }
        }
    }

    private static void loadVehiclesToList() {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            bufferedReader.readLine();//skip header
            String fileData;
            while ((fileData = bufferedReader.readLine()) != null) {
                String[] parts = fileData.split("\\|");

                int mileage = Integer.parseInt(parts[0]);
                int year = Integer.parseInt(parts[1]);
                String make = parts[2];
                String model = parts[3];
                String bodyType = parts[4];
                String color = parts[5];
                int vin = Integer.parseInt(parts[6]);
                double price = Double.parseDouble(parts[7]);

                Vehicle vehicle = new Vehicle(mileage, year, make, model, bodyType, color, vin, price);
                vehicles.add(vehicle);

            }

        } catch (IOException e) {
            System.err.println("File Can't be Read");
        }
    }

    private static void removeVehicle() {
        System.out.println("Vehicle removed Successfully!!");
    }

    private static void addVehicle() {
        System.out.println("Enter Vehicle mileage:");
        int mileage = scanner.nextInt();
        System.out.println("Enter Vehicle Year:");
        int year = scanner.nextInt();
        System.out.println("Enter Vehicle Make");
        scanner.nextLine();
        String make = scanner.nextLine();
        System.out.println("Enter Vehicle Model");
        String model = scanner.nextLine();
        System.out.println("Enter Vehicle Body Type:");
        String bodyType = scanner.nextLine();
        System.out.println("Enter Vehicle Color:");
        String color = scanner.nextLine();
        System.out.println("Enter Vehicle Vin:");
        int vin = scanner.nextInt();
        System.out.println("Enter Vehicle Price:");
        double price = scanner.nextDouble();
        scanner.nextLine();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName, true))) {
            Vehicle vehicle = new Vehicle(mileage, year, make, model, bodyType, color, vin, price);
            vehicles.add(vehicle);
            bufferedWriter.newLine();
            bufferedWriter.write(mileage + "|" + year + "|" + make + "|" + model + "|" + bodyType + "|" + color + "|" + vin + "|" + price);

        } catch (IOException e) {
            System.out.println("Can't Write to this File");
        }

        System.out.println("Vehicle Added Successfully!!");
    }

    private static void allVehicles() {
        System.out.println("All Vehicles Available");
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    private static void findVehicleByType() {
    }

    private static void findVehicleByMileage() {
    }

    private static void findVehicleByColor() {
    }

    private static void findVehicleByYear() {
    }

    private static void findVehicleByMakeModel() {
    }

    private static void findVehicleByPrice() {
    }

    public static void quit() {
        System.out.println("GOOD BYE");
    }
}
