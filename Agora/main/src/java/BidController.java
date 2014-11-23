import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.persistence.EntityExistsException;
import java.util.List;

/**
 * Created by drew on 11/7/14.
 */
public class BidController {
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(BidController.class);

    public void persistBid(Bid bid) {
        if (bid.getBidId() > 0) {
            throw new EntityExistsException();

        }
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(bid);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not insert bid: " + bid.toString() + " to database.");
        } catch (EntityExistsException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not insert bid: " + bid.toString() + " to database. Bid already exists.");
        }
    }

    public Bid getBidById(int bidId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Bid bid = null;
        try {
            session.beginTransaction();
            bid = (Bid) session.get(Bid.class, bidId);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get bid: " + bidId + " from database.");
        }
        return bid;
    }

    public void updateBid(Bid bid) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(bid);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not update bid: " + bid.toString() + " in database.");
        }
    }

    public List<Bid> getBidHistoryForUser(int userId) {
               Session session = HibernateUtils.getSessionFactory().openSession();
        List<Bid> bids = null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT * FROM bids WHERE bidder_user_id=" + userId).addEntity(Bid.class);
            bids = query.list();
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){

            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get bids list for user : " + userId + " from database.");
        }
        return bids;
    }
}
