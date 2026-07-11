package com.vrms.persistence;
import com.vrms.domain.Manager;
import java.util.Optional;

/**
 * Interface for retrieving managers.
 */
public interface ManagerRepository {

    /**
     * Finds a manager by username.
     *
     * @param   username   the username to search for
     * @return  the manager if found, or empty if not
     */
    Optional<Manager> findByUsername(String username);
}