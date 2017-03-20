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









}
