package com.restaurant.rakshith;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

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

    public void optimizedTableMapping(Party party) {

        List<Integer> tableCapacities = new ArrayList<>();
        try {
            Scanner in = new Scanner(new File("tables.txt"));
            while (in.hasNext()) {
                if (in.hasNextInt()) {
                    tableCapacities.add(in.nextInt());
                } else {
                    in.next();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.sort(tableCapacities);

        for (Table tableItem : tables.values()) {
            if (!tableItem.getOccupiedStatus() && tableItem.getCapacity() >= party.getSize()) {
                int tableCapacity = tableItem.getCapacity();
                int partySize = party.getSize();
                int diff = tableCapacity - partySize;
                int currentSelectedTableIndex = tableCapacities.indexOf(tableItem.getCapacity());
                int nextHigherAvailableTableIndex = currentSelectedTableIndex++;


                while (tableCapacities.get(nextHigherAvailableTableIndex).
                        equals(tableCapacities.get(currentSelectedTableIndex))) {
                    nextHigherAvailableTableIndex++;
                }

                if (diff == 0) {
                    tableItem.setParty(party);
                    tableItem.setOccupiedStatus(true);
                    System.out.println("Party Seated at :"+ tableItem);
                } else if (diff > 0 &&
                        diff < (tableCapacities.get(nextHigherAvailableTableIndex) -
                                tableItem.getCapacity())) {
                    tableItem.setParty(party);
                    tableItem.setOccupiedStatus(true);
                    System.out.println("Party Seated at :"+ tableItem);
                }
            }
        }

        System.out.println("Couldn't map");
    }

    public void addTable(Table table){
        tables.put(table.getId(),table);
    }

}
