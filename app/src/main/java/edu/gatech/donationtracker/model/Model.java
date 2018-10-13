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

    /** the currently selected location, defaults to first location */
    private Location currentLocation;

    /** Null Object pattern, returned when no location is found */
    private final Location theNullLocation = new Location();

    private Model () {
        locations = new ArrayList<>();
        loadLocation();
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
                                Log.d("model", location.getCity());
                            }
                        }
                    }
                });
    }

    /**
     *
     * @return  the currently selected Location
     */
    public Location getCurrentLocation() { return currentLocation;}

    public void setCurrentLocation(Location location) { currentLocation = location; }
    /**
     * get all accounts
     *
     * @return a Arraylist of Locations
     */
    public ArrayList<Location> getLocations() {
        return locations;
    }
}
