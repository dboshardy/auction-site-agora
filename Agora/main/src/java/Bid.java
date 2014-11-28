import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Date;

/**
 * Created by drew on 10/19/14.
 */
public class Bid {
    private Currency mCurrency;
    private UserAccount mBidder;
    private int mBidderId;
    private BigDecimal mBidAmount;
    private Timestamp mTimestamp;
    private Auction mAuction;
    private int mAuctionId;
    private int mBidId;

    public int getAuctionId() {
        return mAuction.getAuctionId();
    }

    public void setAuctionId(int auctionId) {
        mAuctionId = auctionId;
        AuctionController auctionController = new AuctionController();
        mAuction = auctionController.getAuctionById(mAuctionId);
    }


    public Bid() {
    }

    public int getBidId() {
        return mBidId;
    }

    public void setBidId(int bidId) {
        mBidId = bidId;
    }

    public Bid(UserAccount bidder, Auction auction, BigDecimal bidAmount) {
        mBidder = bidder;
        mCurrency = Currency.getInstance("USD");
        mBidAmount = bidAmount;
        Date date = new Date();
        mTimestamp = new Timestamp(date.getTime());
        mAuction = auction;
    }
    public Bid(UserAccount bidder, Auction auction, Currency currency, BigDecimal bidAmount) {
        mBidder = bidder;
        mCurrency = currency;
        mBidAmount = bidAmount;
        Date date = new Date();
        mTimestamp = new Timestamp(date.getTime());
        mAuction = auction;
    }

    public void setAuction(Auction auction) {
        mAuction = auction;
    }

    public void setBidder(UserAccount bidder) {
        mBidder = bidder;
    }

    public String getCurrency() {
        return mCurrency.toString();
    }

    public void setCurrency(String currency) {
        mCurrency = Currency.getInstance(currency);
    }

    public BigDecimal getBidAmount() {
        return mBidAmount;
    }

    public void setBidAmount(BigDecimal bidAmount) {
        mBidAmount = bidAmount;
    }

    public Timestamp getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(Timestamp timestamp) {

        mTimestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Bid{" +
                "mBidder=" + mBidderId +
                ", mCurrency=" + mCurrency +
                ", mBidAmount=" + mBidAmount +
                ", mTimestamp=" + mTimestamp +
                ", mAuction=" + mAuctionId +
                ", mBidId=" + mBidId +
                '}';
    }

    public int getBidderId() {
        return mBidder.getUserId();
    }

    public void setBidderId(int bidderId) {

        mBidderId = bidderId;
        UserAccountController userAccountController = new UserAccountController();
        mBidder = userAccountController.getUserById(mBidderId);
    }

    public UserAccount getBidder() {
        UserAccountController userAccountController = new UserAccountController();
        return userAccountController.getUserById(mBidderId);
    }
}