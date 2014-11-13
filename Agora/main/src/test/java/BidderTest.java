import org.junit.BeforeClass;
import org.junit.Test;

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
        mUserAccount = new UserAccount(12341342);
        tester = new Bidder(mUserAccount);

    }

    @Test
    public void testPlaceBid(){
        Currency currency = Currency.getInstance("USD");
        BigDecimal amount = BigDecimal.valueOf(12.50);
        Auction auction = new Auction("blah",new UserAccount("drew","drew@me.com","alskdfj","Drew","Boshardy","hahahaha"),"this is an auction",amount);


        Bid bid = new Bid(tester,auction,currency,amount);
        assertTrue(tester.placeBid(bid));
    }

}
