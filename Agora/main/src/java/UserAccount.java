import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by drew on 10/19/14.
 */
public class UserAccount {
    String mUserName;
    String mEmail;
    String mPassword;
    Watchlist mWatchlist;
    ShoppingCart mShoppingCart; UserDescription mUserDescription;
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

    public void flagAuction(Auction auction,Flag flag){
        auction.setFlag(flag,this.getUserId(), new Timestamp(new Date().getTime()));
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

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public Watchlist getWatchlist() {
        return mWatchlist;
    }

    public void setWatchlist(Watchlist watchlist) {
        mWatchlist = watchlist;
    }

    public ShoppingCart getShoppingCart() {

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.openSession();

        session.beginTransaction();
        session.createQuery("SELECT * FROM UserAccount WHERE user_id="+this.getUserId());

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

    public void addAuctionToWatchlist(Auction auction){

    }

    public void addAuctionToShoppingCart(Auction auction){

    }
}
