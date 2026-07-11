package com.vrms.application;

import com.vrms.domain.Manager;
import com.vrms.persistence.ManagerRepository;
import java.util.Optional;

/**
 * Handles manager login and logout.
 */
public class AuthService {

    private final ManagerRepository managerRepository;
    private boolean loggedIn = false;

    /**
     * Creates a new AuthService.
     *
     * @param   managerRepository   the repository used to look up managers
     */
    public AuthService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    /**
     * Attempts to log in a manager with the given credentials.
     *
     * @param   username   the username to check
     * @param   password   the password to check
     * @return  true if the credentials are valid, false otherwise
     */
    public boolean login(String username, String password) {
        Optional<Manager> manager = managerRepository.findByUsername(username);

        if (manager.isPresent()
                && manager.get().getPassword().equals(password)) {
            loggedIn = true;
            return true;
        } else {
            loggedIn = false;
            return false;
        }
    }

    /**
     * Logs out the currently logged-in manager.
     */
    public void logout() {
        loggedIn = false;
    }

    /**
     * Checks whether a manager is currently logged in.
     *
     * @return  true if logged in, false otherwise
     */
    public boolean isLoggedIn() {
        return loggedIn;
    }
}