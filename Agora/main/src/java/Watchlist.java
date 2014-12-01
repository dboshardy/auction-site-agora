import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by thomkel on 10/19/14.
 */
public class Watchlist {

    private WatchlistModel mWatchlistModel;
    private String mWatchlistName;
    private ArrayList<Auction> mWatchlist;
    private Iterator mIterator;
    private UserAccount mUserAccount;
    private final String mTableName = "user_has_watchlist_auctions";
    private Integer mWatchlistId;
    public int mUserId;
    public int mAuctionId;

    public int getUserId() {
        return mUserId;
    }

    public void setUserId(int mUserId) {
        this.mUserId = mUserId;
    }

    public int getAuctionId() {
        return mAuctionId;
    }

    public void setAuctionId(int mAuctionId) {
        this.mAuctionId = mAuctionId;
    }

    public Watchlist(){}

    public Watchlist(UserAccount user, String watchlistName) {
        mUserAccount = user;
        mWatchlistName = watchlistName;
        mWatchlistModel = new WatchlistModel();
        mWatchlist = new ArrayList<Auction>();

    }

    public Watchlist(int user_id, int auction_id){
        mUserId = user_id;
        mAuctionId = auction_id;
        mWatchlistName = "default";

    }

//    public Watchlist(UserAccount user) {
//        mUserAccount = user;
//        if ((mWatchlist = getWatchlist()) != null) {
//        } else {
//            mWatchlist = new ArrayList<Auction>();
//        }
//
//    }

    public Watchlist(String watchlistName, Integer watchlistId){
        mWatchlistName = watchlistName;
        mWatchlist = new ArrayList<Auction>();
        mWatchlistModel = new WatchlistModel();
    }

    public JSONObject createMessageBody(JSONObject body){
        return new JSONObject();
    }

    public void addItemToWatchlist(Auction item) {
        // add to database
        // make call to database to get all items in watchlist
    }

    public String deleteAuctionFromWatchlist(Auction auction) {
        String result="false";
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM" + mTableName + "WHERE useraccounts_user_id=" + mUserAccount.getUserId() + " AND " +
                "auctions_auction_id=" + auction.getAuctionId() + " AND watchlist_name=" + this.getWatchlistName());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        result="true";
        return result;
    }

    public String deleteWatchlist(int user_id, int auction_id) {
        // delete from database
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM " + mTableName + " WHERE useraccounts_user_id=" + user_id + " AND auctions_auction_id=" + auction_id);
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        String result="true";
        return result;
    }

    public String getWatchlistName() {
        return mWatchlistName;
    }

    public void setWatchlistName(String mWatchlistName) {
        this.mWatchlistName = mWatchlistName;
    }

    public Integer getWatchlistId() {
        return mWatchlistId;
    }

    public void setWatchlistId(Integer mWatchlistId) {
        this.mWatchlistId = mWatchlistId;
    }

    public ArrayList<Auction> getWatchlists(int user_id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = (SQLQuery) session.createSQLQuery("SELECT auctions_auction_id FROM " + mTableName + " WHERE useraccounts_user_id=" + user_id);
        List<Integer> ids = query.list();
        session.getTransaction().commit();
        session.close();

        AuctionController auctionController = new AuctionController();
        ArrayList<Auction> auctions = new ArrayList<>();

        if (ids.size() > 0) {
            for (int i : ids) {
//                WatchlistEntity entity = new WatchlistEntity((Integer)object[0],(Integer)object[1],(String)object[2]);
//                mWatchlist.add(entity.getAuctionFromDatabase());
                Auction auction = auctionController.getAuctionById(i);
                auctions.add(auction);
            }
        }
        return auctions;
    }

    public void setWatchlist(ArrayList<Auction> mWatchlist) {
        this.mWatchlist = mWatchlist;
    }

    public String addAuctionToWatchlist(int user_id, int auction_id) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("INSERT INTO " + mTableName + "(useraccounts_user_id, auctions_auction_id, watchlist_name) VALUES (" + user_id + "," + auction_id + ", \'" + this.getWatchlistName() + "\')");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        String result="true";
        return result;
    }

}