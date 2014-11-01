/**
 * Created by thomkel on 10/19/14.
 */
public class Seller extends UserAccount {

    public Seller(){}

    public Auction createAuction(){
        // Auction-- should this have constructor accepting Seller and timestamp?
        // also, is the timestamp when the auction is created or when the auction begins?
    }

    public Auction deleteAuction(int auctionId){

    }

    public void receiveTransction(){
        // what does receive transaction actually do?
    }

    public void editItemDescription(String newItemDescription){
        // need to test the description fits rules. Done in controller or model? If model, how do we
        // recognize errors returned by the database?

    }

    public void editItemName(String newItemName){
        // input validation. Same question as comment in editItemDescription

    }

}
