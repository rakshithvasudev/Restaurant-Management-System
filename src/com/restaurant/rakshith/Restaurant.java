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
    private double cashRegister;

    public Restaurant(String name) {
        tables = new LinkedHashMap<>();
        waitList = new LinkedHashMap<>();
        servers = new LinkedHashMap<>();
        allocations = new LinkedHashMap<>();
        cashRegister=0.0;
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Enter correct value for restaurant name");
        }
    }

    /**
     * This method places the party in the right table.
     *
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
                setTheParty = true;
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
            //capacity exceeds the party size, set the party.
            if (!currentTable.getOccupiedStatus() &&
                    currentTable.getCapacity() > party.getSize()) {
                if (nextTable.getCapacity() - currentTable.getCapacity() >= 0) {
                    currentTable.setParty(party);
                    currentTable.setOccupiedStatus(true);
                    System.out.println("Party seated at " + currentTable);
                    setTheParty = true;
                    break;
                }
            }
            currentIndex++;
        }
        if (!setTheParty) {
            System.out.println("Sorry! can't serve this party: " + party.getName());
        }
    }

    public void addTable(Table table) {
        tables.put(table.getId(), table);
    }

    public Map<Integer, Table> getTables() {
        return tables;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServerCountOnDuty() {
        int onDutyCount = 0;
        for (Servers currentServer : servers.values()) {
            if (currentServer.getOnDuty())
                onDutyCount++;
        }
        return onDutyCount;
    }

    public void addServer(Servers server) {
        servers.put(server.getId(), server);
    }

    public Map<Integer, Servers> getServers() {
        return servers;
    }

    public void addToWaitList(Party party) {
        waitList.put(party.getName(), party);
    }

    public void removeFromWaitList(Party party) {
        waitList.remove(party.getName());
    }

    public void removeTable(Table table) {
        tables.remove(table.getId());
    }

    public int getBiggestTableSize() {
        int largestSize = 0;
        for (Table currentTable : tables.values())
            if (currentTable.getCapacity() > largestSize)
                largestSize = currentTable.getCapacity();
        return largestSize;
    }

    public int getBiggestTableUsable() {
        int largestSize = 0;
          for (Table currentTable : tables.values())
            if (currentTable.getCapacity() > largestSize &&
                    !currentTable.getOccupiedStatus())
                largestSize = currentTable.getCapacity();
        return largestSize;
    }

    public void addToCashRegister(double value){
        cashRegister+=value;
    }

    public boolean canServerWork(Servers server){
      //if a there are x servers working, this server can work only
        // if this server serves 1 less than the number of available servers. i.e <x-1
        //return server.getTablesServed().size()<servers.size()-1;
        return server.getOnDuty();
    }


}