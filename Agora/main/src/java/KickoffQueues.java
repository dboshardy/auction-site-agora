/**
 * Created by thomkel on 11/14/14.
 */
public class KickoffQueues {

    public static void main(String[] args) {
        QueueHandler auctionHandler = new QueueHandler("Auction", "AuctionConfirm");
        QueueHandler userHandler = new QueueHandler("User", "UserConfirm");
        //QueueHandler watchlistHandler = new QueueHandler("Watchlist", "WatchlistConfirm");

        Thread auctionThread = new Thread(auctionHandler);
        Thread userThread = new Thread(userHandler);
        //Thread watchlistThread = new Thread(watchlistHandler);

        System.out.print("\n\n\n");
        System.out.println("Starting Queues...");

        try {

            System.out.println("Kicking off auction queue connection ...");
            auctionThread.start();
            System.out.println("Kicking off user queue connection ...");
            userThread.start();
            //System.out.println("Kicking off watchlist queue connection...");
            //watchlistThread.start();
//          example.after();

        } catch (Exception e) {
            System.out.println("Caught an exception during the example: " + e);
        }

    }

}
