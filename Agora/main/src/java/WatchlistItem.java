/**
 * Created by thomkel on 11/11/14.
 */
public class WatchlistItem {
    private Auction mAuction;
    private Watchlist mWatchlist;
    private WatchlistItemModel mWatchlistItemModel;

    public WatchlistItem(){}

    public WatchlistItem(Auction auction, Watchlist watchlist){
        mAuction = auction;
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

    public Auction getAuction() {
        return mAuction;
    }

    public void setAuction(Auction auction) {
        this.mAuction = auction;
    }
}
