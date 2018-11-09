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


public class JUnit {

    private static final int TIMEOUT = 200;
    private List<Item> filterList;
    private List<Item> expectedList;
    private Model model;
    private Item[] items;

    @Before
    public void setUp() {
        filterList = new ArrayList<>();

        items = new Item[]{new Item("uri", "Joe", "1", "people", 1, new Locations()),
                new Item("uri", "Mai", "1", "people", 1, new Locations()),
                new Item("uri", "Yong", "1", "people", 1, new Locations()),
                new Item("uri", "Jiajie", "1", "people", 1, new Locations()),
                new Item("uri", "uyng", "1", "people", 1, new Locations()),
                new Item("uri", "Tim", "1", "people", 1, new Locations())};
        for (Item i : items) {
            filterList.add(i);
        }
        model = Model.getInstance();
        //key is j
        expectedList = new ArrayList<>();
        expectedList.add(new Item("uri", "Joe", "1", "people", 1, new Locations()));
        expectedList.add(new Item("uri", "Jiajie", "1", "people", 1, new Locations()));

    }

    @Test(timeout = TIMEOUT)
    public void testFilterName() {
        model.filterName(filterList, "j");
        assertTrue(model.getFilteredItems().contains(items[0]));
        assertTrue(model.getFilteredItems().contains(items[3]));
        assertEquals(model.getFilteredItems().size(), 2);
    }


}
