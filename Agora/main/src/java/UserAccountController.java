import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import javax.persistence.EntityExistsException;
import java.util.Date;
import java.util.List;

/**
 * Created by drew on 11/7/14.
 */
public class UserAccountController {
    private Logger LOG = Logger.getLogger(UserAccountController.class);


    public String persistUserAccount(UserAccount user) {
        String result = "";
        Session session = HibernateUtils.getSessionFactory().openSession();
        if (user.getUserId() > 0) {
            result = "Existed";
            throw new EntityExistsException();

        }
        try {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
            result = "true";

        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            result = "Hibernate Failed";

            LOG.warn("Could not insert user account: " + user.toString() + " to database.");
        } catch (EntityExistsException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            result = "Existed";
            LOG.warn("Could not insert user account: " + user.toString() + " to database. User account already exists");
        }
        return result;
    }

    public String deleteUserAccount(UserAccount user) {
        String result = "";
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            result = "true";
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            result = "Could not delete";
            LOG.warn("Could not remove user account: " + user.toString() + " from database.");
        }
        return result;
    }

    public String updateUserAccount(UserAccount user) {
        String result = "";
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            result = "true";
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            result = "Could not update";
            LOG.warn("Could not update user account: " + user.toString() + " in database.");
        }
        return result;
    }

    public UserAccount getUserById(int i) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        UserAccount user = null;
        try {
            session.beginTransaction();
            user = (UserAccount) session.get(UserAccount.class, i);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get user account with id: " + i + " in database.");
        }
        return user;

    }

    public String placeBidOnAuction(UserAccount user, Bid bid, Auction auction) {
        Date today = new Date();
        String result = "Could not place bid";
        if(auction.getListTime().before(today)
            && !auction.getIsEnded()) {
            BidController bidController = new BidController();
            AuctionController auctionController = new AuctionController();
            Bid currentHighestBid = auction.getCurrentHighestBid();
            if (currentHighestBid.getBidAmount().compareTo(bid.getBidAmount()) == -1) {
                //if currentHightestBid is less than proposed bid
                //old bidder before change
                String oldBidderEmail = auction.getCurrentHighestBid().getBidder().getEmail();
                UserAccount oldBidder = auction.getCurrentHighestBid().getBidder();
                bidController.persistBid(bid);
                auction.setCurrentHighestBid(bid);
                auctionController.updateAuction(auction);
                String sellerEmail = auction.getSeller().getEmail();

                Email sellerNotifyEmail = new Email(UserAccount.ADMIN_EMAIL_ADDRESS,UserAccount.ADMIN_PASSWORD,sellerEmail,
                        "Hello "+auction.getSeller().getFirstName()+",\n\nSomeone has bid on your auction!","Someone has bid on your auction: "+auction.toPrettyString()+
                        "with the bid amount: "+auction.getCurrentHighestBid().getBidAmount()+". Click here to go to the auction: \n\nhttp://localhost:3000/auctions/"+auction.getAuctionId());
                Email oldBidderNotifyEmail = new Email(UserAccount.ADMIN_EMAIL_ADDRESS,UserAccount.ADMIN_PASSWORD,oldBidderEmail,
                        "You have been outbid!", "Hello,"+oldBidder.getFirstName()+",\n\n You have been outbid on auction: "+auction.toPrettyString()
                        +". Click here to go to the auction: \n\nhttp://localhost:3000/auctions/"+auction.getAuctionId());
                Email.sendEmail(oldBidderNotifyEmail);
                Email.sendEmail(sellerNotifyEmail);
                result = "Successfully placed bid";
            }
        }
        return result;
    }

    public UserAccount getUserAccountByUsernameAndPasswordHash(String userName, String passwordHash) {
        List<UserAccount> users = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT * FROM useraccounts WHERE username=\'" + userName + "\' AND password_hash=\'" + passwordHash + "\'").addEntity(UserAccount.class);
            users = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get user account with username: "+userName+" and passwordHash: "+passwordHash+" in database.");
        }
        if (users.size() > 0) {
            return users.get(0);
        }
        else return null;
    }

    public String placeBuyItNow(UserAccount user, Auction auction){
        Date today = new Date();
        if(auction.getListTime().before(today)
                && !auction.getIsEnded()) {
            AuctionController auctionController = new AuctionController();
            ShoppingCart cart = user.getShoppingCart();
            auction.setIsEnded(true);
            cart.addAuctionToShoppingCart(user.getUserId(), auction.getAuctionId());
            auctionController.updateAuction(auction);
            Email.notifyUsersOfEndedAuction(auction, auction.getSeller().getEmail(),user.getEmail());
            return "true";
        }
        else {
            return "Failure";
        }
    }

    public UserAccount getSystemUser() {
        UserAccount user = null;
        List<UserAccount> users = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT * FROM useraccounts WHERE username=\'SYSTEM_SUPER_USER\'").addEntity(UserAccount.class);
            users = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get system user account");
        }
        if (users.size() > 0) {
            return users.get(0);
        }
        else return null;
    }
}

