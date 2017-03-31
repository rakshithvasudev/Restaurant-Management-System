import com.restaurant.rakshith.Party;
import com.restaurant.rakshith.Restaurant;
import com.restaurant.rakshith.Servers;
import com.restaurant.rakshith.Table;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rakshith on 3/21/2017.
 */
public class RestaurantTest {
    @Test
    public void constructorTest() {
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("Rname");

        //When(Action)
        restaurant.addTable(new Table(1, 6));

        // Then (Assertion)
        Assert.assertTrue("constructorTest failed", restaurant.getTables().size() == 1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest2() {
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("");

        //When(Action)
        restaurant.addTable(new Table(1, 6));

        // Then (Assertion)
        Assert.assertTrue("constructorTest2 failed", restaurant.getTables().size() == 1);

    }

    @Test
    public void addTableTest() {
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("Nmake");

        //When(Action)
        restaurant.addTable(new Table(1, 8));

        // Then (Assertion)
        Assert.assertTrue("addTableTest failed", restaurant.getTables().size() == 1);

    }


    @Test
    public void setNameTest() {
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("Nmake");

        //When(Action)
        restaurant.setName("new name");

        // Then (Assertion)
        Assert.assertTrue("setNameTest failed", restaurant.getName().equals("new name"));
    }

    @Test
    public void getServerOnDutyTest() {
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("Nmake");
        Servers servers = new Servers(1, true);

        //When(Action)
        restaurant.addServer(servers);

        // Then (Assertion)
        Assert.assertTrue("getServerOnDutyTest failed", restaurant.getServerCountOnDuty() == (1));
    }

    @Test
    public void addServerTest() {
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("Nmake");
        Servers servers = new Servers(1, true);
        Servers servers1 = new Servers(2, true);

        //When(Action)
        restaurant.addServer(servers);
        restaurant.addServer(servers1);

        // Then (Assertion)
        Assert.assertTrue("addServerTest failed", restaurant.getServerCountOnDuty() == (2));
    }


    @Test
    public void addToWaitListTest() {
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("El-Monte");
        Party party = new Party("Superman", 5);
        Party party1 = new Party("EvilMan", 7);

        //When(Action)
        restaurant.addToWaitList(party);
        restaurant.addToWaitList(party1);

        // Then (Assertion)
        Assert.assertTrue("addToWaitListTest failed", restaurant.getWaitList().size() == 2);
    }


    @Test
    public void removeFromWaitListTest() {
        //Given (Arrange)

        Restaurant restaurant = new Restaurant("El-Monte");
        Party party = new Party("Superman2", 5);
        Party party1 = new Party("EvilMan", 7);

        //When(Action)
        restaurant.addToWaitList(party);
        restaurant.removeFromWaitList(party);
        restaurant.addToWaitList(party1);

        // Then (Assertion)
        Assert.assertTrue("removeFromWaitListTest failed", restaurant.getWaitList().size() == 1);
    }

    @Test
    public void removeTableTest() {
        //Given (Arrange)

        Restaurant restaurant = new Restaurant("El-Monte");
        Table table = new Table(1, 5);
        Table table1 = new Table(2, 6);

        //When(Action)
        restaurant.addTable(table);
        restaurant.addTable(table1);
        restaurant.removeTable(table);

        // Then (Assertion)
        Assert.assertTrue("removeTableTest failed", restaurant.getTables().size() == 1);
    }

    @Test
    public void getBiggestTableSizeTest() {
        Restaurant restaurant = new Restaurant("El-Monte");
        Table table = new Table(1, 5);
        Table table1 = new Table(2, 6);

        //When(Action)
        restaurant.addTable(table);
        restaurant.addTable(table1);


        // Then (Assertion)
        Assert.assertEquals("getBiggestTableSizeTest failed", 6, restaurant.getBiggestTableSize());
    }

    @Test
    public void getBiggestTableSizeTest2() {
        Restaurant restaurant = new Restaurant("El-Monte");
        Table table = new Table(1, 5);
        Table table1 = new Table(2, 6);
        Table table2 = new Table(2, 8);


        //When(Action)
        restaurant.addTable(table);
        restaurant.addTable(table1);
        restaurant.addTable(table2);

        // Then (Assertion)
        Assert.assertEquals("getBiggestTableSizeTest2 failed", 8, restaurant.getBiggestTableSize());
    }


    @Test
    public void getEmptyTablesTest() {
        Restaurant restaurant = new Restaurant("El-Monte");
        Table table = new Table(1, 5);
        Table table1 = new Table(2, 6);

        //When(Action)
        restaurant.addTable(table);
        restaurant.addTable(table1);


        // Then (Assertion)
        Assert.assertEquals("getEmptyTablesTest failed", 2, restaurant.getEmptyTables());
    }


    @Test
    public void addToAllocationsTest() {
        Restaurant restaurant = new Restaurant("El-Monte");
        Table table = new Table(1, 9);
        Table table1 = new Table(2, 6);
        List<Table> tables = new ArrayList<>();
        Servers servers = new Servers(1, true);

        //When(Action)
        tables.add(table);
        tables.add(table1);
        restaurant.addToAllocations(servers, tables);


        // Then (Assertion)
        Assert.assertEquals("addToAllocationsTest failed", 1, restaurant.getAllocations().size());
    }


    @Test
    public void getBiggestTableUsableTest() {
        Restaurant restaurant = new Restaurant("El-Monte");
        Table table = new Table(1, 9);
        Table table1 = new Table(2, 6);

        //When(Action)
        restaurant.addTable(table);
        restaurant.addTable(table1);

        // Then (Assertion)
        Assert.assertEquals("getBiggestTableUsableTest failed", 9, restaurant.getBiggestTableUsable());
    }


//    @Test
//    public void optimizedTableMappingTest(){
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
//        mcd.optimizedTableMapping(ronald);
//
//
//        Assert.assertEquals("Couldn't map",table1.getParty().getName(),joe.getName());
//    }


}
