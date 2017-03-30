import com.restaurant.rakshith.Party;
import com.restaurant.rakshith.Servers;
import com.restaurant.rakshith.Table;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rakshith on 3/17/2017.
 */

public class TableTest {

    @Test
    public void constructorTest(){
        //Given (Arrange)
        Table table = new Table(1,2);
        Party joeParty = new Party("Joe's Party", 2);

        //When(Action)
         table.setParty(joeParty);

        // Then (Assertion)
        Assert.assertEquals("Constructor Failed", table.getParty().getName(), joeParty.getName());

    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorIllegalArgumentTest(){
        //Given (Arrange)
        Table table = new Table(1,-12);
        Party joeParty = new Party("Joe's Party", 2);

        //When(Action)
        table.setParty(joeParty);

        // Then (Assertion)
        Assert.assertEquals("Didn't throw IllegalArgumentException",
                table.getParty().getName(), joeParty.getName());
    }


    @Test(expected = IllegalArgumentException.class)
    public void constructorIllegalArgumentTest2(){
        //Given (Arrange)
        Table table = new Table(-10,-12);
        Party joeParty = new Party("Joe's Party", 2);

        //When(Action)
        table.setParty(joeParty);

        // Then (Assertion)
        Assert.assertEquals("Didn't throw IllegalArgumentException",
                table.getParty().getName(), joeParty.getName());
    }

    @Test
    public void getPartyTest(){
        //Given (Arrange)
        Table table = new Table(1,12);
        Party joeParty = new Party("Joe's Party", 2);

        //When(Action)
        table.setParty(joeParty);

        // Then (Assertion)
        Assert.assertEquals("Didn't throw IllegalArgumentException",
                table.getParty().getName(), joeParty.getName());
    }


    @Test
    public void setPartyTest(){
        //Given (Arrange)
        Table table = new Table(1,12);
        Party rParty = new Party("Ryan's Party", 2);

        //When(Action)
        table.setParty(rParty);

        // Then (Assertion)
        Assert.assertEquals("Didn't throw IllegalArgumentException",
                table.getParty().getName(), rParty.getName());
    }

   @Test
   public void getOccupiedStatusTest(){
       //Given (Arrange)
       Table table = new Table(1,12);

       //When(Action)
       table.setOccupiedStatus(true);

       // Then (Assertion)
       Assert.assertTrue("Not matching status",table.getOccupiedStatus());
   }

    @Test
    public void setOccupiedStatusTest(){
        //Given (Arrange)
        Table table = new Table(1,12);

        //When(Action)
        table.setOccupiedStatus(false);

        // Then (Assertion)
        Assert.assertFalse("Not matching status",table.getOccupiedStatus());
    }

    @Test
    public void getIdTest(){
        //Given (Arrange)
        Table table = new Table(1,12);

        //When(Action)
        table.setId(2);

        // Then (Assertion)
        Assert.assertEquals("Not matching id",table.getId(),2);
    }


    @Test
    public void setIdTest(){
        //Given (Arrange)
        Table table = new Table(1,12);

        //When(Action)
        table.setId(6);

        // Then (Assertion)
        Assert.assertFalse("Not matching id",table.getId()<2);
    }



    @Test
    public void getCapacityTest(){
        //Given (Arrange)
        Table table = new Table(1,12);

        //When(Action)
        table.setCapacity(6);

        // Then (Assertion)
        Assert.assertEquals("Not matching capacity",table.getCapacity(),6);
    }

    @Test
    public void setCapacityTest(){
        //Given (Arrange)
        Table table = new Table(1,12);

        //When(Action)
        table.setCapacity(16);

        // Then (Assertion)
        Assert.assertFalse("Not matching capacity",table.getCapacity()!=16);
    }

    @Test
    public void getServerTest(){
        //Given (Arrange)
        Table table = new Table(1,12);
        Servers servers = new Servers(1,true);

        //When(Action)
        table.setServer(servers);


        // Then (Assertion)
        Assert.assertEquals("Not matching capacity",table.getServer().getId(),1);
    }


    @Test
    public void setServerTest(){
        //Given (Arrange)
        Table table = new Table(1,12);
        Servers servers1 = new Servers(1,true);
        //When(Action)
        table.setServer(servers1);


        // Then (Assertion)
        Assert.assertFalse("Not matching capacity",table.getServer().getId()>1);
    }


    @Test
    public void readjustTableTest(){
        //Given (Arrange)
        Table table = new Table(1,12);
        Servers servers1 = new Servers(1,true);
        Party party = new Party("Mac Joe Harry",6);
        //When(Action)
        table.setServer(servers1);
        table.setParty(party);
        table.setOccupiedStatus(true);

        //When (More Action)
        table.readjustTable();

        // Then (Assertion)
        Assert.assertFalse("Not matching capacity",table.getServer()!=null
        && table.getParty()!=null && !table.getOccupiedStatus());
    }

    @Test
    public void equalsTest(){

        //Given (Arrange)
        Table table = new Table(1,6);
        Servers servers1 = new Servers(1,true);
        Party party = new Party("Mac Joe Harry",6);
        Table clonedTable;

        //When(Action)
        table.setServer(servers1);
        table.setParty(party);
        table.setOccupiedStatus(true);
        clonedTable = table.clone();

        // Then (Assertion)
        Assert.assertTrue("Not matching states",table.equals(clonedTable));

    }

    @Test
    public void cloneTest(){

        //Given (Arrange)
        Table table1 = new Table(2,6);
        Servers servers2 = new Servers(1,true);
        Party party = new Party("Mac",4);
        Table clonedTable;

        //When(Action)
        table1.setServer(servers2);
        table1.setParty(party);
        table1.setOccupiedStatus(true);
        clonedTable = table1.clone();

        // Then (Assertion)
        Assert.assertTrue("Cloned Not matching states",table1.equals(clonedTable));

    }


    @Test
    public void compareToTest(){
        // Given (Arrange)
        Table table1 = new Table(2,9);
        Table table2 = new Table(2,6);

        // When (Nothing)

        // Then (Assertion)
        Assert.assertTrue("Not matching states",table1.compareTo(table2)==3);

    }



}
