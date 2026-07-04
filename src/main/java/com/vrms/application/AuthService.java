package com.vrms.application;

import com.vrms.domain.Manager;
import com.vrms.persistence.ManagerRepository;
import java.util.Optional;

public class AuthService {

    private final ManagerRepository managerRepository;
    private boolean loggedIn = false;
    
    public AuthService(ManagerRepository managerRepository){
        this.managerRepository=managerRepository;
    }

    public boolean login(String username, String password) {
    Optional<Manager> manager = managerRepository.findByUsername(username);

    if( manager.isPresent()
            && manager.get().getPassword().equals(password))
    {
    	loggedIn = true;
    	return true;
    } 
    else {
    	loggedIn = false;
    	return false;
    }
     
   }
    public void logout() {
    	loggedIn = false;
  }
    public boolean isLoggedIn() {
    	return loggedIn;
    }
}