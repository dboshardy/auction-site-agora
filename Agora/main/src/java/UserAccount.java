import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by drew on 10/19/14.
 */
public class UserAccount {
    String mUserName;
    String mEmail;
    String mPassword;
    Map<String, Watchlist> mWatchlists = new HashMap<String, Watchlist>();
    ShoppingCart mShoppingCart;
    UserDescription mUserDescription;
    String mUserLocation;
    private int mUserId;
    private String mFirstName;
    private String mLastName;
    private String mDescription;


    public UserAccount(String userName, String email, String password, String firstName, String lastName, String description) {
        mUserName = userName;
        mEmail = email;
        mPassword = password;
        mFirstName = firstName;
        mLastName = lastName;
        mDescription = description;
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
        auction.setFlag(flag, this.getUserId(), new Timestamp(new Date().getTime()));
        //TODO: implement flagAuction


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
        mWatchlists.put(watchlist.getWatchlistName(),watchlist);
    }

    public ShoppingCart getShoppingCart() {
        return mShoppingCart;
    }

    public UserDescription getUserDescription() {
        return mUserDescription;
    }

    public void setUserDescription(UserDescription userDescription) {
        mUserDescription = userDescription;
    }

    public String getUserLocation() {
        return mUserLocation;
    }

    public void setUserLocation(String userLocation) {
        mUserLocation = userLocation;
    }


    public void addAuctionToShoppingCart(Auction auction) {
        //todo: implement this in ShoppingCart class instead
        this.getShoppingCart().addAuctionToShoppingCart();
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "mUserName='" + mUserName + '\'' +
                ", mEmail='" + mEmail + '\'' +
                ", mPassword='" + mPassword + '\'' +
                ", mWatchlists=" + mWatchlists +
                ", mShoppingCart=" + mShoppingCart +
                ", mUserDescription=" + mUserDescription +
                ", mUserLocation='" + mUserLocation + '\'' +
                ", mUserId=" + mUserId +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mDescription='" + mDescription + '\'' +
                '}';
    }
}
