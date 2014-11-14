/**
 * Created by thomkel on 11/14/14.
 */
public class KickoffQueues {

    public static void main(String[] args) {
        QueueHandler auctionHandler = new QueueHandler("Auction", "AuctionConfirm");
        System.out.print("\n\n\n");
        System.out.println("Starting Queues...");
        try {
            auctionHandler.initializeQueues();
            while(true) {
                System.out.println("Kicking off new connection...");
                auctionHandler.runQueues();
//            example.after();
            }
        } catch (Exception e) {
            System.out.println("Caught an exception during the example: " + e);
        }
        System.out.println("Queue connections have been closed");
        System.out.print("\n\n\n");
    }

}
