import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;

/**
 * Created by drew on 10/19/14.
 */
public class FlagTest {
    Flag tester;
    Auction mAuction;
    UserAccount mUser;


    @BeforeClass
    public void testSetup() {
        mUser = new UserAccount(345345);
        Auction auction = new Auction("blah",new UserAccount("drew","drew@me.com","alskdfj","Drew","Boshardy","hahahaha"),"this is an auction",new BigDecimal(1234.3));
        FlagType flagType = FlagType.FAKE_AUCTION;
        tester = new Flag(flagType, mUser, mAuction);
    }

    @Test
    public void testGetFlagType() {
        assertEquals(FlagType.FAKE_AUCTION, tester.getFlagType());
    }

    @Test
    public void testGetAuction() {
        assertEquals(mAuction, tester.getAuctionFlagged());
    }

    @Test
    public void testGetUser() {
        assertEquals(mUser, tester.getFlaggingUser());
    }

    @Test
    public void testSetFlagType() {
        tester.setFlagType(FlagType.ILLEGAL_ITEM);
        assertEquals(FlagType.ILLEGAL_ITEM, tester.getFlagType());
        assertNotSame(FlagType.FAKE_AUCTION, tester.getFlagType());
    }

    @Test
    public void testSetAuction() {
        Auction auction2 = new Auction("blah",new UserAccount("drew","drew@me.com","alskdfj","Drew","Boshardy","hahahaha"),"this is an auction",new BigDecimal(2039.42));
        tester.setAuctionFlagged(auction2);
        assertEquals(auction2, tester.getAuctionFlagged());
        assertNotSame(mAuction, tester.getAuctionFlagged());
    }

    @Test
    public void testSetUser() {
        UserAccount user2 = new UserAccount(5434534);
        tester.setFlaggingUser(user2);
        assertEquals(user2, tester.getFlaggingUser());
        assertNotSame(mUser, tester.getFlaggingUser());
    }


    @AfterClass
    public void testCleanup() {

    }


}
