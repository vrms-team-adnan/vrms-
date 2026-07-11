package com.vrms.persistence;

import com.vrms.domain.Rental;

/**
 * This interface is responsible for storing rental records.
 * It defines what any storage method (in-memory, database, etc.)
 * must be able to do when it comes to saving a rental.
 */
public interface RentalRepository {

    /**
     * Saves a new rental record.
     *
     * @param   rental   the rental record to be saved
     */
    void save(Rental rental);
    /**
     * find the rental by rentId 
     * @param rentId
     * @return the rental, or null if it is not found
     */
    Rental findById(String rentId);
}