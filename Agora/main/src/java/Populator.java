import org.apache.log4j.Logger;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by drew on 11/28/14.
 */
public class Populator {
    private static final Logger LOG = Logger.getLogger(Populator.class);

    public static void main(String[] args) {
        UserAccount user1 = new UserAccount("upham", "upham@gmail.com", "0293u5r0jf8o;kljwer09ew8", "Steve", "Cozminski", "I like turtles.");
        UserAccount user2 = new UserAccount("caparzo", "caparzo@gmail.com", "020938r293;kljwer09ew8", "Eric", "Riparte", "I've got a lovely bunch of coconuts.");
        UserAccount user3 = new UserAccount("mdamon", "maaaatdaaaamon@gmail.com", "020938r293;kljwer09ew8", "James", "Ryan", "I invented the longer lasting lightbulb.");
        UserAccount user4 = new UserAccount("tHanks", "forrest@gmail.com", "0202935i09r0;alkjfnbklskdjf", "Tom", "Hanks", "Stupid is as stupid does.");
        UserAccount user5 = new UserAccount("cpt. falcon", "falconPunch@gmail.com", "20395lksj;dlkan40238u;d", "The", "Captain", "Show me your moves!");
        UserAccount user6 = new UserAccount("puff", "jiggles@gmail.com", "2002398l;j02ilelkan40238u;d", "Jiggly", "Puff", "PUFF!");

        Date today = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(today);
        cal.add(Calendar.DATE,7);

        Date oneWeek = cal.getTime();

        cal.setTime(today);
        cal.add(Calendar.DATE,2);

        Date dayAfterTomorrow = cal.getTime();

        cal.setTime(today);
        cal.add(Calendar.DATE,1);

        Date tomorrow = cal.getTime();

        cal.setTime(today);
        cal.add(Calendar.MINUTE,3);
        Date in3Minutes = cal.getTime();

        Auction auction1 = new Auction("Brand new iPhone 6!",user2,"The best iPhone on the market", new BigDecimal(500.00),oneWeek);
        Auction auction2 = new Auction("First Edition The Hobbit, signed by Tolkein",user4,"First run copy of th The hobbit signed by J.R.R. Tolkein", new BigDecimal(3000.00),tomorrow);
        Auction auction3 = new Auction("Lenovo Laptop 13\" Display",user6,"Used laptop.  Screen may be cracked.",new BigDecimal(250.00),dayAfterTomorrow);
        Auction auction4 = new Auction("Variety pack of buttons",user3,"Various buttons, 500+ count.",new BigDecimal(50.00),dayAfterTomorrow);
        Auction auction5 = new Auction("Like New Nintendo 3DS XL",user5,"Nintendo 3DS XL. Like new. Barely used. Comes with stylus.",new BigDecimal(250.00),in3Minutes);
        UserAccountController userAccountController = new UserAccountController();
        AuctionController auctionController = new AuctionController();

        userAccountController.persistUserAccount(user1);
        userAccountController.persistUserAccount(user2);
        userAccountController.persistUserAccount(user3);
        userAccountController.persistUserAccount(user4);
        userAccountController.persistUserAccount(user5);
        userAccountController.persistUserAccount(user6);

        auctionController.persistAuction(auction1);
        auctionController.persistAuction(auction2);
        auctionController.persistAuction(auction3);
        auctionController.persistAuction(auction4);
        auctionController.persistAuction(auction5);

        LOG.info("Done seeding database!");
    }
}
