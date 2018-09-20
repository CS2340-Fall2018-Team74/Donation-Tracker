package edu.gatech.donationtracker;


public class UserAccount {
    private String username;
    private String password;
    private String email;
    private static int idNext = 0;
    private int id;

    public UserAccount(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        id = idNext++;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return username;
    }
}
