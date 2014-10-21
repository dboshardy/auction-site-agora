import java.util.List;

/**
 * Created by Miao Yu on 10/19/14.
 */
public class ShoppingCart {

    private ArrayList<Auction> cart;
    public ShoppingCart() {
        
    }

    public ArrayList<Auction> getCart(){
        
        return cart;
    }

    public void putToCart(Auction auction){
        cart.add(auction);
    }

    public boolean delete(Auction auction){
        cart.remove(auction);
        return true;
    }
}
