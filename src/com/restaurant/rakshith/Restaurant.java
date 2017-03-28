package com.restaurant.rakshith;

import java.util.*;


/**
 * Created by Rakshith on 3/19/2017.
 */
public class Restaurant {
    private Map<Integer, Table> tables;
    private Map<String, Party> waitList;
    private Map<Integer, Servers> servers;
    private Map<Servers, List<Table>> allocations;
    private String name;

    public Restaurant(String name) {
        tables = new LinkedHashMap<>();
        waitList = new LinkedHashMap<>();
        servers = new LinkedHashMap<>();
        allocations = new LinkedHashMap<>();

        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Enter correct value for restaurant name");
        }

    }

    /**
     * This method places the party in the right table.
     * @param party the party supposed to be seated.
     */
    public void optimizedTableMapping(Party party) {
        List<Table> tableList = new ArrayList<>(tables.values());
        Collections.sort(tableList);
        Iterator tableIterator = tableList.iterator();
        int currentIndex = 0;
        boolean setTheParty = false;
        while (tableIterator.hasNext()) {
            //Use the iterator to iterate through the available tables.
            Table currentTable = (Table) tableIterator.next();
            if (!currentTable.getOccupiedStatus() &&
                    currentTable.getCapacity() == party.getSize()) {
                currentTable.setParty(party);
                currentTable.setOccupiedStatus(true);
                setTheParty=true;
                System.out.println("Party seated at " + currentTable);
                break;
            }

            //Fix any indexOutOfBoundsExceptions that might occur.
            if (currentIndex + 1 > tableList.size() - 1) {
                currentIndex--;
            }

            //get the next table and compare the capacity.
            Table nextTable = tableList.get(currentIndex + 1);
            //if the currentTable is unoccupied and the current table's
            //capacity exceeds the party size,
            if (!currentTable.getOccupiedStatus() &&
                    currentTable.getCapacity() > party.getSize()) {
                if (nextTable.getCapacity() - currentTable.getCapacity() >= 0) {
                    currentTable.setParty(party);
                    currentTable.setOccupiedStatus(true);
                    System.out.println("Party seated at " + currentTable);
                    setTheParty=true;
                    break;
                }
            }
            currentIndex++;
        }
        if(!setTheParty){
            System.out.println("Sorry! can't serve this party: " +party.getName());
        }

    }

    public void addTable(Table table){
        tables.put(table.getId(),table);
    }

    public Map<Integer, Table> getTables() {
        return tables;
    }

    public void setName(String name) {
        this.name = name;
    }
}
