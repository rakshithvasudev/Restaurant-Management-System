package com.restaurant.rakshith;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Rakshith on 3/17/2017.
 */
public final class Servers implements Cloneable, Comparable<Servers>{

    private int id;
    private boolean onDuty;
    private Map<Integer,Table> tablesServed;
    private double tips;
    public static int addServerIndex=0;


    public Servers(int id, boolean onDuty) {
        tablesServed=new LinkedHashMap<>();
        this.id = id;
        this.onDuty = onDuty;
        tips=0.0;
    }


    public Map<Integer, Table> getTablesServed() {
        return tablesServed;
    }


    public void setTablesServed(Map<Integer,Table> tablesServed ) {
        this.tablesServed=tablesServed;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getOnDuty() {
        return onDuty;
    }

    public void setOnDuty(boolean onDuty) {
        this.onDuty = onDuty;
    }

    public void serveAnotherTable(Table table){
        tablesServed.put(table.getId(),table);
    }

    public void removeTable(Table table) {
        tablesServed.remove(table.getId());
    }

    public double getTips() {
        return tips;
    }

    public void setTips(double tips) {
        this.tips+= tips;
    }

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
                   tablesServed==objEqual.tablesServed;
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

    public int compareTo(Servers o){
        return id-o.id;
    }

}
