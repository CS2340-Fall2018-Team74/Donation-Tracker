package edu.gatech.donationtracker.model;


import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.firestore.DocumentReference;

/**
 * Item that is being stored in inventories
 */
public class Item implements Comparable<Item>, Parcelable {

    private String name;
    private String id;
    private String category;
    private int quantity;
    private DocumentReference reference;

    public Item(String name, String id, String category, int quantity) {
        this.name = name;
        this.id = id;
        this.category = category;
        this.quantity = quantity;
    }

    public Item() {
        this("Enter name: ", "Enter id: ", "Enter category: ", 0);
    }

    protected Item(Parcel in) {
        name = in.readString();
        id = in.readString();
        category = in.readString();
        quantity = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    /** compareTo method */
    public int compareTo (Item item) {
        return this.id.compareTo(item.id);
    }

    /**
     * add the quantity of an item that is already in the inventory
     *
     * @param quantity the # of items to be added
     */
    public void addQuantity (int quantity) {
        this.quantity += quantity;
    }

    /**
     * remove the quantity of an item that is already in the inventory
     *
     * @param quantity the # of items to be added
     */
    public void removeQuantity (int quantity) {
        this.quantity -= quantity;
    }

    /** getter */
    public int getQuantity() {
        return quantity;
    }

    /** toString of this object */
    @Override
    public String toString() {
        return category + " - " + " id: " + id + " - " + name + " x" + quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(id);
        dest.writeString(category);
        dest.writeInt(quantity);
    }
}
