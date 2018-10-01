package edu.gatech.donationtracker.model;

import java.util.List;

public class Location {

    private String _name;

    private String _address;

    private List<Item> _inventory;

    private String _type;

    private float _longitude;

    private float _latitude;

    private String _phone;


    /** constructor */
    public Location(String name, String address, List<Item> inventory, String type, float longitude
            , float latitude, String phone) {
        this._name = name;
        this._address = address;
        this._inventory = inventory;
        this._type = type;
        this._longitude = longitude;
        this._latitude = latitude;
        this._phone = phone;
    }

    /** default constructor */
    public Location() {
        this("Name", "Address", null, "Type", 0, 0
                , "xxx-xxx-xxxx");
    }

    /**
     * add data of this location
     *
     * @param items all items being removed
     */
    public void addData(Item... items) {
        for (Item item : items) {
            if (_inventory.contains(item)) {
                _inventory.get(_inventory.indexOf(item)).addQuantity(item.get_quantity());
            } else {
                _inventory.add(item);
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
            int index = _inventory.indexOf(item);
            if (_inventory.contains(item)) {
                _inventory.get(index).removeQuantity(item.get_quantity());
                if (_inventory.get(index).get_quantity() <= 0) {
                    _inventory.remove(index);
                }
            }
        }
    }

    /** getters and setters */
    public List<Item> getInventory() {
        return _inventory;
    }

    public void setInventory(List<Item> list) {
        this._inventory = list;
    }

    public String getNanme() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }

    public String getAddress() {
        return _address;
    }

    public void setAddress(String address) {
        this._address = address;
    }

    public String getType() {
        return _type;
    }

    public void setType(String type) {
        this._type = type;
    }

    public float getLongitude() {
        return _longitude;
    }

    public void setLongitude(float longitude) {
        this._longitude = longitude;
    }

    public float getLatitude() {
        return _latitude;
    }

    public void setLatitude(float latitude) {
        this._latitude = latitude;
    }


    /** toString of this object */
    @Override
    public String toString() {
        return _name + " " + _address + " " + _type + " " + _phone;
    }
}
