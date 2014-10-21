import org.junit.BeforeClass;
import org.junit.Test;

import javax.jws.soap.SOAPBinding;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertTrue;

/**
 * Created by drew on 10/19/14.
 */
public class BidderTest {

    Bidder tester;

    UserAccount mUserAccount;
    Bid mBid;


    @BeforeClass
    public void testSetup(){
        mUserAccount = new UserAccount();
        tester = new Bidder(mUserAccount);

    }

    @Test
    public void testPlaceBid(){
        Currency currency = Currency.getInstance("USD");
        Auction auction = new Auction();
        BigDecimal amount = BigDecimal.valueOf(12.50);


        Bid bid = new Bid(tester,auction,currency,amount);
        assertTrue(tester.placeBid(bid));
    }
}
