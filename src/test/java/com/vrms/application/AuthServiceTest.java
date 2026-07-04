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
        extractMethodLoginSuccessfuly();
    }

	private void extractMethodLoginSuccessfuly() {
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
    @Test
    void loginThenLogout() {
    	extractMethodLoginSuccessfuly();
    	logoutSuccessfuly();
    }

	private void logoutSuccessfuly() {
		authService.logout();
    	boolean stayLoginOrNot =authService.isLoggedIn();
    	assertFalse(stayLoginOrNot);
	}
    @Test
    void loginAfterLogout() {
    	extractMethodLoginSuccessfuly();
    	logoutSuccessfuly();
    	extractMethodLoginSuccessfuly();
    }
    @Test
    void loginWrongAfterLogout() {
    	extractMethodLoginSuccessfuly();
    	logoutSuccessfuly();
    	boolean result2 = authService.login("adnan", "wrong-password");
        assertFalse(result2, "Login should fail when the password is incorrect");
    }
}
    