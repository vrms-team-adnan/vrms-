package com.vrms.presentation.gui.fx;

import com.vrms.application.AuthService;
import com.vrms.application.RentalService;
import com.vrms.application.VehicleService;
import com.vrms.domain.*;
import com.vrms.persistence.*;
import javafx.application.Application;
import javafx.stage.Stage;

import java.time.LocalDate;

/**
 * Entry point for the JavaFX demo GUI. Wires up the services with
 * in-memory repositories, seeds some sample data, and launches the
 * login screen. This class is for demonstration purposes only and is
 * separate from the core layered architecture.
 */
public class VrmsFxApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Set up repositories
        InMemoryManagerRepository managerRepository = new InMemoryManagerRepository();
        InMemoryVehicleRepository vehicleRepository = new InMemoryVehicleRepository();
        InMemoryRentalRepository rentalRepository = new InMemoryRentalRepository();

        // Seed a manager account
        managerRepository.save(new Manager("admin", "admin123"));

        // Seed a larger, more varied fleet
        vehicleRepository.save(new Car("V1", "Toyota Corolla", VehicleStatus.AVAILABLE));
        vehicleRepository.save(new Car("V2", "Honda Civic", VehicleStatus.AVAILABLE));
        vehicleRepository.save(new Car("V3", "Hyundai Elantra", VehicleStatus.AVAILABLE));

        vehicleRepository.save(new Van("V4", "Ford Transit", VehicleStatus.AVAILABLE));
        vehicleRepository.save(new Van("V5", "Mercedes Sprinter", VehicleStatus.AVAILABLE));

        vehicleRepository.save(new Motorcycle("V6", "Yamaha MT-07", VehicleStatus.AVAILABLE));
        vehicleRepository.save(new Motorcycle("V7", "Harley Davidson", VehicleStatus.AVAILABLE));

        vehicleRepository.save(new Truck("V8", "Volvo FH16", VehicleStatus.AVAILABLE, true));
        vehicleRepository.save(new Truck("V9", "Isuzu NPR", VehicleStatus.AVAILABLE, false));

        vehicleRepository.save(new ElectricVehicle("V10", "Tesla Model 3", VehicleStatus.AVAILABLE, 85));
        vehicleRepository.save(new ElectricVehicle("V11", "Nissan Leaf", VehicleStatus.AVAILABLE, 60));

        // Set up services
        AuthService authService = new AuthService(managerRepository);
        VehicleService vehicleService = new VehicleService(vehicleRepository);
        RentalService rentalService = new RentalService(rentalRepository);

        // Seed some existing rentals so Statistics and Rental History have data to show
        Customer customer1 = new Customer("c1", "Ahmad Ali", 28, false);
        Customer customer2 = new Customer("c2", "Sara Khaled", 22, true);

        Vehicle vehicleForRental1 = vehicleRepository.findAll().get(0); // Toyota Corolla
        Vehicle vehicleForRental2 = vehicleRepository.findAll().get(3); // Ford Transit
        Vehicle vehicleForRental3 = vehicleRepository.findAll().get(8); // Isuzu NPR

        rentalService.rentVehicle("R1", vehicleForRental1,
                LocalDate.of(2026, 8, 1), LocalDate.of(2026, 8, 5), customer1);

        rentalService.rentVehicle("R2", vehicleForRental2,
                LocalDate.of(2026, 8, 3), LocalDate.of(2026, 8, 6), customer2);

        rentalService.rentVehicle("R3", vehicleForRental3,
                LocalDate.of(2026, 8, 2), LocalDate.of(2026, 8, 4), customer1);

        ServiceBundle bundle = new ServiceBundle(
                authService, vehicleService, rentalService,
                vehicleRepository, rentalRepository
        );

        new LoginScreen(bundle, primaryStage).show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}