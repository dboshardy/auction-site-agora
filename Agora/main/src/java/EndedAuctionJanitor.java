import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.List;



public class EndedAuctionJanitor extends Janitor {
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(EndedAuctionJanitor.class);
    @Override
    public void run() {
        List<Auction> endedAuctions = null;
        AuctionController auctionController = new AuctionController();
        UserAccountController userAccountController = new UserAccountController();
        ShoppingCart cart = null;
        while(true){
            endedAuctions = getEndedAuctions();
            for(Auction auction : endedAuctions){
                auction.setIsEnded(true);
                auctionController.updateAuction(auction);
                String sellerEmail = auction.getSeller().getEmail();
                String winnerEmail = auction.getCurrentHighestBid().getBidder().getEmail();
                Email.notifyUsersOfEndedAuction(auction, sellerEmail, winnerEmail);
                cart = auction.getCurrentHighestBid().getBidder().getShoppingCart();
                cart.addAuctionToShoppingCart(auction.getCurrentHighestBid().getBidder().getUserId(), auction.getAuctionId());

            }
        }
    }


    public List<Auction> getEndedAuctions(){
        List<Auction> endedAuctions = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT * FROM auctions where end_time>=now()");
            endedAuctions = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            LOG.warn("Could not find any ended auctions");
        }
        return endedAuctions;
    }
}
