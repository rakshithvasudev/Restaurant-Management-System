package com.restaurant.rakshith;

import java.io.*;
import java.util.*;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 * Created by Rakshith on 3/19/2017.
 */
public class DeleteMain {
    public static void main(String[] args) {
        Party joe = new Party("Joe's Party",4);
        Party ronald = new Party("Ronald's Party",5);
        Party ray = new Party("Ray's Party",5);
        Party donald = new Party("Donald",3);
        Party big = new Party("Large party",52);
//
//        Table table1 = new Table(1,5);
//        table1.setCapacity(6);
//        Table table2 = new Table(2,5);
//        table2.setCapacity(4);
//
//
//        Restaurant mcd = new Restaurant("Mcd");
//        mcd.optimizedTableMapping(joe);
//
        //System.out.println(table1.getParty().getName());
        //System.out.println(joe.getName());


        Restaurant restaurant = new Restaurant("El-Monte Grill");
        StringBuilder restaurantName = new StringBuilder();
        StringBuilder numbersSb = new StringBuilder();

        try {
            Scanner in = new Scanner(new File("tables.txt")).useDelimiter("\\n");
            for (int i = 0; i < 2; i++) {
                if (i == 0)
                    restaurantName.append(in.next());
                else if (i == 1)
                    numbersSb.append(in.next());
            }
            restaurant.setName(restaurantName.toString());

        }catch (FileNotFoundException e) {
            // when there is an error reading the file,
            System.out.println("Unable to read restaurant data: File not Found.");
            e.printStackTrace();
        }


        for (int i = 0; i < numbersSb.length(); i+=2) {
             restaurant.addTable(new Table(++Table.index, Character.getNumericValue(numbersSb.charAt(i))));
        }


//        Servers someOne = new Servers(1,true);
//        Servers someTwo = new Servers(2,true);
//
//        restaurant.addServer(someOne);
//        restaurant.addServer(someTwo);
//
//        Map.Entry<Integer,Servers> idOfServerTobeDismissed = restaurant.getServers().entrySet().iterator().next();
       // restaurant.getServers().remove(idOfServerTobeDismissed.getKey());

        //System.out.println(idOfServerTobeDismissed);

       // System.out.println(restaurant.getServers());



//

        Servers servers1 = new Servers(1,true);
        Servers servers2 = new Servers(2,true);
        Servers servers3 = new Servers(3,true);
        restaurant.addServer(servers1);
        restaurant.addServer(servers2);
        restaurant.addServer(servers3);
//
//        System.out.println(restaurant.getServers());



        restaurant.optimizedTableMapping(ronald);
        restaurant.optimizedTableMapping(joe);
        restaurant.optimizedTableMapping(ray);
        restaurant.optimizedTableMapping(donald);

        System.out.println(servers1.getTablesServed());
//        restaurant.optimizedTableMapping(big);
//
//
//
//        System.out.println("Allocations"+ restaurant.getAllocations());
    }
}

