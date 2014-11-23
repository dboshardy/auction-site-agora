import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 * Created by thomkel on 11/11/14.
 */
public class WatchlistModel {
    private static SessionFactory mSessionFactory;

    public WatchlistModel(){}

    public boolean getWatchlist(Integer watchlistId){
        // create method

        return false;
    }

    public boolean createWatchlist(Watchlist watchlist){
        Session session = mSessionFactory.openSession();
        org.hibernate.Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            watchlist.setWatchlistId((Integer) session.save(watchlist));
            transaction.commit();
            session.close();
            return true;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            return false;
            //LOG.warn("Could not insert user account : {} to database.",user.toString());
        }
    }
    public boolean removeWatchlist(Watchlist watchlist) {
        Session session = mSessionFactory.openSession();
        org.hibernate.Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            session.delete(watchlist);
            transaction.commit();
            session.close();
            return true;
        }
        catch (HibernateException e){
            if(transaction != null){
                transaction.rollback();
            }
            return false;
            //LOG.warn("Could not remove user account : {} to database.",user.toString());
        }
    }
}
