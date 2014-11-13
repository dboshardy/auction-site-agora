import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by thomkel on 10/19/14.
 */
public class Watchlist {

    private String mWatchlistName;
    private ArrayList<Auction> mWatchlist;
    private Iterator mIterator;
    private UserAccount mUserAccount;
    private final String mTableName = "user_has_watchlist_auctions";

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

    public void addItemToWatchlist(Auction item) {
        // add to database
        // make call to database to get all items in watchlist
    }

    public void deleteItemFromWatchlist(Auction auction) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("DELETE * FROM" + mTableName + "WHERE useraccounts_user_id=" + mUserAccount.getUserId() + " AND " +
                "auctions_auction_id=" + auction.getAuctionId() + " AND watchlist_name=" + this.getWatchlistName());
        session.getTransaction().commit();
        session.close();
    }

    public void deleteWatchlist() {
        // delete from database
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("DELETE * FROM " + mTableName + " WHERE useraccounts_user_id=" + mUserAccount.getUserId() + " AND watchlist_name=" + this.getWatchlistName());
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
        List<Integer> ids = query.list();
        session.getTransaction().commit();
        session.close();

        if (ids.size() > 0) {
            session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            for (Integer id : ids) {
                mWatchlist.add((Auction) session.get(Auction.class, id));
            }
            session.getTransaction().commit();
            session.close();
        }
            return mWatchlist;
    }

    public void setWatchlist(ArrayList<Auction> mWatchlist) {
        this.mWatchlist = mWatchlist;
    }

    public Iterator getIterator() {
        return this.getWatchlist().iterator();
    }

    public void addAuctionToWatchlist(Auction auction) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        session.createSQLQuery("INSERT INTO " + mTableName + " VALUES (" + mUserAccount.getUserId() + "," + auction.getAuctionId() + "," + this.getWatchlistName() + ")");
        session.
        session.getTransaction().commit();
        session.close();
    }
}
