<<<<<<< HEAD
=======
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.ArrayList;
>>>>>>> 8bcff1f6d64425ee33e6dfae53ca10deac21973e
import java.util.List;

/**
 * Created by Miao Yu on 10/19/14.
 */
public class ShoppingCart {

<<<<<<< HEAD
    private ArrayList<Auction> cart;
=======

    private String mTableName = "user_has_shoppingcart_auctions";
    private ArrayList<Auction> mShoppingCart;

>>>>>>> 8bcff1f6d64425ee33e6dfae53ca10deac21973e
    public ShoppingCart() {
        
    }

<<<<<<< HEAD
    public ArrayList<Auction> getCart(){
        
        return cart;
    }

    public void putToCart(Auction auction){
        cart.add(auction);
    }

    public boolean delete(Auction auction){
        cart.remove(auction);
        return true;
=======
    public ArrayList<Auction> getShoppingCart(UserAccount user){
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = (SQLQuery) session.createSQLQuery("SELECT * FROM " + mTableName + " WHERE useraccounts_user_id=" + user.getUserId());
        List<Object[]> objects = query.list();
        session.getTransaction().commit();
        session.close();

        if (objects.size() > 0) {
            for (Object[] object : objects) {
                ShoppingCartEntity entity = new ShoppingCartEntity((Integer)object[0],(Integer)object[1]);
                mShoppingCart.add(entity.getAuctionFromDatabase());
            }
        }
        return mShoppingCart;
    }

    public void removeAuctionFromCart(Auction auction) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        SQLQuery query = session.createSQLQuery("DELETE FROM "+mTableName+" WHERE auction_id="+auction.getAuctionId());
        query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        session.beginTransaction();
    }

    public void addAuctionToShoppingCart(UserAccount user, Auction auction) {
            Session session = HibernateUtils.getSessionFactory().openSession();
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("INSERT INTO " + mTableName + "(useraccounts_user_id, auctions_auction_id) VALUES (" + user.getUserId() + "," + auction.getAuctionId()+")");
            query.executeUpdate();
            session.getTransaction().commit();
            session.close();
>>>>>>> 8bcff1f6d64425ee33e6dfae53ca10deac21973e
    }
}
