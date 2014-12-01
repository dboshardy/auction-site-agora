/**
 * Created by thomkel on 11/14/14.
 */
import org.hibernate.Session;


public class KickoffQueues {

    public static void main(String[] args) {
        QueueHandler auctionHandler = new QueueHandler("Auction", "AuctionConfirm");
        QueueHandler userHandler = new QueueHandler("User", "UserConfirm");
        QueueHandler watchlistHandler = new QueueHandler("Watchlist", "WatchlistConfirm");
        QueueHandler flagHandler = new QueueHandler("Flag", "FlagConfirm");
        QueueHandler bidHandler = new QueueHandler("Bid", "BidConfirm");
        QueueHandler cartHandler = new QueueHandler("ShoppingCart", "ShoppingCartConfirm");


        Thread auctionThread = new Thread(auctionHandler);
        Thread userThread = new Thread(userHandler);
        Thread watchlistThread = new Thread(watchlistHandler);
        Thread flagThread = new Thread(flagHandler);
        Thread bidThread = new Thread(bidHandler);
        Thread cartThread = new Thread(cartHandler);

        System.out.print("\n\n\n");
        System.out.println("Starting Queues...");

        try {

            System.out.println("Kicking off auction queue connection ...");
            auctionThread.start();
            System.out.println("Kicking off user queue connection ...");
            userThread.start();
            System.out.println("Kicking off watchlist queue connection...");
            watchlistThread.start();
            System.out.println("Kicking off flag queue connection...");
            flagThread.start();
            System.out.println("Kicking off bid queue connection...");
            bidThread.start();
            System.out.println("Kicking off shopping cart queue connection...");
            cartThread.start();

        } catch (Exception e) {
            System.out.println("Caught an exception during the example: " + e);
        }

    }

}
