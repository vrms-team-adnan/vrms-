package com.vrms.application;

import com.vrms.domain.Manager;
import com.vrms.persistence.ManagerRepository;
import java.util.Optional;

public class AuthService {

    private final ManagerRepository managerRepository;
    
    public AuthService(ManagerRepository managerRepository){
        this.managerRepository=managerRepository;
    }

    public boolean login(String username, String password) {
    Optional<Manager> manager = managerRepository.findByUsername(username);

    return manager.isPresent()
            && manager.get().getPassword().equals(password);
}
    
    
    
    
    
    
    }