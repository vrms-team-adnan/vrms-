package com.vrms.presentation.gui.fx;

import com.vrms.application.AuthService;
import com.vrms.application.RentalService;
import com.vrms.application.VehicleService;
import com.vrms.persistence.RentalRepository;
import com.vrms.persistence.VehicleRepository;

/**
 * Simple holder that bundles the services and repositories needed by the
 * JavaFX GUI screens, so they can be passed around easily between windows.
 * This class is part of the demo GUI only and is not part of the core
 * layered architecture.
 */
public class ServiceBundle {

    private final AuthService authService;
    private final VehicleService vehicleService;
    private final RentalService rentalService;
    private final VehicleRepository vehicleRepository;
    private final RentalRepository rentalRepository;

    public ServiceBundle(AuthService authService,
                          VehicleService vehicleService,
                          RentalService rentalService,
                          VehicleRepository vehicleRepository,
                          RentalRepository rentalRepository) {
        this.authService = authService;
        this.vehicleService = vehicleService;
        this.rentalService = rentalService;
        this.vehicleRepository = vehicleRepository;
        this.rentalRepository = rentalRepository;
    }

    public AuthService getAuthService() {
        return authService;
    }

    public VehicleService getVehicleService() {
        return vehicleService;
    }

    public RentalService getRentalService() {
        return rentalService;
    }

    public VehicleRepository getVehicleRepository() {
        return vehicleRepository;
    }

    public RentalRepository getRentalRepository() {
        return rentalRepository;
    }
}