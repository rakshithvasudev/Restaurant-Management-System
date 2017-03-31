package com.restaurant.rakshith;

import java.util.*;


/**
 * Created by Rakshith on 3/19/2017.
 * This class is responsible for the entire restaurant to be handled. This class
 * is not to be extended and all the fields are initialized in the constructor.
 * allocations is just for the sake of testing. This doesn't implement cloneable.
 * The default clone would not work properly. Collections used are mostly
 * linkedHashMap because they support the retrieval of elements in the inserted order
 * upon the usage of either keySet() values() and EntrySet() methods based on the
 * requirements respectively.
 *
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
     * As long as there is availability of tables that
     * are unoccupied. This would mean that the Party, Servers
     * and Table perform right handshakes with their setting and
     * getting values.
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
                manageTable(party, currentTable);
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
                    manageTable(party, currentTable);
                    return true;
                }
            currentIndex++;
        }
        return false;
    }

    /**
     * Manages the table by setting the party, occupiedStatus and
     * calls setServerForParty(Table,Party)method.
     *
     * @param party        party that has to be seated.
     * @param currentTable the table where server must be assigned.
     */
    private void manageTable(Party party, Table currentTable) {
        currentTable.setParty(party);
        currentTable.setOccupiedStatus(true);
        setServerForParty(currentTable, party);
    }

    /**
     * Sets a server for the required Party at the Required Table.
     *
     * @param currentTable the table that has the party.
     * @param party        the actual party.
     * @pre none of the arguments must be null.
     */
    private void setServerForParty(Table currentTable, Party party) {
        int counterIndex = 1;
//        List<Servers> serversList = new ArrayList<>(servers.values());
        if (servers.size() > 0)
            //values() are retrieved in the order inserted.
            for (Servers currentServer : servers.values())
                if (currentServer.getOnDuty()) {
                    if (currentServer.getTablesServed().size() > 0 &&
                            servers.size() % counterIndex == 0)
                        continue;
                    currentServer.serveAnotherTable(currentTable);
                    currentTable.setServer(currentServer);
                    party.setBeingServed(true);
                    counterIndex++;
                    //addToAllocations(currentServer,new ArrayList<>(tableMap.values()));
//                    System.out.println("Party seated at " + currentTable);
                    break;

                }

    }

    /**
     * Adds a Table to the restaurant. Puts in the
     * tables hashMap of the restaurant.
     *
     * @param table table that has to be added.
     * @pre table is not null.
     */
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

    /**
     * Adds a server to the restaurant.
     *
     * @param server the server to be added.
     * @pre Server can't be null.
     */
    public void addServer(Servers server) {
        servers.put(server.getId(), server);
    }

    /**
     * Adds a party to a waitList.
     *
     * @param party the party to be added to the list.
     */
    public void addToWaitList(Party party) {
        waitList.put(party.getName(), party);
    }

    /**
     * Removes a party from the waitList
     *
     * @param party the party to be removed.
     */
    public void removeFromWaitList(Party party) {
        waitList.remove(party.getName());
    }

    /**
     * Removes a table from the restaurant.
     *
     * @param table table that has to be removed.
     */
    public void removeTable(Table table) {
        tables.remove(table.getId());
    }

    /**
     * Gets the largestSize of the table in the Restaurant.
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
     * Gets the largestSize of table in the Restaurant that's Unoccupied.
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

    /**
     * If there is already a record matching, just update the tables for
     * that record.
     *
     * @param server object that matches the id of the server.
     * @param tables the list of tables that must be for that server added.
     */
    public void addToAllocations(Servers server, List<Table> tables) {
        List<Table> tablesServed;
        if (allocations.size() > 0)
            for (int currentServerId : servers.keySet()) {
                if (server.getId() == currentServerId) {
                    tablesServed = new ArrayList<>(allocations.get(server));
                    for (Table currentTable : tables) {
                        tablesServed.add(currentTable);
                    }
                    break;
                }
            }

        if (allocations.size() == 0)
            allocations.put(server, tables);

    }

}