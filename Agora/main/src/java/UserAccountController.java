import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.EntityExistsException;

/**
 * Created by drew on 11/7/14.
 */
public class UserAccountController {
    private Logger LOG = Logger.getLogger(UserAccountController.class);
    private static SessionFactory mSessionFactory;


    public String persistUserAccount(UserAccount user) {
        String result="";
        Session session = HibernateUtils.getSessionFactory().openSession();
        if (user.getUserId() > 0) {
            result="Existed";
            throw new EntityExistsException();

        }
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            result="true";

        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            result="Hibernate Failed";

            LOG.warn("Could not insert user account: " + user.toString() + " to database.");
        } catch (EntityExistsException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            result="Existed";
            LOG.warn("Could not insert user account: " + user.toString() + " to database. User account already exists");
        }
        return result;
    }

    public String deleteUserAccount(UserAccount user) {
        String result= "";
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            result="true";
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            result="Could not delete";
            LOG.warn("Could not remove user account: " + user.toString() + " from database.");
        }
        return result;
    }

    public String updateUserAccount(UserAccount user) {
        String result="";
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            result="true";
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            result="Could not update";
            LOG.warn("Could not update user account: " + user.toString() + " in database.");
        }
        return result;
    }

    public UserAccount getUserById(int i) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        return (UserAccount) session.get(UserAccount.class, i);
    }

    public void placeBidOnAuction(UserAccount user, Bid bid, Auction auction){
        BidController bidController = new BidController();
        AuctionController auctionController = new AuctionController();
        Bid currentHighestBid = auction.getCurrentHighestBid();
        if(currentHighestBid.getBidAmount().compareTo(bid.getBidAmount()) == -1) {
            //if currentHightestBid is less than proposed bid
            bidController.persistBid(bid);
            auction.setCurrentHighestBid(bid);
            auctionController.updateAuction(auction);
        }


    }
}
