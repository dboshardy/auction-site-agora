import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.EntityExistsException;

/**
 * Created by drew on 11/7/14.
 */
public class BidController {
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(BidController.class);

    public void persistBid(Bid bid) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            bid.setBidId((Integer) session.save(bid));
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not insert account: " + bid.toString() + " to database.");
        }
    }

    public Bid getBidById(int currentHighestBidId) {
        Bid bid = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        if (bid.getBidId() > 0) {
            throw new EntityExistsException();

        }
        try {
            session.beginTransaction();
            bid = (Bid) session.get(Bid.class, currentHighestBidId);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not insert account: " + bid.toString() + " to database.");
        } catch (EntityExistsException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not insert bid: " + bid.toString() + " to database. Bid already exists");
        }
        return bid;
    }
}
