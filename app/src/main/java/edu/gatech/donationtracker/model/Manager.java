package edu.gatech.donationtracker.model;

public class Manager extends LocationEmployee{

    public Manager(String email, String username, String password) {
        super(email, username, password, null);
    }

    @Override
    public void updateData(Inventory inventory) {
        //implement later
    }

}
