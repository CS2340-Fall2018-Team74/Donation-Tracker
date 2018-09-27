package edu.gatech.donationtracker.model;

import java.util.ArrayList;

public class Model {
    /** all accounts */
    private ArrayList<User> accounts;

    /** constructor */
    public Model() {
        accounts = new ArrayList<>();
        loadDefault();
    }

    /**
     * load a default account
     *
     * @return true if all items are found in inventory and deleted, false otherwise
     */
    private void loadDefault() {
        this.accounts.add(new User("admin", "pass", "email"));
    }

    /**
     * add a account into the system
     *
     * @param account a new user to add into the system
     * @return true if user is not existed in the system, false otherwise
     */
    public boolean add(User account) {
        if (!accounts.contains(account)) {
            accounts.add(account);
            return true;
        } else {
            return false;
        }
    }

    /**
     * remove a account into the system
     *
     * @param email the user registered with this email will be removed
     * @return true if user is existed in the system and removed, false otherwise
     */
    public boolean remove(String email) {
        for (User user : accounts) {
            if (user.getEmail().equals(email)) {
                accounts.remove(user);
                return true;
            }
        }
        return false;
    }

    /**
     * get all accounts
     *
     * @return a Arraylist of accounts
     */
    public ArrayList<User> getAccounts() {
        return accounts;
    }
}
