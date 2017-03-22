import com.restaurant.rakshith.Party;
import com.restaurant.rakshith.Restaurant;
import com.restaurant.rakshith.Table;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Rakshith on 3/21/2017.
 */
public class RestaurantTest
{
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

        Assert.assertEquals("Couldn't map",table1.getParty().getName(),joe.getName());





    }

}
