package edu.gatech.donationtracker.model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/** fireStore database https://console.firebase.google.com/project/donation-tracker-bed83/database/firestore/data~2FLocations~2F4G9dqBJsGOlT4lVlVeld */

public class Model {
    private static  Model instance = new Model();
    private static List<Item> filteredItems;

    public static Model getInstance() { return instance; }
    public FirebaseFirestore db;

    private static List<Locations> locations;
    private List<User> accounts;

    private Locations currentLocation;
    private User currentUser;
    private Item currentItem;

    /** constructor **/
    private Model () {
        locations = new ArrayList<>();
        loadLocation();
        accounts = new ArrayList<>();
        loadAccount();
        filteredItems = new ArrayList<>();

        //default user is null (visitor mode)
        currentUser = null;
        currentLocation = null;
        currentItem = null;

    }

    /**
     * load locations from database
     */
    private void loadLocation() {
        db = FirebaseFirestore.getInstance();
        Query query = db.collection("Locations");
        query.get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot documentSnapshot : task.getResult()) {
                                Locations location = documentSnapshot.toObject(Locations.class);
                                location.setReference(documentSnapshot.getReference());
                                locations.add(location);
                                loadItem(location, documentSnapshot);
                                Log.d("ModelLocation", location.toString());
                            }
                        } else {
                            Log.d("ModelLocation", "Load failed");
                        }
                    }
                });
    }

    /**
     * load Items at each location from database
     */
    private void loadItem(final Locations location, DocumentSnapshot locationSnapshot) {
        locationSnapshot.getReference().collection("Items").
                get().
                addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (DocumentSnapshot ItemSnapshot : task.getResult()) {
                                Item item = ItemSnapshot.toObject(Item.class);
                                item.setLocation(location);
                                item.setReference(ItemSnapshot.getReference());
                                location.addData(item);
                                if (Integer.parseInt(item.getId()) > location.itemId) {
                                    location.itemId = Integer.parseInt(item.getId());
                                }
                                Log.d("ModelItem", item.toString());
                            }
                        } else {
                            Log.d("ModelItem", "Load failed");
                        }
                    }
                });
    }

    /**
     * load accounts from database
     */
    private void loadAccount() {
            User test = new User("test@test.com", "test", "test");
            Admin admin = new Admin("admin@admin.com", "admin", "admin");
            accounts.add(test);
            accounts.add(admin);
    }

    /**
     * push new item to database
     * @param items item that will be uploaded
     */
    public void pushNewItemToDatabase(Item... items) {
        for (Item item : items) {
            Map<String, Object> itemAsMap = new HashMap<>();
            itemAsMap.put("url", item.getUri());
            itemAsMap.put("id", item.getId());
            itemAsMap.put("name", item.getName());
            itemAsMap.put("category", item.getCategory());
            itemAsMap.put("quantity", item.getQuantity());
            Model.getInstance().getCurrentLocation().getReference().collection("Items").add(itemAsMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()) {
                        Model.getInstance().getCurrentItem().setReference(task.getResult());
                    } else {
                        Log.d("ModelAddNew", "failed");
                    }
                }
            });

        }
    }

    /**
     * push a change to item to database
     * @param items items that will be changed and pushed to database
     */
    public void pushEditedItemToDatabase(Item... items) {
        for (Item item : items) {
            Map<String, Object> itemAsMap = new HashMap<>();
            itemAsMap.put("url", item.getUri());
            itemAsMap.put("id", item.getId());
            itemAsMap.put("name", item.getName());
            itemAsMap.put("category", item.getCategory());
            itemAsMap.put("quantity", item.getQuantity());
            item.getReference().set(itemAsMap);
        }
    }

    /**
     * delete an item in database
     * @param items items that will be deleted
     */
    public void deleteItemInDatabase(Item... items) {
        for (Item item : items) {
            item.getReference().delete();
        }
    }

    /**
     * filter the item list with a category keyword
     * @param array array that will be filtered
     * @param keyword keyword that filters array
     */
    public void filterCategory(List<Item> array, final String keyword) {
        filteredItems = array.stream().filter(new Predicate<Item>() {
            @Override
            public boolean test(Item item) {
                return item.getCategory().toLowerCase().contains(keyword.toLowerCase());
            }
        }).collect(Collectors.<Item>toList());
    }

    /**
     * filter the item list with a name keyword
     * @param array array that will be filtered
     * @param keyword keyword that filters array
     */
    public static void filterName(List<Item> array, final String keyword) {
        filteredItems = array.stream().filter(new Predicate<Item>() {
            @Override
            public boolean test(Item item) {
                return item.getName().toLowerCase().contains(keyword.toLowerCase());
            }
        }).collect(Collectors.<Item>toList());
    }

    /**
     * filter the item list with a keyword that filter both name and category
     * @param array array that will be filtered
     * @param keyword keyword that filters array
     */
    public void filterBoth(List<Item> array, final String keyword) {
        filteredItems = array.stream().filter(new Predicate<Item>() {
            @Override
            public boolean test(Item item) {
                return item.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        item.getCategory().toLowerCase().contains(keyword.toLowerCase());
            }
        }).collect(Collectors.<Item>toList());
    }

    /**
     * add a location to model
     * @param location location that will be added
     * @return true is success false otherwise
     */
    public boolean addLocation(Locations location) {
        return locations.add(location);
    }

    /**
     * remove a location to model
     * @param location location that will be removed
     * @return true is success false otherwise
     */
    public boolean removeLocation(Locations location) {
        return locations.add(location);
    }

    /**
     * add a user to model
     * @param user user that will be added
     * @return true is success false otherwise
     */
    public boolean addAccount(User user) {
        return accounts.add(user);
    }

    /**
     * remove a user to model
     * @param user user that will be removed
     * @return true is success false otherwise
     */
    public boolean removeAccount(User user) {
        return accounts.remove(user);
    }

    /** getter/setter */
    public Locations getCurrentLocation() { return currentLocation;}

    /** getter/setter */
    public void setCurrentLocation(Locations location) { currentLocation = location; }

    /** getter/setter */
    public User getCurrentUser() {
        return currentUser;
    }

    /** getter/setter */
    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    /** getter/setter */
    public Item getCurrentItem() {
        return currentItem;
    }

    /** getter/setter */
    public void setCurrentItem(Item currentItem) {
        this.currentItem = currentItem;
    }

    /** getter/setter */
    public List<Item> getFilteredItems() { return filteredItems; }

    /**
     * @return int representation of user type, -1 if currentUser is null(Visitor mode)
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
                return "Locations Employee";
            case 2:
                return "Manager";
            case 3:
                return "Admin";
            default:
                return "Visitor";
        }
    }

    /** getter/setter */
    public List<User> getAccounts() {
        return accounts;
    }

    /** getter/setter */
    public List<Locations> getLocations() {
        return locations;
    }

    /**
     * get longitude and latitude for all locations as a map
     * @return a map contains all locations' longitude and latitude
     */
    public static Map<String, Locations> getLongitudeLatitude() {
        Map<String, Locations> map = new HashMap<String, Locations>();
        for (Locations l : locations) {
            map.put(l.getName(), l);
        }
        return map;
    }

    /**
     * get a list contain all locations' name
     * @return a list of all locations' name
     */
    public List<String> getLocationsAsString() {
        List<String> locationsAsString = new ArrayList<>();
        for (Locations l : locations) {
            locationsAsString.add(l.getName());
        }
        return locationsAsString;
    }

    /**
     * get all items in all locations
     * @return a list of all items
     */
    public List<Item> getAllItems() {
        ArrayList<Item> items = new ArrayList<>();
        for (Locations l : locations) {
            for (Item i : l.getInventory()) {
                items.add(i);
            }
        }
        return items;
    }
}
