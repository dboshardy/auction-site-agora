import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.persistence.EntityExistsException;
import java.util.List;

/**
 * Created by drew on 11/7/14.
 */
public class AuctionController {
    private static final Logger LOG = Logger.getLogger(AuctionController.class);

    public String persistAuction(Auction auction) {
        String result = "";
        if (auction.getAuctionId() > 0) {
            throw new EntityExistsException();

        }
        Session session = HibernateUtils.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            auction.setAuctionId((Integer) session.save(auction));
            session.getTransaction().commit();
            session.close();
            result = "true";

        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not insert account: " + auction.toString() + " to database.");
            result="Unknown Exception";
            LOG.warn(e.getCause().getCause());
            e.printStackTrace();
        } catch (EntityExistsException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not insert auction: " + auction.toString() + " to database. Auction already exists");
            result="Existed";
        }

        BidController bidController = new BidController();
        bidController.persistBid(auction.getNonPersistedBid());
        auction.setCurrentHighestBidId(auction.getNonPersistedBid().getBidId());
        this.updateAuction(auction);
        return result;

    }

    public String updateAuction(Auction auction) {
        String result = "";
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(auction);
            session.getTransaction().commit();
            session.close();
            result = "Successfully Updated Auction";
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not update auction: " + auction.toString() + " in database.");
            result = "Could not Update Auction";
        }
        return result;
    }

    public String deleteAuction(Auction auction) {
        String result="";

        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(auction);
            session.getTransaction().commit();
            session.close();
            result="true";
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not remove auction: " + auction.toString() + " from database.");
            result="";
        }
        return result;
    }

    public Auction getAuctionById(int auctionId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Auction auction = null;
        try {
            session.beginTransaction();
            auction = (Auction) session.get(Auction.class,auctionId);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get auction : " + auction.toString() + " from database.");
        }
        return auction;
    }

    public List<Bid> getAuctionBids(Auction auction) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Bid> bids = null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT * FROM bids WHERE auction_id="+auction.getAuctionId()).addEntity(Bid.class);
            bids = query.list();
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get bid list for auction : " + auction.toString() + " from database.");
        }
        return bids;
    }

    public List<Auction> getAllAuctionsByUserId(int userId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Auction> auctions = null;
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT * FROM auctions WHERE seller_user_id=" + userId).addEntity(Auction.class);
            auctions = query.list();
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){

            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get auction list for user : " + userId + " from database.");
            LOG.warn(e.getCause().getCause());
            e.printStackTrace();
        }
        return auctions;
    }

    public List<Auction> getAuctionByBidId(int bidId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Auction> auction = null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("SELECT * FROM auctions WHERE current_highest_bid_id="+bidId).addEntity(Auction.class);
            List<Auction> auctions = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get auction : " + auction.toString() + " from database.");
        }
        return auction;
    }
}
