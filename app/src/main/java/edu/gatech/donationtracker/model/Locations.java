package edu.gatech.donationtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;
import java.util.List;

public class Locations implements Parcelable{

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

    /** constructor **/
    public Locations(String key, String name, String streetAddress, String type, String longitude, String latitude, String phone, String zip, String state, String city, String website) {
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

    /** default constructor **/
    public Locations() {
        inventory = new ArrayList<>();
    }

    /** getter/setter */
    public String getKey() {
        return key;
    }

    /** getter/setter */
    public void setKey(String key) {
        this.key = key;
    }

    /** constructor for Parcel **/
    protected Locations(Parcel in) {
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

    /** creator for Parcel **/
    public static final Creator<Locations> CREATOR = new Creator<Locations>() {
        @Override
        public Locations createFromParcel(Parcel in) {
            return new Locations(in);
        }

        @Override
        public Locations[] newArray(int size) {
            return new Locations[size];
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
            inventory.remove(item);
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

    /** getter/setter */
    public String getName() {
        return name;
    }

    /** getter/setter */
    public void setName(String name) {
        this.name = name;
    }

    /** getter/setter */
    public String getStreetAddress() {
        return streetAddress;
    }

    /** getter/setter */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /** getter/setter */
    public String getType() {
        return type;
    }

    /** getter/setter */
    public void setType(String type) {
        this.type = type;
    }

    /** getter/setter */
    public String getLongitude() {
        return longitude;
    }

    /** getter/setter */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /** getter/setter */
    public String getLatitude() {
        return latitude;
    }

    /** getter/setter */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /** getter/setter */
    public String getPhone() {
        return phone;
    }

    /** getter/setter */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /** getter/setter */
    public String getZip() {
        return zip;
    }

    /** getter/setter */
    public void setZip(String zip) {
        this.zip = zip;
    }

    /** getter/setter */
    public String getState() {
        return state;
    }

    /** getter/setter */
    public void setState(String state) {
        this.state = state;
    }

    /** getter/setter */
    public String getCity() {
        return city;
    }

    /** getter/setter */
    public void setCity(String city) {
        this.city = city;
    }

    /** getter/setter */
    public String getWebsite() {
        return website;
    }

    /** getter/setter */
    public void setWebsite(String website) {
        this.website = website;
    }

    /** getter/setter */
    public DocumentReference getReference() {
        return reference;
    }

    /** getter/setter */
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
