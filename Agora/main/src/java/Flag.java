import java.util.Date;

/**
 * Created by drew on 10/19/14.
 */
public class Flag {
    private FlagType mFlagType;
    private UserAccount mFlaggingUser;
    private Auction mAuctionFlagged;
    private int mAuctionId;
    private int mFlagId;

    public Date getDateFlagged() {
        return mDateFlagged;
    }

    public void setDateFlagged(Date dateFlagged) {
        mDateFlagged = dateFlagged;
    }

    private Date mDateFlagged;


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


    public Flag(FlagType flagType, UserAccount flaggingUser, Auction auctionFlagged) {
        mDateFlagged = new Date();
        mFlagType = flagType;
        mFlaggingUser = flaggingUser;
        mAuctionFlagged = auctionFlagged;
    }

    public Flag() {
    }


    public FlagType getFlagType() {
        return mFlagType;
    }

    public void setFlagType(FlagType flagType) {
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
