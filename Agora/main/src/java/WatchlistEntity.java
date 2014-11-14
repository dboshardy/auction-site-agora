import java.io.Serializable;

/**
 * Created by drew on 11/14/14.
 */
public class WatchlistEntity implements Serializable {
    private String mWatchlistName;
    private int mAuctionId;
    private int mUserId;

    public WatchlistEntity(int userId, int auctionId, String watchlistName) {
        mWatchlistName = watchlistName;
        mAuctionId = auctionId;
        mUserId = userId;
    }

    public String getWatchlistName() {
        return mWatchlistName;
    }

    public void setWatchlistName(String watchlistName) {
        mWatchlistName = watchlistName;
    }

    public int getAuctionId() {
        return mAuctionId;
    }

    public void setAuctionId(int auctionId) {
        mAuctionId = auctionId;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }
    public Auction getAuctionFromDatabase(){
        AuctionController auctionController = new AuctionController();
        return auctionController.getAuctionById(this.getAuctionId());
    }
}
