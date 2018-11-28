package edu.gatech.donationtracker;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


import java.util.*;

import edu.gatech.donationtracker.model.Admin;
import edu.gatech.donationtracker.model.LocationEmployee;
import edu.gatech.donationtracker.model.Manager;
import edu.gatech.donationtracker.model.Model;
import edu.gatech.donationtracker.model.User;

import static org.junit.Assert.*;
public class TQtest {
    private User user;
    private User admin;
    private User LM;
    private User manager;
    public static final int  TIMEOUT = 200;

    @Before
    public void setup() {
        user = new User("","","");
        admin = new Admin("","","");
        LM = new LocationEmployee("","","",null);
        manager = new Manager("","","");
    }

    @Test(timeout = TIMEOUT)
    public void testGetUserType() {
        Model.getInstance().setCurrentUser(user);
        int i = Model.getInstance().getCurrentUserType();
        assertEquals(0,i);
    }
    @Test(timeout = TIMEOUT)
    public void testGetLocationEmpolyeeType() {
        Model.getInstance().setCurrentUser(LM);
        int i = Model.getInstance().getCurrentUserType();
        assertEquals(1,i);
    }
    @Test(timeout = TIMEOUT)
    public void testGetManagerType() {
        Model.getInstance().setCurrentUser(manager);
        int i = Model.getInstance().getCurrentUserType();
        assertEquals(2,i);
    }
    @Test(timeout = TIMEOUT)
    public void testGetAdminType() {
        Model.getInstance().setCurrentUser(admin);
        int i = Model.getInstance().getCurrentUserType();
        assertEquals(3, i);
    }
    @Test(timeout = TIMEOUT)
    public void testNullType() {
        Model.getInstance().setCurrentUser(null);
        int i = Model.getInstance().getCurrentUserType();
        assertEquals(-1, i);
    }
}
