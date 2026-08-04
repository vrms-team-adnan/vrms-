package com.vrms.persistence;

import com.vrms.domain.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * File-based implementation of VehicleRepository. Stores vehicles in a
 * plain CSV file on disk, so data survives between program runs. This
 * demonstrates that the service layer works with any VehicleRepository
 * implementation without needing changes, thanks to the Repository
 * pattern.
 */
public class FileVehicleRepository implements VehicleRepository {

    private final String filePath;

    public FileVehicleRepository(String filePath) {
        this.filePath = filePath;
        ensureFileExists();
    }

    private void ensureFileExists() {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Could not create vehicle data file: " + filePath, e);
            }
        }
    }

   
    public void save(Vehicle vehicle) {
        String line = toCsvLine(vehicle);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            throw new RuntimeException("Could not save vehicle to file", e);
        }
    }

    @Override
    public List<Vehicle> findAll() {
        List<Vehicle> vehicles = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines) {
                if (!line.isBlank()) {
                    vehicles.add(fromCsvLine(line));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read vehicles from file", e);
        }
        return vehicles;
    }

    private String toCsvLine(Vehicle vehicle) {
        String type = vehicle.getClass().getSimpleName();
        String extra = "";

        if (vehicle instanceof Truck) {
            extra = String.valueOf(((Truck) vehicle).requiresSpecialLicense());
        } else if (vehicle instanceof ElectricVehicle) {
            extra = String.valueOf(((ElectricVehicle) vehicle).getBatteryLevel());
        }

        return String.join(",",
                vehicle.getId(),
                vehicle.getName(),
                type,
                vehicle.getStatus().name(),
                extra
        );
    }

    private Vehicle fromCsvLine(String line) {
        String[] parts = line.split(",", -1);
        String id = parts[0];
        String name = parts[1];
        String type = parts[2];
        VehicleStatus status = VehicleStatus.valueOf(parts[3]);
        String extra = parts.length > 4 ? parts[4] : "";

        switch (type) {
            case "Car":
                return new Car(id, name, status);
            case "Van":
                return new Van(id, name, status);
            case "Motorcycle":
                return new Motorcycle(id, name, status);
            case "Truck":
                return new Truck(id, name, status, Boolean.parseBoolean(extra));
            case "ElectricVehicle":
                return new ElectricVehicle(id, name, status, Integer.parseInt(extra));
            default:
                throw new IllegalArgumentException("Unknown vehicle type: " + type);
        }
    }
}