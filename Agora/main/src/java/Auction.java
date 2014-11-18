import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by Miao Yu on 10/19/14.
 */
public class Auction {

    private Bid mCurrentHighestBid = null;
    private Date mListTime;
    private Date mEndTime;
    private List<Bid> mBidList;
    private List<Flag> mFlagList;
    private UserAccount mSeller;
    private int mAuctionId;
    private String mDescription;
    private int mCurrentHighestBidId;
    private BigDecimal mBuyItNowPrice;
    private int mSellerId;

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getCurrentHighestBidId() {
        return mCurrentHighestBid.getBidId();
    }

    public void setCurrentHighestBidId(int currentHighestBidId) {
        mCurrentHighestBidId = currentHighestBidId;
    }

    public List<Bid> getBidList() {
        AuctionController auctionController = new AuctionController();
        return auctionController.getAuctionBids(this);
    }

    //todo: implement buy it now price
    public Auction() {
    }

    public int getSellerId() {
        return mSeller.getUserId();
    }

    public void setSellerId(int sellerId) {
        mSellerId = sellerId;
    }

    public Auction(String auctionName, UserAccount seller, String description, BigDecimal bid) {
        mAuctionName = auctionName;
        mSeller = seller;
        mDescription = description;
        //set date as of now
        mListTime = new Date();
        Bid initialBid = new Bid(mSeller,this,bid);
        mCurrentHighestBid = initialBid;
    }

    public Auction(String auctionName, int sellerId, String description, BigDecimal bid) {
        mAuctionName = auctionName;
        mSellerId = sellerId;
        mDescription = description;
        //set date as of now
        mListTime = new Date();
        Bid initialBid = new Bid(mSeller,this,bid);
        mCurrentHighestBid = initialBid;
    }
    public UserAccount getSeller() {
        UserAccountController userAccountController = new UserAccountController();
        return  userAccountController.getUserById(this.getSellerId());
    }

    public void setSeller(UserAccount seller) {
        mSeller = seller;
    }

    public String getAuctionName() {
        return mAuctionName;
    }

    public void setAuctionName(String auctionName) {
        mAuctionName = auctionName;
    }

    private String mAuctionName;


    public BigDecimal getBuyItNowPrice() {
        return mBuyItNowPrice;
    }

    public void setBuyItNowPrice(BigDecimal buyItNowPrice) {
        mBuyItNowPrice = buyItNowPrice;
    }

    public Bid getNonPersistedBid(){
        return mCurrentHighestBid;
    }
    public Bid getCurrentHighestBid() {
        BidController bidController = new BidController();
        return bidController.getBidById(this.getCurrentHighestBidId());
    }

    public void setCurrentHighestBid(Bid currentHighestBid) {
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

    public void setTimestamp(Date timestamp){
        mListTime = timestamp;
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

    @Override
    public String toString() {
        return "Auction{" +
                "mListTime=" + mListTime +
                ", mEndTime=" + mEndTime +
                ", mBidList=" + mBidList +
                ", mFlagList=" + mFlagList +
                ", mSeller=" + mSeller +
                ", mAuctionId=" + mAuctionId +
                ", mCurrentHighestBidId=" + mCurrentHighestBidId +
                ", mDescription='" + mDescription + '\'' +
                ", mBuyItNowPrice=" + mBuyItNowPrice +
                ", mSellerId=" + mSellerId +
                ", mAuctionName='" + mAuctionName + '\'' +
                '}';
    }

}
