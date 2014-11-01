import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by thomkel on 10/24/14.
 */
public class SellerTest {
    Seller tester;
    UserID mUserId;

    @BeforeClass
    public void testSetup() {
        mUserId = new UserID(5555);
        tester = new Seller(mUserId);
    }

    @Test
    public void testConstructor() {
        // assertEquals(mUserId, tester.getUserId());
    }

    public void testCreateAuction(){
        Auction testAuction = tester.createAuction();
        // test each element of Auction
        // test Auction was added to database
        // TODO: research how Java communicates with Database.
    }

    public void testDeleteAuction(){
        // should only delete auction when no bids. Test two scenarios: auction with bids and auction without
        // confirm Auction is deleted from database
    }

    public void testReceiveTransaction(){
        // what does receive transaction actually do?
    }

}
