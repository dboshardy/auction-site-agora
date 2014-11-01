import java.util.ArrayList;

/**
 * Created by thomkel on 10/19/14.
 */
public class Watchlist {

    private String mWatchlistName;
    private ArrayList<Item> mWatchlist;

    public Watchlist(String watchlistName){
        mWatchlistName = watchlistName;
        mWatchlist = new ArrayList<Item>();
    }

    public void addItemToWatchlist(Item item){
        // add to database
        // make call to database to get all items in watchlist
    }

    public void deleteItemFromWatchlist(){
        // delete from database
        // make call to database to get all items in watchlist
    }

    public void deleteWatchlist(){
        // delete from database
    }

    public String getWatchlistName() {
        return mWatchlistName;
    }

    public void setWatchlistName(String mWatchlistName) {
        this.mWatchlistName = mWatchlistName;
    }

    public ArrayList<Item> getWatchlist() {
        // make call to database to get items on watchlist

        return mWatchlist;
    }

    public void setWatchlist(ArrayList<Item> mWatchlist) {
        this.mWatchlist = mWatchlist;
    }
}
