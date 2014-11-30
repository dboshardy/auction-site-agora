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

    public String createWatchlist(Watchlist watchlist){
        Session session = mSessionFactory.openSession();
        org.hibernate.Transaction transaction = null;
        String result = "";
        try {
            transaction = session.beginTransaction();
            watchlist.setWatchlistId((Integer) session.save(watchlist));
            transaction.commit();
            session.close();
            result = "true";
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            result = "Could not create watchlist";
            //LOG.warn("Could not insert user account : {} to database.",user.toString());
        }
        return result;
    }
    public String removeWatchlist(Watchlist watchlist) {
        Session session = mSessionFactory.openSession();
        org.hibernate.Transaction transaction = null;
        String result = "";
        try{
            transaction = session.beginTransaction();
            session.delete(watchlist);
            transaction.commit();
            session.close();
            result = "Successfully removed watchlist";
        }
        catch (HibernateException e){
            if(transaction != null){
                transaction.rollback();
            }
            result = "Could not remove watchlist";
//            LOG.warn("Could not remove watchlist: "+ watchlist.toString()+" from database.");
        }
        return result;
    }
}
