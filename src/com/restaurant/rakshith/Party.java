package com.restaurant.rakshith;

import java.util.List;

/**
 * Created by Rakshith on 3/17/2017.
 */
public class Party implements Cloneable {

    private String name;
    private int size;
    private boolean beingServed;

    public Party(String name, int size) {
        this.name = name;
        this.size = size;
    }


    public boolean checkRepeatedNames(List<Table> tablesServed, List<Party> waitList, String nameToCheck) {
        for (Table tableItem : tablesServed) {
            if (tableItem.getOccupiedStatus() && tableItem.getParty().getName().compareTo(nameToCheck) == 0) {
                return true;
            }
        }

        for (Party partyItem : waitList) {
            if (partyItem.getName().compareTo(nameToCheck) == 0) {
                return true;
            }
        }

        return false;
    }


    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isBeingServed() {
        return beingServed;
    }

    public void setBeingServed(boolean beingServed) {
        this.beingServed = beingServed;
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

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            Party equalsParty = (Party) obj;

            return this.beingServed == equalsParty.beingServed &&
                    this.size == equalsParty.size &&
                    this.name.compareTo(equalsParty.name) == 0;

        }
        return false;
    }

    @Override
    public Party clone() {
        try {
            Party copy = (Party) super.clone();
            return copy;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
