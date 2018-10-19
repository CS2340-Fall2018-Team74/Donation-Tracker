package edu.gatech.donationtracker.model;


/**
 * Item that is being stored in inventories
 */
public class Item implements Comparable<Item> {

    /** this Item's name */
    private String name;

    /** this Item's id */
    private String id;

    /** this Item's quantity in inventory */
    private int quantity;

    private String category;

    public Item() {
    }

    /** constructor */
    public Item(String name, String id, int quantity, String category) {
        this.name = name;
        this.id = id;
        this.quantity = 0;
        this.category = category;
    }

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
        return name + " id: " + id + " x" + quantity;
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
}

