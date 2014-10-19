/**
 * Created by drew on 10/19/14.
 */
public class Flag {
    private FlagType mFlagType;
    private UserAccount mFlaggingUser;
    private Auction mAuctionFlagged;

    public Flag(FlagType flagType, UserAccount flaggingUser, Auction auctionFlagged) {
        mFlagType = flagType;
        mFlaggingUser = flaggingUser;
        mAuctionFlagged = auctionFlagged;
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
}
