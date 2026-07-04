package com.vrms.application;

import com.vrms.domain.Manager;
import com.vrms.persistence.InMemoryManagerRepository;
import com.vrms.persistence.ManagerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AuthServiceTest {

    private AuthService authService;

    @BeforeEach
    void setUp() {
        ManagerRepository managerRepository = new InMemoryManagerRepository();
        ((InMemoryManagerRepository) managerRepository)
                .save(new Manager("adnan", "1234"));

        authService = new AuthService(managerRepository);
    }

    @Test
    void loginSucceed() {
        boolean result = authService.login("adnan", "1234");

        assertTrue(result, "Login should succeed with correct username and password");
    }

    @Test
    void loginhWrongPassword() {
        boolean result = authService.login("adnan", "wrong-password");

        assertFalse(result, "Login should fail when the password is incorrect");
    }

    @Test
    void loginhWronguserName(){
        boolean result=authService.login("wrong_name","1234");
        assertFalse(result,"Login should fail when the user_name is incorrect");
    }
}
    