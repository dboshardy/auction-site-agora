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

    public Watchlist(UserAccount user, String watchlistName) {
        mUserAccount = user;
        mWatchlistName = watchlistName;
        mWatchlist = new ArrayList<Auction>();

    }

    public Watchlist(UserAccount user) {
        mUserAccount = user;
        if ((mWatchlist = getWatchlist()) != null) {
        } else {
            mWatchlist = new ArrayList<Auction>();
        }

    }

    public Watchlist(String watchlistName, Integer watchlistId){
        mWatchlistName = watchlistName;
        mWatchlist = new ArrayList<Auction>();
        mWatchlistModel = new WatchlistModel();
    }

    public JSONObject createMessageBody(String body){
        return new JSONObject();
    }

    public void addItemToWatchlist(Auction item) {
        // add to database
        // make call to database to get all items in watchlist
    }

    public void deleteAuctionFromWatchlist(Auction auction) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM" + mTableName + "WHERE useraccounts_user_id=" + mUserAccount.getUserId() + " AND " +
                "auctions_auction_id=" + auction.getAuctionId() + " AND watchlist_name=" + this.getWatchlistName());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void deleteWatchlist() {
        // delete from database
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM " + mTableName + " WHERE useraccounts_user_id=" + mUserAccount.getUserId() + " AND watchlist_name=" + this.getWatchlistName());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public String getWatchlistName() {
        return mWatchlistName;
    }

    public void setWatchlistName(String mWatchlistName) {
        this.mWatchlistName = mWatchlistName;
    }

    public ArrayList<Auction> getWatchlist() {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = (SQLQuery) session.createSQLQuery("SELECT * FROM " + mTableName + " WHERE useraccounts_user_id=" + mUserAccount.getUserId() + " AND watchlist_name=\'" + this.getWatchlistName() + "\'");
        List<Object[]> objects = query.list();
        session.getTransaction().commit();
        session.close();

        if (objects.size() > 0) {
            for (Object[] object : objects) {
                WatchlistEntity entity = new WatchlistEntity((Integer)object[0],(Integer)object[1],(String)object[2]);
                mWatchlist.add(entity.getAuctionFromDatabase());
            }
        }
        return mWatchlist;
    }

    public void setWatchlist(ArrayList<Auction> mWatchlist) {
        this.mWatchlist = mWatchlist;
    }

    public void addAuctionToWatchlist(Auction auction) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("INSERT INTO " + mTableName + "(useraccounts_user_id, auctions_auction_id, watchlist_name) VALUES (" + mUserAccount.getUserId() + "," + auction.getAuctionId() + ", \'" + this.getWatchlistName() + "\')");
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();
    }

    public void setWatchlistId(Integer watchlistId) {
        mWatchlistId = watchlistId;
    }
}
