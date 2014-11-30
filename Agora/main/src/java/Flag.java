<<<<<<< HEAD
import java.util.Date;

=======
>>>>>>> code merge and change
/**
 * Created by drew on 10/19/14.
 */
public class Flag {
    private String mFlagType;
    private UserAccount mFlaggingUser;
    private Auction mAuctionFlagged;
<<<<<<< HEAD
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
=======

    public Flag(FlagType flagType, UserAccount flaggingUser, Auction auctionFlagged) {
>>>>>>> code merge and change
        mFlagType = flagType;
        mFlaggingUser = flaggingUser;
        mAuctionFlagged = auctionFlagged;
        mAuctionId = auctionFlagged.getAuctionId();
        mUserId = flaggingUser.getUserId();
    }
<<<<<<< HEAD

<<<<<<< HEAD
=======
>>>>>>> 7f8760962d6d08baed4ced092bb09bc1b39253b1
    public Flag() {
    }

=======
>>>>>>> code merge and change

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
<<<<<<< HEAD

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
=======
        mAuctionFlagged = auctionFlagged;
    }
>>>>>>> code merge and change
}
