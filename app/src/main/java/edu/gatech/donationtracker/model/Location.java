package edu.gatech.donationtracker.model;

import java.util.List;

public class Location {

    /** this location's address*/
    private String _address;

    /** the inventory of this location */
    private List<Item> _inventory;

    /** constructor */
    public Location(String address, List<Item> inventory) {
        this._address = address;
        this._inventory = inventory;
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

    /** getter */
    public List<Item> get_inventory() {
        return _inventory;
    }

    /** toString of this object */
    @Override
    public String toString() {
        return _address;
    }
}
