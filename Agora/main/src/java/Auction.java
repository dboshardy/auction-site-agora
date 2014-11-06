import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Miao Yu on 10/19/14.
 */
public class Auction {

    private Date mListTime;
    private Date mEndTime;
    private List<Bid> mBidList;
    private List<Flag> mFlagList;
    private Seller mSeller;
    private int mAuctionId;
    private int mCurrentHighestBid;
    private String mDesription;
    private BigDecimal mBuyItNowPrice;

    public BigDecimal getBuyItNowPrice() {
        return mBuyItNowPrice;
    }

    public void setBuyItNowPrice(BigDecimal buyItNowPrice) {
        mBuyItNowPrice = buyItNowPrice;
    }

    public String getDesription() {
        return mDesription;
    }

    public void setDesription(String desription) {
        mDesription = desription;
    }

    public int getCurrentHighestBid() {
        return mCurrentHighestBid;
    }

    public void setCurrentHighestBid(int currentHighestBid) {
        mCurrentHighestBid = currentHighestBid;
    }

    public int getAuctionId() {
        return mAuctionId;
    }

    public void setAuctionId(int auctionId) {
        mAuctionId = auctionId;
    }

    public Date getListTime() {
        return mListTime;
    }

    public void setListTime(Date listTime) {
        mListTime = listTime;
    }

    public Date getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Date endTime) {
        mEndTime = endTime;
    }

    public List<Bid> getBidList(){
        List<Bid> result= mBidList;
        return result;
    }

    public void setTimestamp(Date timestamp){
        mListTime = timestamp;
    }

    public Seller getSeller(){
        return mSeller;
    }

    public void setSeller(Seller mSeller){
        this.mSeller=mSeller;
    }

    public boolean addBid(Bid bid){
        mBidList.add(bid);
        return true;
    }

    public boolean isEnded(){
        boolean result = false;
        if (mEndTime.after(new Date())){
            result = true;
        }
       return result;
    }

    public void setFlag(Flag flag, int userID, Date timestamp) {
        //todo: implement this
    }
}
