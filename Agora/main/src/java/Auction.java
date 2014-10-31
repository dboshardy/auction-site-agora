import java.sql.Timestamp;
import java.util.List;

/**
 * Created by Miao Yu on 10/19/14.
 */
public class Auction {

    private Timestamp mTimestamp;
    private List<Bid> mBidList;
    private List<Flag> mFlagList;
    private Seller mSeller;
    public Auction() {
        
    }

    public List<Bid> getBidList(){
        List<Bid> result= mBidList;
        return result;
    }

    public void setTimestamp(Timestamp timestamp){
        mTimestamp = timestamp;
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

    public void setFlag(Flag flag, UserID userID, Timestamp timestamp) {
    }
}
