package edu.gatech.donationtracker.model;

import java.lang.Class;

public class Admin extends User {

    /** constructor */
    public Admin(String email, String username, String password) {
        super(email, username, password);
    }

    /**
     * ***Extra credit!
     * Lock an account
     *
     * @param user The user who would be locked
     */
    public void LockAccount(User user) {
        user.setIsLocked(true);
    }

    /**
     * ***Extra credit!
     * Unlock an account
     *
     * @param user The user who would be unlocked
     */
    public void UnlockAccount(User user) {
        user.setIsLocked(false);
    }

    /**
     * add an account
     * @param model the model of all accounts
     * @param user The user who would be added to database
     */
    public void AddUser(Model model, User user) {
        model.add(user);
    }

    /**
     * remove an account
     * @param model the model of all accounts
     * @param user The user who would be removed from database
     */
    public void RemoveUser(Model model, User user) {
        model.remove(user.get_email());
    }

    /**
     * ***Extra credit!
     * Change account type
     *
     * @param model the model of all accounts
     * @param user The user who would be locked
     * @param type the new class type of the user
     * @return true if user found and class type changed successfully, false otherwise
     */
    public boolean ChangeAccountType(Model model, User user, Class<? extends User> type)
            throws InstantiationException, IllegalAccessException {
        if (model.remove(user.get_email())) {
            User newAcc = type.newInstance();
            newAcc.set_name(user.get_name());
            newAcc.set_email(user.get_email());
            newAcc.set_id(user.get_id());
            newAcc.set_password(user.get_password());
            model.add(type.cast(newAcc));
            return true;
        } else {
            return false;
        }
    }
}
