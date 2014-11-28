import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Miao Yu on 10/19/14.
 */
public class Auction {

    private Bid mCurrentHighestBid;
    private Timestamp mListTime;
    private Timestamp mEndTime;
    private List<Bid> mBidList;
    private List<Flag> mFlagList;
    private UserAccount mSeller;
    private int mAuctionId;
    private String mDescription;
    private int mCurrentHighestBidId;
    private BigDecimal mBuyItNowPrice;
    private int mSellerId;
    private Category mCategory;
//    private Date ListTime;
//    private Date EndTime;

    public Timestamp getEndTime() {
        return mEndTime;
    }

    public void setEndTime(Timestamp endTime) {
        mEndTime = endTime;
    }

    public Timestamp getListTime() {
        return mListTime;
    }

    public void setListTime(Timestamp listTime) {
        mListTime = listTime;
    }


    public String getDescription() {
        return mDescription;
    }

    public Category getCategory() {
        return mCategory;
    }

    public void setCategory(Category category) {
        mCategory = category;
    }

    public List<Flag> getFlagList() {
        return mFlagList;
    }

    public void setFlagList(List<Flag> flagList) {
        mFlagList = flagList;
    }

    public void setBidList(List<Bid> bidList) {
        mBidList = bidList;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public int getCurrentHighestBidId() {
        return mCurrentHighestBidId;
    }

    public void setCurrentHighestBidId(int currentHighestBidId) {
        mCurrentHighestBidId = currentHighestBidId;
    }

    public List<Bid> getBidList() {
        AuctionController auctionController = new AuctionController();
        return auctionController.getAuctionBids(this);
    }

    public Auction() {
    }

    public Auction(String auctionName, int sellerId, Timestamp listTime, Timestamp endTime, String description,
                   double buyNowPrice, double startBid){
        mAuctionName = auctionName;
        mSellerId = sellerId;
        mListTime = listTime;
        mEndTime = endTime;
        mDescription = description;
        mBuyItNowPrice = new BigDecimal(buyNowPrice);

        UserAccountController seller = new UserAccountController();
        mSeller = seller.getUserById(sellerId);

        Bid initialBid = new Bid(mSeller,this,new BigDecimal(startBid));
        mCurrentHighestBid = initialBid;

    }

    public Auction(String auctionName, int sellerId, String description, Calendar listTime,
                   Calendar endTime, Double buyItNowPrice){
        mAuctionName = auctionName;
        mSellerId = sellerId;
        mDescription = description;
        mEndTime = new Timestamp(endTime.getTime().getTime());
        BigDecimal d = new BigDecimal(buyItNowPrice);
        mBuyItNowPrice = d;
        mListTime = new Timestamp(listTime.getTime().getTime());
        System.out.println(mListTime);
    }

    public Auction(String auctionName, UserAccount seller, String description, GregorianCalendar listTime,
                   GregorianCalendar endTime, BigDecimal buyItNowPrice) {
        mAuctionName = auctionName;
//        mSeller = seller;
        mDescription = description;
//        mListTime = listTime;
//        mEndTime = endTime;
        mBuyItNowPrice = buyItNowPrice;

    }

    public int getSellerId() {
        return mSeller.getUserId();
    }

    public void setSellerId(int sellerId) {
        mSellerId = sellerId;
    }

    public Auction(String auctionName, UserAccount seller, String description, BigDecimal bid,Date endTime) {
        mAuctionName = auctionName;
        mSeller = seller;
        mDescription = description;
        //set date as of now
//        mListTime = new Date();
        Bid initialBid = new Bid(mSeller,this,bid);
        mCurrentHighestBid = initialBid;
//        mEndTime = endTime;
    }

    public Auction(String auctionName, int sellerId, String description, BigDecimal bid,Date endTime) {
        mAuctionName = auctionName;
        mSellerId = sellerId;
        mDescription = description;
        //set date as of now
//        mListTime = new Date();
        Bid initialBid = new Bid(mSeller,this,bid);
        mCurrentHighestBid = initialBid;
//        mEndTime = endTime;
    }

    public UserAccount getSeller() {
        UserAccountController userAccountController = new UserAccountController();
        return  userAccountController.getUserById(this.getSellerId());
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

//    public Calendar getListTime() {
//        return mListTime;
//    }
//
//    public void setListTime(Calendar listTime) {
//        mListTime = listTime;
//    }
//
//    public Calendar getEndTime() {
//        return mEndTime;
//    }
//
//    public void setEndTime(Calendar endTime) {
//        mEndTime = endTime;
//    }
//
//    public void setTimestamp(Calendar timestamp){
//        mListTime = timestamp;
//    }

    public void setSeller(UserAccount mSeller){
        this.mSeller=mSeller;
    }

    public boolean addBid(Bid bid){
        mBidList.add(bid);
        return true;
    }

    public boolean isEnded(){
        boolean result = false;
        Date date = new Date();
        if (mEndTime.after(new Timestamp(date.getTime()))){
            result = true;
        }
       return result;
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

    public void setFlag(Flag flag, int userId) {
        FlagController flagController = new FlagController();
        flagController.persistFlagOnAuction(flag);
    }
}
