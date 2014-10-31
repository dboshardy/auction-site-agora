import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by drew on 10/19/14.
 */
public class UserAccount {
    String mUserName;
    String mEmail;
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
        auction.setFlag(flag,this.getUserID(), new Timestamp(new Date().getTime()));
        //TODO: implement flagAuction


    }

    public void editUserDescription(){
        //TODO: implement editUserDescription()
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

    public Password getPassword() {
        return mPassword;
    }

    public void setPassword(Password password) {
        mPassword = password;
    }

    public Watchlist getWatchlist() {
        return mWatchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        mWatchlist = watchlist;
    }

    public ShoppingCart getShoppingCart() {
        return mShoppingCart;
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        mShoppingCart = shoppingCart;
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

    public UserID getUserID() {
        return mUserID;
    }

    public void setUserID(UserID userID) {
        mUserID = userID;
    }
}
