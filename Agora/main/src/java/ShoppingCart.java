import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Miao Yu on 10/19/14.
 */
public class ShoppingCart {


    private static final org.apache.log4j.Logger LOG = Logger.getLogger(ShoppingCart.class);
    private String mTableName = "user_has_shoppingcart_auctions";
    private ArrayList<Auction> mAuctions;

    public ShoppingCart() {
        
    }

    public ArrayList<Auction> getShoppingCart(int user_id){
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Object[]> objects = null;
        try {
            session.beginTransaction();
            SQLQuery query = (SQLQuery) session.createSQLQuery("SELECT * FROM " + mTableName + " WHERE useraccounts_user_id=" + user_id);
            objects = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e ){

        }

        if (objects.size() > 0) {
            for (Object[] object : objects) {
                ShoppingCartEntity entity = new ShoppingCartEntity((Integer)object[0],(Integer)object[1]);
                mAuctions.add(entity.getAuctionFromDatabase());
            }
        }
        return mAuctions;
    }

    public String removeAuctionFromCart(Auction auction) {
        String result="";
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("DELETE FROM " + mTableName + " WHERE auction_id=" + auction.getAuctionId());
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            result = "Successfully removed auction from cart";
        } catch (HibernateException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            result = "Could not remove auction from cart";
        }

        session.beginTransaction();
        result="true";
        return result;
    }

    public String addAuctionToShoppingCart(int userId, int auctionId) {
        String result;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("INSERT INTO " + mTableName + "(useraccounts_user_id, auctions_auction_id) VALUES (" + userId + "," + auctionId + ")");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
            result = "Success";
        } catch (HibernateException e){
            if(session.getTransaction() != null){
                session.getTransaction().rollback();
            }
            LOG.warn("Could not add auction: "+auctionId+" to shopping cart: "+this.toString());
            result = "Failure";
        }
        return result;
    }
}
