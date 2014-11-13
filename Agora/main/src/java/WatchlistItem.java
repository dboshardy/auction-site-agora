/**
 * Created by thomkel on 11/11/14.
 */
public class WatchlistItem {
    private Item mItem;
    private Watchlist mWatchlist;
    private WatchlistItemModel mWatchlistItemModel;

    public WatchlistItem(){}

    public WatchlistItem(Item item, Watchlist watchlist){
        mItem = item;
        mWatchlist = watchlist;
        mWatchlistItemModel = new WatchlistItemModel();
    }

    public String deleteWatchlistItem(){
        boolean deleted = mWatchlistItemModel.deleteWatchlistItem(this);

        if (deleted) {
            // craft JSON reply
        }
        else {
            // craft JSON reply
        }

        return "test";

    }

    public String addWatchlistItem(){
        boolean added = mWatchlistItemModel.addWatchlistItem(this);

        if (added) {
            // craft JSON reply
        }
        else {
            // craft JSON reply
        }

        return "test";
    }

    public Watchlist getWatchlist() {
        return mWatchlist;
    }

    public void setWatchlist(Watchlist mWatchlist) {
        this.mWatchlist = mWatchlist;
    }

    public Item getItem() {
        return mItem;
    }

    public void setItem(Item mItem) {
        this.mItem = mItem;
    }
}
