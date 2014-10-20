/**
 * Created by thomkel on 10/19/14.
 */
public class Watchlist {

    private String mWatchlistName;
    private Item[] mWatchlist;

    public Watchlist(String watchlistName){
        mWatchlistName = watchlistName;
        mWatchlist = new Item[0];
    }

    public Watchlist(String watchlistName, Item[] watchlist){
        mWatchlistName = watchlistName;
        mWatchlist = watchlist;
    }

    public void addItemToWatchlist(){}

    public void deleteItemFromWatchlist(){}

    public String getWatchlistName() {
        return mWatchlistName;
    }

    public void setWatchlistName(String mWatchlistName) {
        this.mWatchlistName = mWatchlistName;
    }
}
