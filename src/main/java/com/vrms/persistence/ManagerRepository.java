package com.vrms.persistence;

import com.vrms.domain.Manager;
import java.util.Optional;
public interface ManagerRepository {

    Optional<Manager> findByUsername(String username);
}