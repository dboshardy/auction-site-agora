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
        //users
        UserAccount user1 = new UserAccount("upham", "upham@gmail.com", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", "Steve", "Cozminski", "I like turtles.");
        UserAccount user2 = new UserAccount("caparzo", "caparzo@gmail.com", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", "Eric", "Riparte", "I've got a lovely bunch of coconuts.");
        UserAccount user3 = new UserAccount("mdamon", "maaaatdaaaamon@gmail.com", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", "James", "Ryan", "I invented the longer lasting lightbulb.");
        UserAccount user4 = new UserAccount("tHanks", "forrest@gmail.com", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", "Tom", "Hanks", "Stupid is as stupid does.");
        UserAccount user5 = new UserAccount("cpt. falcon", "falconPunch@gmail.com", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", "The", "Captain", "Show me your moves!");
        UserAccount user6 = new UserAccount("puff", "jiggles@gmail.com", "9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08", "Jiggly", "Puff", "PUFF!");
        UserAccount admin = new UserAccount("TheDude","elduderino@agora.com","9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08","The","Dude","Well, that's just... like... your opinion, man.");
        admin.setIsAdmin(true);

        //dates
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


        //categories
        Category rootCategory = new Category("root",null);
        Category category1 = new Category("Collectibles & Art",rootCategory);
        Category category2 = new Category("Fashion",rootCategory);
        Category category3 = new Category("Electronics",rootCategory);
        Category category5 = new Category("Entertainment",rootCategory);
        Category category6 = new Category("Home & Garden",rootCategory);
        Category category7 = new Category("Motors",rootCategory);
        Category category8 = new Category("Sporting Goods",rootCategory);
        Category category9 = new Category("Toys & Hobbies",rootCategory);
        Category category10 = new Category("Everything Else",rootCategory);


        //controllers
        CategoryController categoryController = new CategoryController();
        UserAccountController userAccountController = new UserAccountController();
        AuctionController auctionController = new AuctionController();

        //persist
        userAccountController.persistUserAccount(admin);
        userAccountController.persistUserAccount(user1);
        userAccountController.persistUserAccount(user2);
        userAccountController.persistUserAccount(user3);
        userAccountController.persistUserAccount(user4);
        userAccountController.persistUserAccount(user5);
        userAccountController.persistUserAccount(user6);

        categoryController.persistCategory(rootCategory);
        categoryController.persistCategory(category1);
        categoryController.persistCategory(category2);
        categoryController.persistCategory(category3);
        categoryController.persistCategory(category5);
        categoryController.persistCategory(category6);
        categoryController.persistCategory(category7);
        categoryController.persistCategory(category8);
        categoryController.persistCategory(category9);
        categoryController.persistCategory(category10);

        //auctions
        Auction auction1 = new Auction("Brand new iPhone 6!",user2,"The best iPhone on the market", new BigDecimal(500.00),oneWeek, category3.getCategoryId());
        Auction auction2 = new Auction("First Edition The Hobbit, signed by Tolkein",user4,"First run copy of th The hobbit signed by J.R.R. Tolkein", new BigDecimal(3000.00),tomorrow, category5.getCategoryId());
        Auction auction3 = new Auction("Lenovo Laptop 13\" Display",user6,"Used laptop.  Screen may be cracked.",new BigDecimal(250.00),dayAfterTomorrow, category3.getCategoryId()  );
        Auction auction4 = new Auction("Variety pack of buttons",user3,"Various buttons, 500+ count.",new BigDecimal(50.00),dayAfterTomorrow, category10.getCategoryId());
        Auction auction5 = new Auction("Like New Nintendo 3DS XL",user5,"Nintendo 3DS XL. Like new. Barely used. Comes with stylus.",new BigDecimal(250.00),in3Minutes, category3.getCategoryId());

        //persist
        auctionController.persistAuction(auction1);
        auctionController.persistAuction(auction2);
        auctionController.persistAuction(auction3);
        auctionController.persistAuction(auction4);
        auctionController.persistAuction(auction5);

        LOG.info("Done seeding database!");
    }
}
