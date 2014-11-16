import java.util.ArrayList;

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

    public Watchlist(){
        mWatchlistName = "New Watchlist";
        mWatchlist = new ArrayList<Item>();
        mWatchlistModel = new WatchlistModel();
    }

    public Watchlist(UserAccount user, String watchlistName) {
        mUserAccount = user;
        mWatchlistName = watchlistName;
        mWatchlistModel = new WatchlistModel();
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

    public JSONObject createMessageBody(JSONObject body){
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

    public Integer getWatchlistId() {
        return mWatchlistId;
    }

    public void setWatchlistId(Integer mWatchlistId) {
        this.mWatchlistId = mWatchlistId;
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

    public void setWatchlist(ArrayList<Item> mWatchlist) {
        this.mWatchlist = mWatchlist;
    }
}
