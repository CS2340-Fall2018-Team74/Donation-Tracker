package edu.gatech.donationtracker.model;

public class Admin extends User {

    /** constructor */
    public Admin(String email, String username, String password) {
        super(email, username, password);
    }

    public Admin() {
        super("", "", "");
    }

    /**
     * lock/unlock an user account
     * @param user user whom will be locked/unlocked
     */
    public void lockAccount(User user) {
        if (user != null) {
            user.setIsLocked(!user.getIsLocked());
        } else {
            throw new IllegalArgumentException("user is null");
        }
    }

    /**
     * add an account
     * @param user The user who would be added to database
     */
    public void AddUser(User user) {
        Model.getInstance().addAccount(user);
    }

    /**
     * remove an account
     * @param user The user who would be removed from database
     */
    public void RemoveUser(User user) {
        Model.getInstance().removeAccount(user);
    }

    /**
     * Change account type
     * @param user The user who would be locked
     * @param type int representation of user type
     * @return true if user found and class type changed successfully, false otherwise
     */
    public boolean ChangeAccountType(User user, int type) {
        if (user != null && type >= 0 && type <= 3 && Model.getInstance().removeAccount(user) ) {
            User newUser;
            switch(type) {
                case 3:
                    newUser = new Admin(user.getEmail(), user.getName(), user.getPassword());
                    break;
                case 2:
                    newUser = new Manager(user.getEmail(), user.getName(), user.getPassword());
                    break;
                case 1:
                    newUser = new LocationEmployee(user.getEmail(), user.getName(), user.getPassword(), null);
                    break;
                default:
                    newUser = new User(user.getEmail(), user.getName(), user.getPassword());
            }
            Model.getInstance().addAccount(newUser);
            return true;
        } else {
            return false;
        }
    }
}
