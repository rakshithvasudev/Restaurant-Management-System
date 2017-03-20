package com.restaurant.rakshith;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rakshith on 3/19/2017.
 */
public class Restaurant {
    private Map<Integer,Table> tables;
    private Map<String,Party> waitList;
    private Map<Integer,Servers> servers;
    private Map<Servers,List<Table>> allocations;
    private String name;

    public Restaurant(String name) {
        tables=new LinkedHashMap<>();
        waitList=new LinkedHashMap<>();
        servers = new LinkedHashMap<>();
        allocations=new LinkedHashMap<>();
        this.name = name;
    }




}
