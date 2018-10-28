package edu.gatech.donationtracker.model;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;
import java.util.List;

/**
 * created by xiaohong chen on 9/23/2018
 *
 * Information Holder - represents a single student in model
 *
 * We are passing this object in a bundle between intents, so we implement
 * the Parcelable interface.
 */

public class User implements Parcelable {

    /** all user types */
    public static List<String> legalType = Arrays.asList("User", "Location Employee", "Manager", "Admin");

    /** this User's name */
    private String name;

    /** this User's password */
    private String password;

    /** this User's email */
    private String email;

    /** allow us to assign unique id numbers to each user */
    private static int Next_Id = 0;

    /** this User's id number */
    private int id;

    /** this User's state of being locked */
    private boolean isLocked;

    /** this User's password counter */
    private int counter;

    /** User constructor*/
    public User(String email, String username, String password) {
        this.name = username;
        this.password = password;
        this.email = email;
        this.isLocked = false;
        this.id = User.Next_Id++;
    }

    /**
     * No param constructor -- DO NOT CALL NORMALLY
     * This constructor only for GUI use in edit/new user dialog
     */
    public User() {
        this("enter new name" , "enter new password", "enter new email");
    }

    /** getters and setters */
    public String getName() {
        return name;
    }

    public void setName(String _name) {
        this.name = _name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String _password) {
        this.password = _password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String _email) {
        this.email = _email;
    }

    public int getId() {
        return id;
    }

    public void setIsLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public void setId(int _id) {
        this.id = _id;
    }

    public boolean getIsLocked() {
        return isLocked;
    }

    public int getCounter() {return counter;}

    public void setCounter(int counter) {this.counter = counter;}

    public void counterIncrement() {counter++;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeInt(id);
    }

    /**
     * Lookup a major based on its code.  Returns the postion of that
     * major in the array
     *
     * @param code the major to find
     *
     * @return the index of the array that corresponds to the submitted major
     */
    public static int findPosition(String code) {
        int i = 0;
        while (i < legalType.size()) {
            if (code.equals(legalType.get(i))) return i;
            ++i;
        }
        return 0;
    }


    /**
     * Should not have to edit this method if the constructor and write method are
     * working correctly.
     */
    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User();
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    /** toString of this object */
    @Override
    public String toString() {
        return name;
    }
}
