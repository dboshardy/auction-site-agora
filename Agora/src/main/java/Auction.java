import java.util.List;

/**
 * Created by Miao Yu on 10/19/14.
 */
public class Auction {

    private String timestamp;
    private ArrayList<Bid> bidList;
    private Seller seller;
    public Auction() {
        
    }

    public ArrayList<Bid> getBidList(){
        ArrayList<Item> result= bidList;
        return result;
    }

    public void setTimestamp(String time){
        timestamp=time;
    }

    public Seller getSeller(){
        return seller;
    }

    public void setSeller(Seller seller){
        this.seller=seller;
    }

    public boolean addBid(Bid bid){
        bidList.add(bid);
        return true;
    }

}
