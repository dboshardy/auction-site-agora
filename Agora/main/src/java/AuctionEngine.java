import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;

import java.io.IOException;

/**
 * Created by drew on 11/7/14.
 */
public class AuctionEngine {

    private static final Logger LOG = Logger.getLogger(AuctionEngine.class);
    private static SessionFactory mSessionFactory;

    public static void main(String[] args) throws IOException {

        try {
            mSessionFactory = HibernateUtils.getSessionFactory();
        } catch (Throwable e) {
            LOG.warn("Failed to create session factory object. " + e);
        }

        UserAccount user1 = new UserAccount("dboshardy","dboshardy@gmail.com","qoiwejg;alksjdfho","Drew","Boshardy","I am a person, and I want to sell stuff.");

        UserAccountController userAccountController = UserAccountController.getInstance(mSessionFactory);

        //add user
        userAccountController.addUserAccount(user1);

        user1.setFirstName("Andrew");
        //update
        userAccountController.updateUserAccount(user1);

        userAccountController.getUserById(1);
        //delete it after input
        System.out.println("press any key to delete");
        System.in.read();
        userAccountController.deleteUserAccount(user1);
    }




}
