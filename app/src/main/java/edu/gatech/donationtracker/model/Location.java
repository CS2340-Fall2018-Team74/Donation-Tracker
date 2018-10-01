package edu.gatech.donationtracker.model;

import java.util.List;

public class Location {

    private String name;

    private String address;

    private List<Item> inventory;

    private String type;

    private float longitude;

    private float latitude;

    private String phone;


    /** constructor */
    public Location(String name, String address, List<Item> inventory, String type, float longitude
            , float latitude, String phone) {
        this.name = name;
        this.address = address;
        this.inventory = inventory;
        this.type = type;
        this.longitude = longitude;
        this.latitude = latitude;
        this.phone = phone;
    }

    /** default constructor */
    public Location() {
        this("Name", "Address", null, "Type", 0, 0
                , "xxx-xxx-xxxx");
    }

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
            int index = inventory.indexOf(item);
            if (inventory.contains(item)) {
                inventory.get(index).removeQuantity(item.getQuantity());
                if (inventory.get(index).getQuantity() <= 0) {
                    inventory.remove(index);
                }
            }
        }
    }

    /** getters and setters */
    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> list) {
        this.inventory = list;
    }

    public String getNanme() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /** toString of this object */
    @Override
    public String toString() {
        return name + " " + address + " " + type + " " + phone;
    }
}
