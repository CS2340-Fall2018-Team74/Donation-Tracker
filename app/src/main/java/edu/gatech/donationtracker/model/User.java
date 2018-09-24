package edu.gatech.donationtracker.model;

public class User {

    private String email;
    private String username;
    private String password;
    private boolean isLocked;

    public User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        isLocked = false;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

}
