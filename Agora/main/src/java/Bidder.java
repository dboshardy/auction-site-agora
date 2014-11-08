import java.util.List;

/**
 * Created by drew on 10/19/14.
 */
public class Bidder extends UserAccount{

    private UserAccount mUserAccount;
    private List<Bid> mBidList;

    public Bidder(UserAccount userAccount) {
        super(userAccount.getUserId());
        mUserAccount = userAccount;
    }

    public Bidder(int userID) {
        super(userID);
    }

    public boolean placeBid(Bid bid){
        boolean isSuccess = false;

        //TODO: flesh out placeBid()
        return isSuccess;
    }
}
