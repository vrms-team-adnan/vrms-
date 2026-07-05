package com.vrms.persistence;

import com.vrms.domain.Rental;

/**
 * This interface is responsible for storing rental records.
 * It defines what any storage method (in-memory, database, etc.)
 * must be able to do when it comes to saving a rental.
 */
public interface RentalRepository {
    void save(Rental rental);
}