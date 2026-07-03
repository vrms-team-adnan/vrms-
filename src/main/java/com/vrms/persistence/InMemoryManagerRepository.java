package com.vrms.persistence;

import com.vrms.domain.Manager;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryManagerRepository implements ManagerRepository{
    private final Map<String,Manager>managersByUsername=new HashMap<>();

public void save(Manager manager) {
        managersByUsername.put(manager.getUsername(), manager);
    }
    @Override
      public Optional<Manager> findByUsername(String username) {
        return Optional.ofNullable(managersByUsername.get(username));
    }
}