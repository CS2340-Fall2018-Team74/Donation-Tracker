package edu.gatech.donationtracker.model;

import java.util.ArrayList;

public class Model {
    private ArrayList<User> accounts;

    public Model() {
        accounts = new ArrayList<>();
        loadDefault(accounts);
    }

    private void loadDefault(ArrayList<User> accounts) {
        accounts.add(new User("admin", "pass", "email"));
    }

    public boolean add(User account) {
        if (!accounts.contains(account)) {
            accounts.add(account);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<User> getAccounts() {
        return accounts;
    }
}
