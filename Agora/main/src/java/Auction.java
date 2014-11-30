<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
import org.json.JSONArray;
import org.json.JSONObject;

=======
>>>>>>> 7f8760962d6d08baed4ced092bb09bc1b39253b1
=======
>>>>>>> 981e7d85c0c4653c1ee943e9b064a28b3bf3fc6b
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * Created by Miao Yu on 10/19/14.
 */
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
public class Auction extends Message{
=======
public class Auction {
>>>>>>> 7f8760962d6d08baed4ced092bb09bc1b39253b1
=======
public class Auction {
>>>>>>> 981e7d85c0c4653c1ee943e9b064a28b3bf3fc6b

    private Bid mCurrentHighestBid;
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
    private Category mCategory;
    private boolean mIsEnded;
    private int mCategoryId;


    public void setEnded(boolean isEnded) {
        mIsEnded = isEnded;
    }

    public int getCategoryId() {
        return mCategoryId;
    }
    public void setCategoryId(int id){
        mCategoryId = id;
    }

    public String getDescription() {
        return mDescription;
    }

    public Category getCategory() {
        CategoryController categoryController = new CategoryController();
        return categoryController.getCategoryById(mCategoryId);
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
        //         get null pointer exception when running getAllAuctionsByUserId

//        return mCurrentHighestBid.getBidId();
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

    public Auction(String auctionName, int sellerId, Date listTime,
                   Date endTime, String description, Double buyItNowPrice, Double bidPrice){
        mAuctionName = auctionName;
        mSellerId = sellerId;
        mDescription = description;
        mEndTime = endTime;
        BigDecimal d = new BigDecimal(buyItNowPrice);
        mBuyItNowPrice = d;
        mListTime = listTime;

        UserAccountController seller = new UserAccountController();
        mSeller = seller.getUserById(mSellerId);

        Bid initialBid = new Bid(mSeller,this,new BigDecimal(bidPrice));
        mCurrentHighestBid = initialBid;
    }

    public Auction(String auctionName, int sellerId, Date listTime,
                   Date endTime, String description, Double bidPrice){
        mAuctionName = auctionName;
        mSellerId = sellerId;
        mDescription = description;
        mEndTime = endTime;
        mListTime = listTime;

        UserAccountController seller = new UserAccountController();
        mSeller = seller.getUserById(mSellerId);

        Bid initialBid = new Bid(mSeller,this,new BigDecimal(bidPrice));
        mCurrentHighestBid = initialBid;
    }

    public int getSellerId() {
//         get null pointer exception when running getAllAuctionsByUserId
//        return mSeller.getUserId();
        return mSellerId;
    }

    public void setSellerId(int sellerId) {
        mSellerId = sellerId;
    }

    public Auction(String auctionName, UserAccount seller, String description, BigDecimal bid,Date endTime) {
        mAuctionName = auctionName;
        mSeller = seller;
        mDescription = description;
//        this.mCategoryId = categoryId;
        //set date as of now
//        mListTime = new Date();
//        this.mCategoryId = categoryId;
        mListTime = new Date();
        Bid initialBid = new Bid(mSeller,this,bid);
        mCurrentHighestBid = initialBid;
        mEndTime = endTime;
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
<<<<<<< HEAD
<<<<<<< HEAD
=======
public class Auction {

    private String timestamp;
    private ArrayList<Bid> bidList;
    private Seller seller;
    public Auction() {
        
    }

    public ArrayList<Bid> getBidList(){
        ArrayList<Item> result= bidList;
        return result;
    }

    public void setTimestamp(String time){
        timestamp=time;
    }

    public Seller getSeller(){
        return seller;
    }

    public void setSeller(Seller seller){
        this.seller=seller;
    }

    public boolean addBid(Bid bid){
        bidList.add(bid);
        return true;
    }

>>>>>>> code merge and change
}
=======

    public boolean getIsEnded(){
        return mIsEnded;
    }

    public void setIsEnded(boolean isEnded) {
        mIsEnded = isEnded;
    }
}
>>>>>>> 7f8760962d6d08baed4ced092bb09bc1b39253b1
=======

    public boolean getIsEnded(){
        return mIsEnded;
    }

    public void setIsEnded(boolean isEnded) {
        mIsEnded = isEnded;
    }

    public String toPrettyString() {
            return  ", mAuctionName='" + mAuctionName + '\'' + "\n" +
                    "mListTime=" + mListTime + "\n" +
                    ", mEndTime=" + mEndTime + "\n" +
                    ", mDescription='" + mDescription + '\'' + "\n" +
                    ", mBuyItNowPrice=" + mBuyItNowPrice +"\n";
        }
}
>>>>>>> 981e7d85c0c4653c1ee943e9b064a28b3bf3fc6b
