package com.vrms.persistence;

import com.vrms.domain.Rental;
import java.util.ArrayList;
import java.util.List;

/**
 * Simple in-memory implementation of RentalRepository.
 * It just stores rentals in a list while the program is running.
 * Data is lost once the program stops (no real database yet).
 */
public class InMemoryRentalRepository implements RentalRepository {

    private final List<Rental> rentals = new ArrayList<>();

    /**
     * Adds a new rental to the list.
     * @param   rental   the rental to save
     */
    @Override
    public void save(Rental rental) {
        rentals.add(rental);
    }
    @Override
    /**
     * 
     * find the rental by rentId from list
     * @param rentId
     * @return the rental, or null if it is not found
     */
    public Rental findById(String rentId) {
        for (Rental rental : rentals) {
            if (rental.getId().equals(rentId))
            {
            return rental;
            }}
        return null;
    }
}