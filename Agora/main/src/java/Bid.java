import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Currency;
import java.util.Date;

/**
 * Created by drew on 10/19/14.
 */
public class Bid {
    private Bidder mBidder;
    private Currency mCurrency;
    private BigDecimal mBidAmount;
    private Timestamp mTimestamp;
    private Auction mAuction;

    public Bid(Bidder bidder, Auction auction, Currency currency, BigDecimal bidAmount) {
        mBidder = bidder;
        mCurrency = currency;
        mBidAmount = bidAmount;
        Date date = new Date();
        mTimestamp = new Timestamp(date.getTime());
        mAuction = auction;
    }

    public Auction getAuction() {
        return mAuction;
    }

    public void setAuction(Auction auction) {
        mAuction = auction;
    }

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
}
