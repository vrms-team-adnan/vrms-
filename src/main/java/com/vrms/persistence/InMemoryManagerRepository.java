package com.vrms.persistence;

import com.vrms.domain.Manager;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * In-memory implementation of ManagerRepository. Stores managers in a
 * map while the program is running.
 */
public class InMemoryManagerRepository implements ManagerRepository {

    private final Map<String, Manager> managersByUsername = new HashMap<>();

    /**
     * Saves a manager in the repository.
     *
     * @param   manager   the manager to save
     */
    public void save(Manager manager) {
        managersByUsername.put(manager.getUsername(), manager);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Manager> findByUsername(String username) {
        return Optional.ofNullable(managersByUsername.get(username));
    }
}