import com.restaurant.rakshith.Party;
import com.restaurant.rakshith.Servers;
import com.restaurant.rakshith.Table;
import org.junit.Assert;
import org.junit.Test;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Rakshith on 3/20/2017.
 */
public class ServersTest {
    @Test
    public void constructorTest(){
        //Given (Arrange)
        Servers servers = new Servers(1,true);
        Table table = new Table(1,2);
        Party joeParty = new Party("Joe's Party", 2);

        //When(Action)
        table.setParty(joeParty);
        table.setOccupiedStatus(true);
        table.setServer(servers);

        // Then (Assertion)
        Assert.assertTrue("Constructor Failed", table.getServer().getId()==1);
    }


    @Test
    public void constructorTest2(){
        //Given (Arrange)
        Servers servers = new Servers(2,true);
        Table table = new Table(1,2);
        Party mixParty = new Party("Joe's Party", 2);

        //When(Action)
        table.setParty(mixParty);
        table.setOccupiedStatus(true);
        table.setServer(servers);

        // Then (Assertion)
        Assert.assertTrue("Constructor Failed", table.getServer().getId()==2);
    }

    @Test
    public void getTablesServedTest(){
        //Given (Arrange)
        Servers servers = new Servers(2,true);
        Party mixParty = new Party("Joe's Party", 2);
        Party mixParty2 = new Party("Micheal's Party", 5);
        Map<Integer,Table> servedTables = new LinkedHashMap<>();

        Table table = new Table(1,2);
        Table table1 = new Table(2,5);
        servedTables.put(table.getId(),table);
        servedTables.put(table1.getId(),table1);


        //When(Action)
        table.setParty(mixParty);
        table.setOccupiedStatus(true);
        table.setServer(servers);
        table1.setParty(mixParty2);
        table1.setOccupiedStatus(true);
        table1.setServer(servers);

        servers.setTablesServed(servedTables);

        // Then (Assertion)
        Assert.assertTrue("get tables served  Failed", servers.getTablesServed().size()==2);
    }


    @Test
    public void setTablesServedTest(){
        //Given (Arrange)
        Servers servers = new Servers(3,true);
        Party mixParty = new Party("Joe's Party", 2);
        Party mixParty2 = new Party("Micheal's Party", 5);
        Map<Integer,Table> servedTables = new LinkedHashMap<>();

        Table table = new Table(1,2);
        Table table1 = new Table(2,5);
        Table table2 = new Table(3,3);
        servedTables.put(table.getId(),table);
        servedTables.put(table1.getId(),table1);
        servedTables.put(table2.getId(),table2);


        //When(Action)
        table.setParty(mixParty);
        table.setOccupiedStatus(true);
        table.setServer(servers);
        table1.setParty(mixParty2);
        table1.setOccupiedStatus(true);
        table1.setServer(servers);

        servers.setTablesServed(servedTables);

        // Then (Assertion)
        Assert.assertFalse("Served tables Failed", servers.getTablesServed().size()==2);
    }


    @Test
    public void setIdTest(){
        //Given (Arrange)
        Servers servers = new Servers(4,true);

        //When(Action)
        servers.setId(2);

        // Then (Assertion)
        Assert.assertTrue("Set Id Failed", servers.getId()==2);
    }



    @Test
    public void setonDutyTest(){
        //Given (Arrange)
        Servers servers = new Servers(4,true);

        //When(Action)
        servers.setOnDuty(false);

        // Then (Assertion)
        Assert.assertTrue("onDuty set Failed", !servers.getOnDuty());
    }



    @Test
    public void removeTableTest(){
        //Given (Arrange)
        Servers servers = new Servers(4,true);
        Table table = new Table(1,6);
        Table table1 = new Table(2,7);

        //When(Action)
        servers.serveAnotherTable(table);
        servers.serveAnotherTable(table1);

        //When(Extra Action)
        servers.removeTable(table1);

        // Then (Assertion)
        Assert.assertTrue("Removing Failed", servers.getTablesServed().size()==1);
    }


    @Test
    public void setTipsTest(){
        //Given (Arrange)
        Servers servers = new Servers(1,true);

        //When(Action)
        servers.setTips(10);
        servers.setTips(0.3);

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", servers.getTips()==10.3);
    }


    @Test
    public void cloneTest(){
        //Given (Arrange)
        Servers servers = new Servers(1,true);
        Servers cloned;
        //When(Action)
        cloned = servers.clone();

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", servers.equals(cloned));
    }


    @Test
    public void cloneTest2(){
        //Given (Arrange)
        Servers servers = new Servers(1,true);
        Servers cloned;
        //When(Action)
        cloned = servers.clone();

        // Then (Assertion)
        Assert.assertFalse("setting tips failed", servers==(cloned));
    }

    @Test
    public void  equalsTest(){
        //Given (Arrange)
        Servers servers = new Servers(1,true);
        Servers cloned;
        //When(Action)
        cloned = servers.clone();

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", servers.equals(cloned));
    }


    @Test
    public void  compareToTest(){
        //Given (Arrange)
        Servers servers = new Servers(1,true);
        Servers servers1 = new Servers(2,true);

        //When(Action)
        //nothing

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", servers.compareTo(servers1)<0);
    }



}
