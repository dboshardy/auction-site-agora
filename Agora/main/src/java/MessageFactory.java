/**
 * Created by thomkel on 11/14/14.
 */
public class MessageFactory {

    public Message getMessageClass(String queue){
        //todo: fix this, thom :D
        if (queue.equals("/queue/Auction")){
            return new AuctionQueue();
        }
        else if (queue.equals("/queue/Bid")){
            return new BidQueue();

        }
        else if (queue.equals("/queue/User")){
            return new UserQueue();

        }
        else if (queue.equals("/queue/Watchlist")){
            return new WatchlistQueue();

        }
        else if (queue.equals("/queue/WatchlistItem")){
            return new WatchlistItemQueue();

        }
        else if (queue.equals("/queue/ShoppingCart")){
            return new ShoppingCartQueue();

        }
         else if (queue.equals("/queue/Flag")){
            return new FlagQueue();

        }
        else if (queue.equals("/queue/ShoppingCartItem")){
            return new ShoppingCartItemQueue();

        }
        else if (queue.equals("/queue/Transaction")){
            return new TransactionQueue();

        }
        else if (queue.equals("/queue/Category")){
            return new CategoryQueue();

        }
        else if (queue.equals("/queue/Email")){
            return new EmailQueue();

        }
        return null;
    }
}
