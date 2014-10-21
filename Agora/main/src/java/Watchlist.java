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

    public void addItemToWatchlist(){}

    public void deleteItemFromWatchlist(){}

    public void deleteWatchlist(){}

    public String getWatchlistName() {
        return mWatchlistName;
    }

    public void setWatchlistName(String mWatchlistName) {
        this.mWatchlistName = mWatchlistName;
    }
}
