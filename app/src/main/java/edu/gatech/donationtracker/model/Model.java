package edu.gatech.donationtracker.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

/** firestore database https://console.firebase.google.com/project/donation-tracker-bed83/database/firestore/data~2FLocations~2F4G9dqBJsGOlT4lVlVeld */

public class Model {
    private static  Model _instance = new Model();
    public static Model getInstance() { return _instance; }
    public static FirebaseFirestore db;

    /** holds the list of all locations */
    private ArrayList<Location> locations;

    private ArrayList<User> accounts;

    private Location currentLocation;

    private User currentUser;

    private Model () {
        locations = new ArrayList<>();
        loadLocation();
        accounts = new ArrayList<>();
        loadAccount();

        //default user is null (visitor mode)
        currentUser = null;
        //default location is locations[0] or null if no location in database
        currentLocation = locations.size() == 0 ?
                null : locations.get(0);
    }

    /**
     * load a default account
     *
     * @return true if all items are found in inventory and deleted, false otherwise
     */
    private void loadLocation() {
        db = FirebaseFirestore.getInstance();
        Query query = db.collection("Locations");
        query.
            get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Location location = documentSnapshot.toObject(Location.class);
                                locations.add(location);
                            }
                        }
                    }
                });
    }

    private void loadAccount() {
            User test = new User("test@test.com", "test", "test");
            Admin admin = new Admin("admin@admin.com", "admin", "admin");
            accounts.add(test);
            accounts.add(admin);
    }

    public void addLocation(Location location) {

    }

    public void removeLocation(Location location) {

    }

    public void addAccount(User user) {
        accounts.add(user);
    }

    public void removeAccount(User user) {

    }

    public Location getCurrentLocation() { return currentLocation;}

    public void setCurrentLocation(Location location) { currentLocation = location; }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return int representaion of type, or -1 if currentUser is null(Visitor mode)
     * User = 0
     * LE = 1
     * Manager = 2
     * Admin = 3
     */
    public int getCurrentUserType() {
        if (currentUser != null) {
            if (currentUser instanceof Admin)
                return 3;
            else if (currentUser instanceof Manager)
                return 2;
            else if (currentUser instanceof LocationEmployee)
                return 1;
            else
                return 0;
        }
        return -1;
    }

    /**
     * @return current User type as String
     */
    public String getCurrentUserTypeAsString() {
        switch(getCurrentUserType()) {
            case 0:
                return "User";
            case 1:
                return "Location Employee";
            case 2:
                return "Manager";
            case 3:
                return "Admin";
            default:
                return "Visitor";
        }
    }

    public ArrayList<User> getAccounts() {
        return accounts;
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }
}
