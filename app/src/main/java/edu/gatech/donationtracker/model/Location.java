package edu.gatech.donationtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;

public class Location implements Parcelable{

    private String key;
    private String name;
    private String streetAddress;
    private List<Item> inventory;
    private String type;
    private String longitude;
    private String latitude;
    private String phone;
    private String zip;
    private String state;
    private String city;
    private String website;
    private DocumentReference reference;

    public int itemId = 0;


    public Location(String key, String name, String streetAddress, String type, String longitude, String latitude, String phone, String zip, String state, String city, String website) {
        this();
        this.name = name;
        this.streetAddress = streetAddress;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
        this.zip = zip;
        this.state = state;
        this.city = city;
        this.website = website;
        this.key = key;
    }

    public Location() {
        inventory = new ArrayList<>();
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    protected Location(Parcel in) {
        key = in.readString();
        name = in.readString();
        streetAddress = in.readString();
        type = in.readString();
        longitude = in.readString();
        latitude = in.readString();
        phone = in.readString();
        city = in.readString();
        state= in.readString();
        website = in.readString();
        zip = in.readString();
    }

    public static final Creator<Location> CREATOR = new Creator<Location>() {
        @Override
        public Location createFromParcel(Parcel in) {
            return new Location(in);
        }

        @Override
        public Location[] newArray(int size) {
            return new Location[size];
        }
    };

    /**
     * add data of this location
     *
     * @param items all items being added
     */
    public void addData(Item... items) {
        for (Item item : items) {
            if (inventory.contains(item)) {
                inventory.get(inventory.indexOf(item)).addQuantity(item.getQuantity());
            } else {
                inventory.add(item);
            }
        }
    }

    /**
     * remove data of this location
     *
     * @param items all items being removed
     */
    public void removeData(Item... items){
        for (Item item : items) {
            if (inventory.contains(item)) {
                inventory.remove(item);
            }
        }
    }

    /** getters and setters */
    public List<Item> getInventory() {
        return inventory;
    }


    @Override
    public String toString() {
        return name + " Type: " + type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public DocumentReference getReference() {
        return reference;
    }

    public void setReference(DocumentReference reference) {
        this.reference = reference;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(streetAddress);
        parcel.writeString(type);
        parcel.writeString(longitude);
        parcel.writeString(latitude);
        parcel.writeString(phone);
        parcel.writeString(city);
        parcel.writeString(state);
        parcel.writeString(website);
        parcel.writeString(key);
        parcel.writeString(zip);
    }
}
