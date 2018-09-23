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

public class UserAccount implements Parcelable {

    /** a demonstration of using something other than an enum for holding choices */
    public static List<String> legalType = Arrays.asList("Account type", "User", "Location Employee", "Admin");

    /** this User's name */
    private String _name;

    /** this User's password */
    private String _password;

    /** this User's email */
    private String _email;

    /** allow us to assign unique id numbers to each user */
    private static int Next_Id = 0;

    /** this students id number */
    private int _id;


    public UserAccount(String _name, String _password, String _email) {
        this._name = _name;
        this._password = _password;
        this._email = _email;
        _id = UserAccount.Next_Id++;
    }

    /**
     * No param constructor -- DO NOT CALL NORMALLY
     * This constructor only for GUI use in edit/new user dialog
     */
    public UserAccount() {
        this("enter new name" , "enter new password", "enter new email");
    }

    public String get_name() {
        return _name;
    }

    public void set_name(String _name) {
        this._name = _name;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(_name);
        parcel.writeString(_email);
        parcel.writeString(_password);
        parcel.writeInt(_id);
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
    public static final Parcelable.Creator<UserAccount> CREATOR
            = new Parcelable.Creator<UserAccount>() {
        public UserAccount createFromParcel(Parcel in) {
            return new UserAccount();
        }

        public UserAccount[] newArray(int size) {
            return new UserAccount[size];
        }
    };
}
