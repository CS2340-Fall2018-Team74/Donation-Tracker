package edu.gatech.donationtracker.model;

public class LocationEmployee extends User {

    /** the location this employee belongs to */
    private Location _location;

    /** constructor */
    public LocationEmployee(String email, String username, String password, Location location) {
        super(email, username, password);
        this._location = location;
    }

    /**
     * add data of the location this employee belongs to
     *
     * @param items all items being added
     */
    public void addData(Item... items) {
        _location.removeData(items);
    }

    /**
     * remove data of the location this employee belongs to
     *
     * @param items all items being removed
     */
    public void removeData(Item... items){
        _location.removeData(items);
    }

    /** getters and setters */
    public Location get_location() {
        return _location;
    }
    public void set_location(Location location) {
        this._location = location;
    }
}
