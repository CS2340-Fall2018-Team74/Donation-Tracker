package edu.gatech.donationtracker;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.donationtracker.model.Admin;
import edu.gatech.donationtracker.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class JUnit {

    public User user;
    public Admin admin;

    @Before
    public void setup() {
        user = new User();
        admin = new Admin("E","N", "P");
    }

    @Test
    public void testLockAccount() {
        assertFalse(user.getIsLocked());
        admin.lockAccount(user);
        assertTrue(user.getIsLocked());
        admin.lockAccount(user);
        assertFalse(user.getIsLocked());
    }
}
