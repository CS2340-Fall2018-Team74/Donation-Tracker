package edu.gatech.donationtracker.model;


/**
 * Item that is being stored in inventories
 */
public class Item implements Comparable<Item> {

    /** this Item's name */
    private String name;

    /** this Item's id */
    private int id;

    /** this Item's quantity in inventory */
    private int quantity;


    /** constructor with no quantity specified*/
    public Item(String name, int id) {
        this.name = name;
        this.id = id;
        this.quantity = 0;
    }
    /** constructor with quantity specified*/
    public Item(String name, int id, int quantity) {
        this(name, id);
        this.quantity = quantity;
    }

    /** compareTo method */
    public int compareTo (Item item) {
        return this.id - item.id;
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
    public int get_quantity() {
        return quantity;
    }

    /** toString of this object */
    @Override
    public String toString() {
        return name + " id: " + id + " x" + quantity;
    }
}
