package edu.gatech.donationtracker.model;

public class Admin extends User {

    public Admin(String email, String username, String password) {
        super(email, username, password);
    }

    public void LockAccount(User user) {
        user.setIsLocked(true);
    }

    public void UnlockAccount(User user) {
        user.setIsLocked(false);
    }

    public void AddUser(User user) {
        //implement later
    }

    public void RemoveUser(User user) {
        //implement later
    }

    public void ChangeAccountType(User user) {
        //implement later
    }

}
