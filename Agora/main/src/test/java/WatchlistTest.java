import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

/**
 * Created by thomkel on 10/31/14.
 */
public class WatchlistTest {
    Watchlist tester;
    String mWatchlistName;
    Item[] mWatchlist;

    @BeforeClass
    public void testSetup() {
        mWatchlistName = "Things I Want";
        tester = new Watchlist(new UserAccount(),mWatchlistName);
    }

    @Test
    public void testConstructor() {
        // assertEquals(mUserId, tester.getUserId());
    }

    public void testEditWatchlistName() {
        String watchlistName = "New things for you";
        Watchlist testWatchlist = new Watchlist(watchlistName);
        assertEquals(watchlistName, testWatchlist.getWatchlistName());

        watchlistName = "New things for me";
        testWatchlist.setWatchlistName(watchlistName);

        assertEquals(watchlistName, testWatchlist.getWatchlistName());
    }

    public void testAddItemToWatchlist(){
        //TODO: fix this
        //Item item = new Item();
    }
}

