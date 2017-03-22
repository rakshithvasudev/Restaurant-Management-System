package com.restaurant.rakshith;

/**
 * Created by Rakshith on 3/19/2017.
 */
public class DeleteMain {
    public static void main(String[] args) {
        Party joe = new Party("Joe's Party",5);
        Party ronald = new Party("Ronald's Party",4);

        Table table1 = new Table(1,5);
        table1.setCapacity(6);
        Table table2 = new Table(2,5);
        table2.setCapacity(4);


        Restaurant mcd = new Restaurant("Mcd");
        mcd.optimizedTableMapping(joe);

//        System.out.println(table1.getParty().getName());
//        System.out.println(joe.getName());



    }

}
