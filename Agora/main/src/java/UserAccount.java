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
        //TODO: implement flagAuction


    }

    public void editUserDescription(){
        //TODO: implement editUserDescription()
    }

}
