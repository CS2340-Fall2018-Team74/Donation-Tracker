package edu.gatech.donationtracker.model;

public class Location {

    private String address;
    private Inventory inventory;

    public Location(String address, Inventory inventory) {
        this.address = address;
        this.inventory = inventory;
    }
}
