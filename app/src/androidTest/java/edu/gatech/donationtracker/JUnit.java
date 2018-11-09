package edu.gatech.donationtracker;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.donationtracker.model.Admin;
import edu.gatech.donationtracker.model.Model;
import edu.gatech.donationtracker.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class JUnit {

    public User user;
    public Admin admin;
    Model model;

    @Before
    public void setup() {
        user = new User();
        admin = new Admin("E","N", "P");
        model = Model.getInstance();
    }

    @Test
    public void testLockAccount() {
        assertFalse(user.getIsLocked());
        admin.lockAccount(user);
        assertTrue(user.getIsLocked());
        admin.lockAccount(user);
        assertFalse(user.getIsLocked());
    }

    @Test
    public void testUserType() {
        user = new User("mai@mai.com", "mai", "mai");
        model.setCurrentUser(user);
        assertEquals("User", model.getCurrentUserTypeAsString());
        assertEquals(0, model.getCurrentUserType());
    }
}
