package edu.gatech.donationtracker.model;

public class LocationEmployee extends User {

    private Location location;

    public LocationEmployee(String email, String username, String password, Location location) {
        super(email, username, password);
        this.location = location;
    }

    public void updateData(Inventory inventory) {
        //implement later
    }
}
