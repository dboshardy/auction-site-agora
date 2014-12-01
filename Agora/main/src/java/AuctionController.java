import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.ArrayList;

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
            result = "true";
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
            result="Could not delete auction";
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
    public List<Auction> getAllAuctionsByEndLatestWithLimit(int limit){
        //returns ending soonest
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Auction> auctions = null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("SELECT * FROM auctions WHERE is_ended=FALSE BY end_time DESC LIMIT "+limit).addEntity(Auction.class);
            auctions = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get auctions from database.");
        }
        return auctions;
    }
    public List<Auction> getAllAuctionsByEndingSoonestWithLimit(int limit){
        //returns ending soonest
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Auction> auctions = null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("SELECT * FROM auctions WHERE is_ended=FALSE BY end_time ASC LIMIT "+limit).addEntity(Auction.class);
            auctions = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get auctions from database.");
        }
        return auctions;
    }
    public List<Auction> getAllAuctionsByKeyword(String keyword){
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Auction> auctions = null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("SELECT * FROM auctions WHERE auctions.auction_name ILIKE \'%"+keyword+"%\' OR auctions.auction_description ILIKE \'%"+keyword+"%\'").addEntity(Auction.class);
            auctions = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get auctions matching the keyword: "+keyword+" from database.");
        }
        return auctions;

    }
    public List<Auction> getAllAuctionsByCategory(int categoryId){
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Auction> auctions = null;
        try {
            session.beginTransaction();
            Query query = session.createSQLQuery("SELECT * FROM auctions WHERE category_id="+categoryId).addEntity(Auction.class);
            auctions = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get auctions matching the category: "+categoryId+" from database.");
        }
        return auctions;
    }
    public ArrayList<Auction> getAllActiveAuctionsBidOnForUser(UserAccount user){
        Session session = HibernateUtils.getSessionFactory().openSession();
        ArrayList<Auction> auctions = new ArrayList<>();
        BidController bidController = new BidController();
        AuctionController auctionController = new AuctionController();
        List<Bid> bids = bidController.getBidHistoryForUser(user.getUserId());
        for(Bid bid : bids){
            Auction auction = auctionController.getAuctionById(bid.getAuctionId());
            if(!auction.getIsEnded()){
//                    && auction.getCurrentHighestBid().equals(bid)){
               auctions.add(auction);
            }
        }
        return auctions;

    }
}
