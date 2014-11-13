import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.EntityExistsException;

/**
 * Created by drew on 11/7/14.
 */
public class AuctionController {
    private static final Logger LOG = Logger.getLogger(AuctionController.class);

    public void persistAuction(Auction auction) {
        if (auction.getAuctionId() > 0) {
            throw new EntityExistsException();

        }
        Session session = HibernateUtils.getSessionFactory().openSession();
        BidController bidController = new BidController();
        bidController.persistBid(auction.getCurrentHighestBid());

        try {
            session.beginTransaction();
            auction.setAuctionId((Integer) session.save(auction));
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not insert account: " + auction.toString() + " to database.");
        } catch (EntityExistsException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not insert auction: " + auction.toString() + " to database. Auction already exists");
        }

        auction.setCurrentHighestBidId(auction.getCurrentHighestBid().getBidId());
        this.updateAuction(auction);

    }

    public void updateAuction(Auction auction) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(auction);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not update auction: " + auction.toString() + " in database.");
        }
    }

    public void deleteAuction(Auction auction) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(auction);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not remove auction account: " + auction.toString() + " from database.");
        }
    }
}
