package com.restaurant.rakshith;

import java.util.List;

/**
 * Created by Rakshith on 3/17/2017.
 */
public final class Party implements Cloneable, Comparable<Party>{

    private String name;
    private int size;
    private boolean beingServed;

    public Party(String name, int size) {
        if(name!=null && !name.isEmpty())
        {
            this.name = name;
        }else{
            throw new IllegalArgumentException("Please enter correct party name.");
        }
        if(size>0){
            this.size = size;
        }else {
            throw new IllegalArgumentException("Please enter correct party size.");
        }
        beingServed=false;
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

    public boolean checkRepeatedNames(List<Table> tablesServed, List<Party> waitList, String nameToCheck) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj != null && getClass() == obj.getClass()) {
            Party equalsParty = (Party) obj;

            return beingServed == equalsParty.beingServed &&
                    size == equalsParty.size &&
                    name.equals(equalsParty.name);
        }
        return false;
    }

    @Override
    public Party clone(){
        try {
            return (Party)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String toString() {

        return "Party Name: "+name + " whose size is: "+ size +
                (getBeingServed()? " who is being served.":" who is not served.");
    }

    @Override
    public int compareTo(Party o) {
        return  size-o.size;
    }
}
