package com.restaurant.rakshith;

import java.io.CharArrayReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Rakshith on 3/19/2017.
 */
public class DeleteMain {
    public static void main(String[] args) {
//        Party joe = new Party("Joe's Party",5);
//        Party ronald = new Party("Ronald's Party",4);
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
////        System.out.println(table1.getParty().getName());
////        System.out.println(joe.getName());


        StringBuilder restaurantName = new StringBuilder();
        List<Integer> number = new ArrayList<>();
        StringBuilder numbersSb = new StringBuilder();

        try {
            Scanner in = new Scanner(new File("tables.txt"));
            while (in.hasNext("[A-Za-z]+")) {
                restaurantName.append(in.next("[0-9]+"));
                numbersSb.append(in.next("[a-zA-Z]+").trim());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(restaurantName.toString());
        for (int i = 0; i < numbersSb.length(); i++) {
            if (Character.getNumericValue(numbersSb.charAt(i)) != -1) {
                number.add(Character.getNumericValue(numbersSb.charAt(i)));
            }
        }
        System.out.println(number.toString());
    }
}

