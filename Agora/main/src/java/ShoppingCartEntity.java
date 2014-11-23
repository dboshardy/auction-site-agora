import java.io.Serializable;

/**
 * Created by drew on 11/14/14.
 */
public class ShoppingCartEntity implements Serializable {
    private int mAuctionId;
    private int mUserId;

    public ShoppingCartEntity(int userId, int auctionId) {
        mAuctionId = auctionId;
        mUserId = userId;
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

    public Auction getAuctionFromDatabase() {
        AuctionController auctionController = new AuctionController();
        return auctionController.getAuctionById(this.getAuctionId());
    }
}
