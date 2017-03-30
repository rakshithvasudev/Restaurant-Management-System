package com.restaurant.rakshith;

import java.util.List;

/**
 * Created by Rakshith on 3/17/2017.
 * This class is responsible for a parties in the restaurant.
 * Supports cloning and comparing. This class cannot be extended.
 * No two parties can have the same name. If a party arrives
 * having larger size than that of the maximum size of the restaurant,
 * they are unfortunately not served.
 */
public final class Party implements Cloneable, Comparable<Party> {

    private String name;
    private int size;
    private boolean beingServed;

    /**
     * Constructor that creates a party object having specified name and size.
     *
     * @param name required name for the object.
     * @param size required size for the object.
     * @throws IllegalArgumentException if the party name is null or is empty.
     */
    public Party(String name, int size) {
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Please enter correct party name.");
        }
        if (size > 0) {
            this.size = size;
        } else {
            throw new IllegalArgumentException("Please enter correct party size.");
        }
        beingServed = false;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean getBeingServed() {
        return beingServed;
    }

    public void setBeingServed(boolean beingServed) {
        this.beingServed = beingServed;
    }

    /**
     * Checks for any repeated name in the tables currently served, waiting list.
     * returns false if there are no party name duplicates.
     *
     * @param tablesServed an arrayList\<Table\> of all the tables served in the restaurant.
     * @param waitList     an arrayList\<Party\> of all the parties in the waiting List.
     * @param nameToCheck  string name that has to be check for duplications.
     * @return true if there was a duplicate.
     * @pre all the arrayList elements are not null or empty.
     * @post
     */
    public static boolean checkRepeatedNames(List<Table> tablesServed, List<Party> waitList, String nameToCheck) {
        for (Table tableItem : tablesServed) {
            if (tableItem.getOccupiedStatus() &&
                    tableItem.getParty().getName().equals(nameToCheck))
                return true;
        }

        for (Party partyItem : waitList) {
            if (partyItem.getName().equals(nameToCheck))
                return true;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
        int a = 31 * name.hashCode();
        a = ((beingServed) ? 1231 : 1237) * a;
        a = a * 53 * size;
        return a;
    }

    /**
     * State equality check, name is not considered as an attribute to check
     * because no 2 party can be created with the same name.
     *
     * @param obj that is passed to check for equality.
     * @return true if there is an exact match.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            Party equalsParty = (Party) obj;

            return beingServed == equalsParty.beingServed &&
                    size == equalsParty.size;
        }
        return false;
    }

    /**
     * Creates a shallow copy since all the fields are primitives.
     *
     * @return a party object that is state wise equal having different memory references.
     */
    @Override
    public Party clone() {
        try {
            return (Party) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return "Party Name: " + name + " whose size is: " + size +
                (getBeingServed() ? " who is being served." : " who is not served.");
    }

    /**
     * Returns an integer based on the comparision of the parties.
     *
     * @param o other party
     * @return the difference between the party sizes.
     */
    @Override
    public int compareTo(Party o) {
        return size - o.size;
    }
}
