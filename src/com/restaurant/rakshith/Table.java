package com.restaurant.rakshith;

/**
 * Created by Rakshith on 3/17/2017.
 */
public class Table {

    private int id;
    private boolean occupiedStatus;
    private Servers servers;
    private int index;
    private Party party;

    public Table(int id, int index) {
        this.id = id;
        this.index = index;
    }

    Party getParty(){
        return party;
    }

    public boolean getOccupiedStatus() {
        return occupiedStatus;
    }
}
