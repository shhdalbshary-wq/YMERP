package com.ymerp.app;

import org.junit.Before;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.Assert.*;

/**
 * اختبارات نشاط تسجيل الدخول
 * LoginActivityTest
 */
public class LoginActivityTest {

    private PasswordEncoder passwordEncoder;

    @Before
    public void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
    }

    @Test
    public void testPasswordEncryption() {
        String password = "testPassword123";
        String hashedPassword = passwordEncoder.encode(password);

        assertNotNull("Hashed password should not be null", hashedPassword);
        assertTrue("Password encoder should match encrypted password",
                passwordEncoder.matches(password, hashedPassword));
        assertFalse("Password encoder should not match wrong password",
                passwordEncoder.matches("wrongPassword", hashedPassword));
    }

    @Test
    public void testPasswordValidation() {
        String shortPassword = "123";
        String validPassword = "validPassword123";

        assertTrue("Valid password should pass validation",
                validPassword.length() >= 4);
        assertFalse("Short password should fail validation",
                shortPassword.length() >= 4);
    }

    @Test
    public void testUsernameValidation() {
        String shortUsername = "ab";
        String validUsername = "testuser";

        assertTrue("Valid username should pass validation",
                validUsername.length() >= 3);
        assertFalse("Short username should fail validation",
                shortUsername.length() >= 3);
    }
}
