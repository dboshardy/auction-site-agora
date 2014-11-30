import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by drew on 11/7/14.
 */
public class AuctionEngine {

    private static final Logger LOG = Logger.getLogger(AuctionEngine.class);
    private static SessionFactory mSessionFactory;

    public static void main(String[] args) throws IOException {


        UserAccount user1 = new UserAccount("dboshardy", "dboshardy@gmail.com", "qoiwejg;alksjdfho", "Drew", "Boshardy", "I am a person, and I want to sell stuff.");

        UserAccountController userAccountController = new UserAccountController();

        //add user
        userAccountController.persistUserAccount(user1);

        user1.setFirstName("Andrew");
        //update
        userAccountController.updateUserAccount(user1);

        //add second user
        UserAccount user2 = new UserAccount("thomkel", "thomkel@gmail.com", "a;o8p234ifsd", "Thomas", "Kelly", "I am a person, and I want to buy stuff.");
        userAccountController.persistUserAccount(user2);

        //create auction
        Date date = new Date();
        Date newDate = new Date(date.getTime());

        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(newDate);
        calendar.add(Calendar.DATE, 7);
        newDate.setTime(calendar.getTime().getTime());

//        Auction auction1 = new Auction("Computer", user1, "This is a computer I want to sell.",new BigDecimal(2000.00),newDate, categoryId);
//
//        AuctionController auctionController = new AuctionController();
//        auctionController.persistAuction(auction1);
//        //give user a watchlist
//        user2.addWatchlist(new Watchlist(user2, "default"));

//        user2.getWatchlist("default").addAuctionToWatchlist(auction1);
//        userAccountController.placeBidOnAuction(user2,new Bid(user2,auction1,new BigDecimal(2500.00)),auction1);

        //delete it after input
        System.out.println("press any key to delete");
        System.in.read();
        userAccountController.deleteUserAccount(user1);
    }


}