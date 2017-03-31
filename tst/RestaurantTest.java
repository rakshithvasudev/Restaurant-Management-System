import com.restaurant.rakshith.Party;
import com.restaurant.rakshith.Restaurant;
import com.restaurant.rakshith.Servers;
import com.restaurant.rakshith.Table;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rakshith on 3/21/2017.
 */
public class RestaurantTest
{
    @Test
    public void constructorTest(){
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("Rname");

        //When(Action)
        restaurant.addTable(new Table(1,6));

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", restaurant.getTables().size()==1);

    }

    @Test(expected = IllegalArgumentException.class)
    public void constructorTest2(){
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("");

        //When(Action)
        restaurant.addTable(new Table(1,6));

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", restaurant.getTables().size()==1);

    }

    @Test
    public void addTableTest(){
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("Nmake");

        //When(Action)
        restaurant.addTable(new Table(1,8));

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", restaurant.getTables().size()==1);

    }


    @Test
    public void setNameTest(){
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("Nmake");

        //When(Action)
        restaurant.setName("new name");

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", restaurant.getName().equals("new name"));
    }

    @Test
    public void getServerOnDutyTest(){
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("Nmake");
        Servers servers = new Servers(1,true);

        //When(Action)
        restaurant.addServer(servers);

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", restaurant.getServerCountOnDuty()==(1));
    }

    @Test
    public void addServerTest(){
        //Given (Arrange)
        Restaurant restaurant = new Restaurant("Nmake");
        Servers servers = new Servers(1,true);
        Servers servers1 = new Servers(2,true);

        //When(Action)
        restaurant.addServer(servers);
        restaurant.addServer(servers1);

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", restaurant.getServerCountOnDuty()==(2));
    }


    @Test
    public void addToWaitListTest(){
        Restaurant restaurant = new Restaurant("El-Monte");
        Party party = new Party("Superman",5);
        Party party1 = new Party("EvilMan", 7);

        //When(Action)
        restaurant.addToWaitList(party);
        restaurant.addToWaitList(party1);

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", restaurant.getWaitList().size()==2);
    }


    @Test
    public void removeFromWaitListTest(){
        Restaurant restaurant = new Restaurant("El-Monte");
        Party party = new Party("Superman2",5);
        Party party1 = new Party("EvilMan", 7);

        //When(Action)
        restaurant.addToWaitList(party);
        restaurant.removeFromWaitList(party);
        restaurant.addToWaitList(party1);

        // Then (Assertion)
        Assert.assertTrue("setting tips failed", restaurant.getWaitList().size()==1);
    }


    @Test
    public void optimizedTableMappingTest(){
        Party joe = new Party("Joe's Party",5);
        Party ronald = new Party("Ronald's Party",4);

        Table table1 = new Table(1,5);
        table1.setCapacity(6);
        Table table2 = new Table(2,5);
        table2.setCapacity(4);


        Restaurant mcd = new Restaurant("Mcd");
        mcd.optimizedTableMapping(joe);
        mcd.optimizedTableMapping(ronald);


        Assert.assertEquals("Couldn't map",table1.getParty().getName(),joe.getName());
    }





}
