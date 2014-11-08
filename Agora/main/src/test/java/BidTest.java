import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertEquals;


/**
 * Created by drew on 10/19/14.
 */
public class BidTest {
    Bid tester;
    Bidder mBidder;
    Currency mCurrency;
    BigDecimal mAmount;
    Auction mAuction;
    UserAccount mUserAccount;

    @BeforeClass
    public void testSetup() {
        mUserAccount = new UserAccount(1234);
        mBidder = new Bidder(mUserAccount);
        mCurrency = Currency.getInstance("USD");
        mAmount = BigDecimal.valueOf(12.50);
        mAuction = new Auction();
        tester = new Bid(mBidder, mAuction, mCurrency, mAmount);
    }

    @Test
    public void testConstructor() {
        assertEquals(mBidder, tester.getBidder());
        assertEquals(mAuction, tester.getAuction());
        assertEquals(mCurrency, tester.getCurrency());
        assertEquals(mAmount, tester.getBidAmount());
    }
}
