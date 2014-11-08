import org.hibernate.Session;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by drew on 10/19/14.
 */
public class UserAccount {
    String mUserName;
    String mEmail;
    Password mPassword;
    Watchlist mWatchlist;
    ShoppingCart mShoppingCart; UserDescription mUserDescription;
    String mUserLocation;
    private int mUserId;
    private String mFirstName;
    private String mLastName;
    private String mDescription;

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

    public UserAccount(int userID) {
        mUserId = userID;
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

    public int getUserID() {
        return mUserId;
    }

    public void setUserID(int  userID) {
        mUserId = userID;
    }
}
