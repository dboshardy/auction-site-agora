import java.util.Date;

/**
 * Created by drew on 10/19/14.
 */
public class Flag {
    private String mFlagType;
    private UserAccount mFlaggingUser;
    private Auction mAuctionFlagged;
    private int mAuctionId;
    private int mFlagId;
    private int mUserId;
    private Date mDateFlagged;

    public Date getDateFlagged() {
        return mDateFlagged;
    }

    public void setDateFlagged(Date dateFlagged) {
        mDateFlagged = dateFlagged;
    }



    public int getAuctionId() {
        return mAuctionId;
    }

    public void setAuctionId(int auctionId) {
        mAuctionId = auctionId;
    }

    public int getFlagId() {
        return mFlagId;
    }

    public void setFlagId(int flagId) {
        mFlagId = flagId;
    }


//    public Flag(FlagType flagType, UserAccount flaggingUser, Auction auctionFlagged) {
//        mDateFlagged = new Date();
//        mFlagType = flagType;
//        mFlaggingUser = flaggingUser;
//        mAuctionFlagged = auctionFlagged;
//    }

    public Flag(String flagType, UserAccount flaggingUser, Auction auctionFlagged) {
        mDateFlagged = new Date();
        mFlagType = flagType;
        mFlaggingUser = flaggingUser;
        mAuctionFlagged = auctionFlagged;
        mAuctionId = auctionFlagged.getAuctionId();
        mUserId = flaggingUser.getUserId();
    }
    public Flag() {
    }


    public String getFlagType() {
        return mFlagType;
    }

    public void setFlagType(String flagType) {
        mFlagType = flagType;
    }

    public UserAccount getFlaggingUser() {
        return mFlaggingUser;
    }

    public void setFlaggingUser(UserAccount flaggingUser) {
        mFlaggingUser = flaggingUser;
    }

    public Auction getAuctionFlagged() {
        return mAuctionFlagged;
    }

    public int getmUserId() {
        return mUserId;
    }

    public void setmUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public void setAuctionFlagged(Auction auctionFlagged) {

        mAuctionFlagged = auctionFlagged;
    }

    @Override
    public String toString() {
        return "Flag{" +
                "mFlagType=" + mFlagType +
                ", mAuctionId=" + mAuctionId +
                ", mFlagId=" + mFlagId +
                ", mFlaggingUser=" + mFlaggingUser.getUserId() +
                '}';
    }
}
