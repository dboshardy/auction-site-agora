import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Date;

/**
 * Created by drew on 10/19/14.
 */
public class Bid {
<<<<<<< HEAD
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
=======
    private Bidder mBidder;
    private Currency mCurrency;
    private BigDecimal mBidAmount;
    private Timestamp mTimestamp;
    private Auction mAuction;

    public Bid(Bidder bidder, Auction auction, Currency currency, BigDecimal bidAmount) {
>>>>>>> code merge and change
        mBidder = bidder;
        mCurrency = currency;
        mBidAmount = bidAmount;
        Date date = new Date();
        mTimestamp = new Timestamp(date.getTime());
        mAuction = auction;
    }

<<<<<<< HEAD
=======
    public Auction getAuction() {
        return mAuction;
    }

>>>>>>> code merge and change
    public void setAuction(Auction auction) {
        mAuction = auction;
    }

<<<<<<< HEAD
    public void setBidder(UserAccount bidder) {
        mBidder = bidder;
    }

    public String getCurrency() {
        return mCurrency.toString();
    }

    public void setCurrency(String currency) {
        mCurrency = Currency.getInstance(currency);
=======
    public Bidder getBidder() {
        return mBidder;
    }

    public void setBidder(Bidder bidder) {
        mBidder = bidder;
    }

    public Currency getCurrency() {
        return mCurrency;
    }

    public void setCurrency(Currency currency) {
        mCurrency = currency;
>>>>>>> code merge and change
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
<<<<<<< HEAD

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
=======
        mTimestamp = timestamp;
    }
>>>>>>> code merge and change
}
