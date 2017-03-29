package com.restaurant.rakshith;

import java.util.*;


/**
 * Created by Rakshith on 3/19/2017.
 */
public final class Restaurant {
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
        cashRegister = 0.0;
        if (name != null && !name.isEmpty()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("Enter correct value for restaurant name.");
        }
    }

    /**
     * Places the party in the right table.
     * Prints where the Party was seated.
     * If that was not made possible, then a
     * not possible message is printed.
     *
     * @param party the party supposed to be seated.
     * @pre : The party is not null and has a practically finite number of
     * people as members.
     * @post: Seats the Party in the most appropriate manner
     * as mentioned in the spec.
     */
    public boolean optimizedTableMapping(Party party) {
        List<Table> tableList = new ArrayList<>(tables.values());
        Collections.sort(tableList);
        Iterator tableIterator = tableList.iterator();
        int currentIndex = 0;
        while (tableIterator.hasNext()) {
            Table currentTable = (Table) tableIterator.next();
            if (!currentTable.getOccupiedStatus() &&
                    currentTable.getCapacity() == party.getSize()) {
                currentTable.setParty(party);
                currentTable.setOccupiedStatus(true);
                //System.out.println("Party seated at " + currentTable);
                return true;
            }
            //Fix any indexOutOfBoundsExceptions that might occur.
            if (currentIndex + 1 > tableList.size() - 1)
                currentIndex--;
            //get the next table and compare the capacity.
            Table nextTable = tableList.get(currentIndex + 1);
            //if the currentTable is unoccupied and the current table's
            //capacity exceeds the party size, set the party.
            if (!currentTable.getOccupiedStatus() &&
                    currentTable.getCapacity() > party.getSize())
                if (nextTable.getCapacity() - currentTable.getCapacity() >= 0) {
                    currentTable.setParty(party);
                    currentTable.setOccupiedStatus(true);
                    //System.out.println("Party seated at " + currentTable);
                    return true;
                }
            currentIndex++;
        }

        return false;
    }

    public void addTable(Table table) {
        tables.put(table.getId(), table);
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

    public void addToWaitList(Party party) {
        waitList.put(party.getName(), party);
    }

    public void removeFromWaitList(Party party) {
        waitList.remove(party.getName());
    }

    public void removeTable(Table table) {
        tables.remove(table.getId());
    }

    /**
     * Gets the largest table in the Restaurant.
     *
     * @return largestSize in the available Map of Tables.
     * @Pre: The tables field is not null.
     */
    public int getBiggestTableSize() {
        int largestSize = 0;
        for (Table currentTable : tables.values())
            if (currentTable.getCapacity() > largestSize)
                largestSize = currentTable.getCapacity();
        return largestSize;
    }

    /**
     * Gets the largest table in the Restaurant that's Unoccupied.
     *
     * @return the largestSize of the Unoccupied Tables.
     * @Pre: The tables field is not null.
     */
    public int getBiggestTableUsable() {
        int largestSize = 0;
        for (Table currentTable : tables.values())
            if (currentTable.getCapacity() > largestSize &&
                    !currentTable.getOccupiedStatus())
                largestSize = currentTable.getCapacity();
        return largestSize;
    }

    public void addToCashRegister(double value) {
        cashRegister += value;
    }

    public boolean canServerWork(Servers server) {
        //if a there are x servers working, this server can work only
        // if this server serves 1 less than the number of available servers. i.e <x-1
        return server.getTablesServed().size() < servers.size() - 1;
        //return server.getOnDuty();
    }

    /**
     * Gets the number of emptyTables that are not being used by the Restaurant.
     *
     * @return the number of emptyTables.
     */
    public int getEmptyTables() {
        int emptyTableCount = 0;
        for (Table currentTable : tables.values())
            if (!currentTable.getOccupiedStatus())
                emptyTableCount++;
        return emptyTableCount;
    }

    public Map<String, Party> getWaitList() {
        return waitList;
    }

    public Map<Integer, Table> getTables() {
        return tables;
    }

    public double getCashRegister() {
        return cashRegister;
    }

    public String getName() {
        return name;
    }

    public Map<Servers, List<Table>> getAllocations() {
        return allocations;
    }

    public Map<Integer, Servers> getServers() {
        return servers;
    }

    public void addToAllocations(Servers server, List<Table> tables) {
        allocations.put(server, tables);
    }
}