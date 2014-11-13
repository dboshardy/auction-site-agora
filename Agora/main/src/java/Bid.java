import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Date;

/**
 * Created by drew on 10/19/14.
 */
public class Bid {
    private UserAccount mBidder;
    private Currency mCurrency;
    private BigDecimal mBidAmount;
    private Timestamp mTimestamp;
    private Auction mAuction;
    private int mBidId;

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

    public void setBidder(Bidder bidder) {
        mBidder = bidder;
    }

    public Currency getCurrency() {
        return mCurrency;
    }

    public void setCurrency(Currency currency) {
        mCurrency = currency;
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
                "mBidder=" + mBidder +
                ", mCurrency=" + mCurrency +
                ", mBidAmount=" + mBidAmount +
                ", mTimestamp=" + mTimestamp +
                ", mAuction=" + mAuction +
                ", mBidId=" + mBidId +
                '}';
    }
}
