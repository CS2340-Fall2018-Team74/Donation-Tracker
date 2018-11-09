package edu.gatech.donationtracker;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.donationtracker.model.Admin;
import edu.gatech.donationtracker.model.Model;
import edu.gatech.donationtracker.model.User;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class JUnit {

    public User user;
    public Admin admin;
    public User userToRemove1;
    public User userToRemove2;
    public Model model;

    @Before
    public void setup() {
        user = new User();
        admin = new Admin("E","N", "P");


        //testRemoveUser setup
        userToRemove1 = new User("userToRemove1", "userToRemove1", "userToRemove1");
        userToRemove2 = new User("userToRemove1", "userToRemove1", "userToRemove1");
        model = Model.getInstance();
        model.addAccount(userToRemove1);
        model.addAccount(userToRemove2);

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
    public void testRemoveUser() {
        model.removeAccount(userToRemove1);
        assertFalse(model.getAccounts().contains(userToRemove1));
        assertTrue(model.getAccounts().contains(userToRemove2));
    }
}
