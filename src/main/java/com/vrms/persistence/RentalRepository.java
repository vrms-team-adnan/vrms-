package com.vrms.persistence;

import com.vrms.domain.Rental;

import java.util.List;

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
     * @param rentId the id of the rental to search for
     * @return the rental, or null if it is not found
     */
    Rental findById(String rentId);

    /**
     * Finds all rentals made by a specific customer.
     *
     * @param   customerId   the id of the customer to search for
     * @return  a list of rentals belonging to that customer (empty if none found)
     */
    List<Rental> findByCustomerId(String customerId);
}