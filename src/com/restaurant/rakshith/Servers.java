package com.restaurant.rakshith;


import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Rakshith on 3/17/2017.
 * This class is responsible for the servers object in the restaurant. This class supports
 * Cloning, Comparing. The static field addServerIndex must be initialized as soon as declared.
 * This field is imperative to create a new Servers object.
 * cloning performs a deep copy of the Servers object.
 */
public final class Servers implements Cloneable, Comparable<Servers>{

    private int id;
    private boolean onDuty;
    private Map<Integer,Table> tablesServed;
    private double tips;
    public static int addServerIndex=0;

    /**
     * This Constructor creates a new Servers object and initializes the other fields.
     * @param id sets the id of the Servers object. Needs to be larger than 0.
     * @param onDuty sets the server on duty or not on duty based on true or false
     *               respectively.
     * @throws IllegalArgumentException if the id is not larger than 0.
     */
    public Servers(int id, boolean onDuty) {
        if(id>0)
            this.id = id;
         else
             throw new IllegalArgumentException("Enter correct Id");

        tablesServed=new LinkedHashMap<>();
        this.onDuty = onDuty;
        tips= 0.0;
    }

    /**
     * Returns a LinkedHashMap of \<Integer, Table\> tables served.
     * @return the tables served by the current servers object.
     */
    public Map<Integer, Table> getTablesServed() {
        return tablesServed;
    }


    public void setTablesServed(Map<Integer,Table> tablesServed ) {
            this.tablesServed = new LinkedHashMap<>(tablesServed);
    }


    public int getId() {
        return id;
    }

    /**
     * Required just to test. Once id set wouldn't be
     * altered.
     * @param id sets the id.
     */
    public void setId(int id) {
        this.id = id;
    }

    public boolean getOnDuty() {
        return onDuty;
    }

    /**
     * Required just to test. Once duty set wouldn't be
     * altered.
     * @param onDuty sets the Duty status.
     */
    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    /**
     * Adds a new table to the server's serving list.
     * @param table new table that has to be added.
     */
    public void serveAnotherTable(Table table){
        tablesServed.put(table.getId(),table);
    }

    /**
     * Removes a table from the list of tables served.
     * @param table required table that needs to be removed.
     */
    public void removeTable(Table table) {
        tablesServed.remove(table.getId());
    }

    public double getTips() {
        return this.tips;
    }

    /**
     * Adds tip to the server.
     * @param tipsValue double value that has to be added.
     */
    public void setTips(double tipsValue) {
        this.tips= this.tips+tipsValue;
    }

    /**
     * Performs a deep copy of the given Servers object.
     * @return cloned Servers object.
     */
    @Override
    public Servers clone(){
        try {
            Servers copy = (Servers)super.clone();
            copy.tablesServed=new LinkedHashMap<>(tablesServed);
            return copy;
        }catch (CloneNotSupportedException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int hashCode() {
        int a = 883;
        a = id*a*(onDuty?967:1033);
        a=(tablesServed.hashCode())*a;
        return a;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj!=null&& getClass()==obj.getClass()){
             Servers objEqual = (Servers)obj;
           return onDuty==objEqual.onDuty &&
                   tips==objEqual.tips &&
                   tablesServed.equals(objEqual.tablesServed);
        }
        return false;
    }

    @Override
    public String toString() {
          StringBuilder builder = new StringBuilder();
                for(Table tableItem : tablesServed.values()){
                    builder=builder.append(tableItem).append(", ");
                }

                return "Server id: "+id+" duty status:"
                +(onDuty?" yes, ":" no, ") + "Serving Tables : " +
                        (builder.length()==0?"None": builder);
    }

    /**
     * returns an integer comparing the 2 servers.
     * @param o other server.
     * @return >0 if this server id>other server, ==0
     * if they servers are equal & <0 otherwise.
     */
    public int compareTo(Servers o){
        return id-o.id;
    }

}
