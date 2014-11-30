/**
 * Created by drew on 11/21/14.
 */
public class Janitor implements Runnable {
    @Override
    public void run() {
        Janitor endedAuctionJanitor = new EndedAuctionJanitor();
        Janitor bidChangeJanitor = new BidChangeJanitor();
        endedAuctionJanitor.run();
        bidChangeJanitor.run();

    }
    //this class runs a continues loop that checks the database for ended auctions
    //  if it finds any ended auctions, it sets their "is_ended" column to true and
    //  does any tasks necessary
    // probably use this http://jdbc.postgresql.org/documentation/81/listennotify.html

    //maybe also add bid notification class
    // implement in watchlist to make sure
}
