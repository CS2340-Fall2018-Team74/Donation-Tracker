package edu.gatech.donationtracker.model;

import java.util.ArrayList;

import edu.gatech.donationtracker.UserAccount;

public class Model {
    private ArrayList<UserAccount> accounts;

    public Model() {
        accounts = new ArrayList<>();
        loadDefault(accounts);
    }

    private void loadDefault(ArrayList<UserAccount> accounts) {
        accounts.add(new UserAccount("admin", "pass", "email"));
    }

    public boolean add(UserAccount account) {
        if (!accounts.contains(account)) {
            accounts.add(account);
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<UserAccount> getAccounts() {
        return accounts;
    }
}
