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
        Table table1 = new Table(1,0);
        tables.add(table1);

        // Then (Assertion)
        Assert.assertEquals("Sorry, Test failed",aParty.checkRepeatedNames(tables,parties,"aParty"),true);
    }






}
