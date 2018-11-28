package edu.gatech.donationtracker;

import org.junit.Before;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.assumeTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import edu.gatech.donationtracker.model.Item;
import edu.gatech.donationtracker.model.Locations;
import edu.gatech.donationtracker.model.Model;

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
    public int userType;

    public User userToRemove1;
    public User userToRemove2;
    private Model model;
    private List<Item> filterList;
    private List<Item> expectedList;
    private Item[] items;

    @Before
    public void setup() {
        user = new User();
        admin = new Admin("E","N", "P");
        model = Model.getInstance();


        //testRemoveUser setup
        userToRemove1 = new User("userToRemove1", "userToRemove1", "userToRemove1");
        userToRemove2 = new User("userToRemove1", "userToRemove1", "userToRemove1");
        model = Model.getInstance();
        model.addAccount(userToRemove1);
        model.addAccount(userToRemove2);

        filterList = new ArrayList<>();

        items = new Item[]{new Item("uri", "Joe", "1", "people", 1, new Locations()),
                new Item("uri", "Mai", "1", "student", 1, new Locations()),
                new Item("uri", "Yong", "1", "teacher", 1, new Locations()),
                new Item("uri", "Jiajie", "1", "worker", 1, new Locations()),
                new Item("uri", "uyng", "1", "athlete", 1, new Locations()),
                new Item("uri", "Tim", "1", "professor", 1, new Locations())};
        for (Item i : items) {
            filterList.add(i);
        }
        model = Model.getInstance();
        //key is j
        expectedList = new ArrayList<>();
        expectedList.add(new Item("uri", "Joe", "1", "people", 1, new Locations()));
        expectedList.add(new Item("uri", "Jiajie", "1", "people", 1, new Locations()));

    }

    @Test
    public void testFilterCategory() {
        model.filterCategory(filterList, "er");
        assertTrue(model.getFilteredItems().contains(items[2]));
        assertTrue(model.getFilteredItems().contains(items[3]));
        assertEquals(model.getFilteredItems().size(), 2);
        model.filterCategory(filterList, "xx");
        assertEquals(model.getFilteredItems().size(), 0);
    }

    @Test
    public void testRemoveUser() {
        model.removeAccount(userToRemove1);
        assertFalse(model.getAccounts().contains(userToRemove1));
        assertTrue(model.getAccounts().contains(userToRemove2));
    }

    @Test
    public void testUserType() {
        user = new User("mai@mai.com", "mai", "mai");
        model.setCurrentUser(user);
        assertEquals("User", model.getCurrentUserTypeAsString());
        assertEquals(0, model.getCurrentUserType());
    }

    @Test
    public void testFilterName() {
        model.filterName(filterList, "j");
        assertTrue(model.getFilteredItems().contains(items[0]));
        assertTrue(model.getFilteredItems().contains(items[3]));
        assertEquals(model.getFilteredItems().size(), 2);
        model.filterName(filterList, "x");
        assertFalse(model.getFilteredItems().contains(items[0]));
        assertEquals(model.getFilteredItems().size(), 0);
    }


}
