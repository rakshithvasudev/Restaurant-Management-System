import com.restaurant.rakshith.Party;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rakshith on 3/17/2017.
 */
public class PartyTest {

    @Test
    public void constructorTest() {

        int size = 4;
        String name = "Joe's Party";

        Party JoeParty = new Party(name, size);
        JoeParty.setBeingServed(true);
        JoeParty.setSize(size);
        JoeParty.setBeingServed(false);

        Assert.assertEquals("Didn't match", size, JoeParty.getSize());


    }
}
