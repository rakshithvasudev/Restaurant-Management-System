import com.restaurant.rakshith.Party;
import com.restaurant.rakshith.Table;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rakshith on 3/17/2017.
 */
public class PartyTest {

    @Test
    public void constructorTest() {
        //Given (Arrange)
        int size = 4;
        String name = "Joe's Party";

        //When(Action)
        Party JoeParty = new Party(name, size);
        JoeParty.setBeingServed(true);
        JoeParty.setSize(size);
        JoeParty.setBeingServed(false);

        // Then (Assertion)
        Assert.assertEquals("Didn't match", size, JoeParty.getSize());
    }


    @Test
    public void RepeatedNameTest(){
        //Given (Arrange)
        List<Party> parties = new ArrayList<>();
        List<Table> tables = new ArrayList<>();

        //When(Action)
        Party aParty = new Party("aParty",4);
        Party bParty = new Party("bParty",3);
        Party copyParty = new Party("aParty",4);
        parties.add(aParty);
        parties.add(bParty);
        parties.add(copyParty);
        Table table1 = new Table(1,1);
        tables.add(table1);

        // Then (Assertion)
        Assert.assertEquals("Sorry, Test failed",aParty.checkRepeatedNames(tables,parties,"aParty"),true);
        Assert.assertEquals("Sorry test failed", bParty.checkRepeatedNames(tables,parties,"bParty"),true);
    }


    @Test(expected = IllegalArgumentException.class)
    public void IllegalSizeTest(){
        //Given
        Party aParty = new Party("aParty",0);

        //When (Action)
        aParty.setBeingServed(true);

        //Assert
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void IllegalNameTest(){
        //Given
        Party aParty = new Party(null,0);

        //When (Action)
        aParty.setBeingServed(true);

        //Assert
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void IllegalNameTest2(){
        //Given
        Party aParty = new Party("",0);

        //When (Action)
        aParty.setBeingServed(true);

        //Assert
        Assert.fail();
    }

    @Test
    public void beingServedTest(){
        //Given (Arrange)
        Party aParty = new Party("aParty",4);
        Party bParty = new Party("bParty",3);

        //When(Action)
        bParty.setBeingServed(true);
        aParty.setBeingServed(false);

        //Assert
        Assert.assertEquals("Sorry, Being served test failed",aParty.getBeingServed(),false);
        Assert.assertEquals("Sorry, Being served test failed",bParty.getBeingServed(),true);
    }

    @Test
    public void getSizeTest(){
        //Given (Arrange)
        Party aParty = new Party("aParty",4);

        //When(Action)
         aParty.setSize(6);

        //Assert
        Assert.assertEquals("Sorry, Being served test failed",aParty.getSize(),6);
    }


    @Test(expected = IllegalArgumentException.class)
    public void getSizeCornerTest(){
        //Given (Arrange)
        Party sPartyCorner = new Party("aParty",0);

        //When(Action)
        sPartyCorner.setSize(5);

        //Assert
        Assert.assertEquals("Size Corner Test Failed!",sPartyCorner.getSize(),5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void getSizeCornerTest2(){
        //Given (Arrange)
        Party sPartyCorner = new Party("aParty",-1);

        //When(Action)
        sPartyCorner.setSize(5);

        //Assert
        Assert.assertEquals("Size Corner Test Failed!",sPartyCorner.getSize(),5);
    }

    @Test
    public void setSizeTest(){
        //Given (Arrange)
        Party Ray = new Party("Ray's Party",2);

        //When(Action)
        Ray.setSize(5);

        //Assert
        Assert.assertEquals("Size Test Failed!",Ray.getSize(),5);
    }


    @Test
    public void getBeingServedTest(){
        //Given (Arrange)
        Party Ray = new Party("Ray's Party",3);

        //When(Action)
        Ray.setBeingServed(true);

        //Assert
        Assert.assertEquals("Current Serve Test Failed!",Ray.getBeingServed(),true);
    }


    @Test
    public void setBeingServedTest(){
        //Given (Arrange)
        Party joe = new Party("Joe's Party",3);

        //When(Action)
        joe.setBeingServed(false);

        //Assert
        Assert.assertEquals("Current Serve Test Failed!",joe.getBeingServed(),false);
    }

    @Test
    public void checkRepeatedNamesTest(){
        //Given
        Party joe = new Party("Joe's Party",3);
        Party joe2 = new Party("Joe's Party",3);
        Party miller = new Party("Miller's Party",3);

        Table table = new Table(1,4);
        Table table1 = new Table(2,4);
        Table table2 = new Table(3,5);
        List<Party> partyWaitList = new ArrayList<>();
        List<Table> tablesServed = new ArrayList<>();

        //When (Action)
        partyWaitList.add(joe2);
        tablesServed.add(table);
        tablesServed.add(table1);
        tablesServed.add(table2);

        table.setParty(joe);
        table1.setParty(miller);

        //Assert
        Assert.assertTrue("Repeated name test failed",
                Party.checkRepeatedNames(tablesServed,partyWaitList,joe.getName()));

    }

    @Test
    public void equalsTest(){
        Party joe = new Party("Joe's Party",3);
        Party ryan = new Party("Ryan's Party",3);

        //When (Action)
        joe.setBeingServed(true);
        ryan.setBeingServed(true);

        //Assert
        Assert.assertTrue("Equals test Failed" , joe.equals(ryan));
    }

    @Test
    public void cloneTest(){
        Party joe = new Party("Joe's Party",3);
        Party clonedJoe;

        //When (Action)
        clonedJoe=joe.clone();

        //Assert
        Assert.assertTrue("Clone test Failed" , joe.equals(clonedJoe));
    }


    @Test
    public void cloneTest2(){
        Party joe = new Party("Joe's Party",3);
        Party clonedJoe;

        //When (Action)
        clonedJoe=joe.clone();

        //Assert
        Assert.assertFalse("Clone test Failed" , joe==(clonedJoe));
    }


    @Test
    public void toStringTest(){
        Party joe = new Party("Joe's Party",3);

        //When (Action)
        String originalToString  = joe.toString();
        String expectedToString  = "Party Name: " +joe.getName() + " whose size is: "+ joe.getSize()+
        (joe.getBeingServed()? " who is being served.":" who is not served.");

        //Assert
        Assert.assertTrue("toString test Failed" , originalToString.equals(expectedToString));
    }



}
