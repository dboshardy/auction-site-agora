<<<<<<< HEAD
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

=======
>>>>>>> code merge and change
/**
 * Created by drew on 10/19/14.
 */
public class UserAccount {
    String mUserName;
    String mEmail;
<<<<<<< HEAD
    String mPassword;
    Map<String, Watchlist> mWatchlists = new HashMap<String, Watchlist>();
    ShoppingCart mShoppingCart = new ShoppingCart();
    String mUserLocation;
    private int mUserId;
    private String mFirstName;
    private String mLastName;
    private String mDescription;
    private Date mUserJoinedDate;
    private boolean mIsAdmin = false;

    public boolean getIsAdmin() {
        return mIsAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        mIsAdmin = isAdmin;
    }

    public boolean isAdmin() {
        return mIsAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        mIsAdmin = isAdmin;
    }


    public Date getUserJoinedDate() {
        return mUserJoinedDate;
    }

    public void setUserJoinedDate(Date userJoinedDate) {
        mUserJoinedDate = userJoinedDate;
    }


    public UserAccount(String userName, String email, String password, String firstName, String lastName, String description) {
        mUserName = userName;
        mEmail = email;
        mPassword = password;
        mFirstName = firstName;
        mLastName = lastName;
        mDescription = description;
        mUserJoinedDate = new Date();

    }

    public UserAccount(int id, String userName, String email, String password, String firstName, String lastName, String description) {
        mUserId = id;
        mUserName = userName;
        mEmail = email;
        mPassword = password;
        mFirstName = firstName;
        mLastName = lastName;
        mDescription = description;
        mUserJoinedDate = new Date();

    }

    public boolean userHasCurrentHighestBid() {
        boolean result = false;
        BidController bidController = new BidController();
        List<Bid> userBids = bidController.getBidHistoryForUser(this.getUserId());
        AuctionController auctionController = new AuctionController();
        List<Auction> auctions = null;
        for (Bid bid : userBids) {
            auctions = auctionController.getAuctionByBidId(bid.getBidId());
            if (auctions != null) {
                for (Auction auction : auctions) {
                    if (auction != null) {
                        result = true;
                    }
                }

            }
        }

        return result;
    }

    public boolean userHasActiveAuction() {
        boolean result = false;
        AuctionController auctionController = new AuctionController();

        List<Auction> userAuctions = auctionController.getAllAuctionsByUserId(this.getUserId());
        for (Auction auction : userAuctions) {
            if (!auction.isEnded()) {
                result = true;
            }
        }
        return result;
    }

    public UserAccount() {
    }

    public UserAccount(int userID) {
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int userId) {
        mUserId = userId;
    }

    public void flagAuction(Auction auction, Flag flag) {
        auction.setFlag(flag, this.getUserId());
    }

    public void editUserDescription(String newDesc) {
        UserAccountController controller = new UserAccountController();
        this.setDescription(newDesc);
        controller.updateUserAccount(this);
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public Watchlist getWatchlist(String name) {
        return mWatchlists.get(name);
    }

    public void addWatchlist(Watchlist watchlist) {
        mWatchlists.put(watchlist.getWatchlistName(), watchlist);
    }

    public ShoppingCart getShoppingCart() {
        return mShoppingCart;
    }

    public String getUserLocation() {
        return mUserLocation;
    }

    public void setUserLocation(String userLocation) {
        mUserLocation = userLocation;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "mUserName='" + mUserName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mWatchlists=" + mWatchlists +
                ", mShoppingCart=" + mShoppingCart +
                ", mUserLocation='" + mUserLocation + '\'' +
                ", mUserId=" + mUserId +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                ", isAdmin='" + mIsAdmin + '\'' +
                '}';
    }
=======
    Password mPassword;
    //TODO: define "history" for user account
    Watchlist mWatchlist;
    ShoppingCart mShoppingCart;
    UserDescription mUserDescription;
    String mUserLocation;
    UserID mUserID;

    public UserAccount(UserID userID) {
        mUserID = userID;
    }

    public UserAccount(String userName, String email, Password password, Watchlist watchlist, ShoppingCart shoppingCart, UserDescription userDescription, String userLocation) {
        mUserName = userName;
        mEmail = email;
        mPassword = password;
        mWatchlist = watchlist;
        mShoppingCart = shoppingCart;
        mUserDescription = userDescription;
        mUserLocation = userLocation;
    }

    public void flagAuction(Auction auction,Flag flag){
        //TODO: implement flagAuction


    }

    public void editUserDescription(){
        //TODO: implement editUserDescription()
    }

>>>>>>> code merge and change
}
