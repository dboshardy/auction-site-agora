import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.InputStream;

/**
 * Created by drew on 11/7/14.
 */
public class AuctionEngine {

    private static final Logger LOG = Logger.getLogger(AuctionEngine.class);
    private static SessionFactory mSessionFactory;
    private static ServiceRegistry mServiceRegistry;

    public static void main(String[] args){

        try {
            mSessionFactory = createSessionFactory();
        } catch (Throwable e) {
            LOG.warn("Failed to create session factory object. " + e);
        }

        UserAccount user1 = new UserAccount("dboshardy","dboshardy@gmail.com","qoiwejg;alksjdfho","Drew","Boshardy","I am a person, and I want to sell stuff.");

        UserAccountController userAccountController = UserAccountController.getInstance(mSessionFactory);

        userAccountController.addUserAccount(user1);
    }



    public static SessionFactory createSessionFactory() {
        InputStream inputStream = AuctionEngine.class.getResourceAsStream("/hibernate.cfg.xml");
        Configuration configuration = new Configuration().addInputStream(inputStream);
        configuration.configure();
        ServiceRegistry serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);
        return factory;

    }


}
