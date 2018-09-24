package edu.gatech.donationtracker.model;

import java.util.LinkedList;
import java.util.List;

public class Inventory {

    private List<Item> inventory;

    public Inventory(List<Item> list) {
        inventory = list;
    }

    public Inventory() {
        inventory = new LinkedList<Item>();
    }
}
