import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by drew on 11/7/14.
 */
public class AuctionEngine {

    private static SessionFactory mSessionFactory;
    private static ServiceRegistry mServiceRegistry;

    public static void main(String[] args){


    }

    public static SessionFactory createSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure();
        mServiceRegistry = (ServiceRegistry) new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        mSessionFactory = new Configuration().configure().buildSessionFactory(mServiceRegistry);
        return mSessionFactory;

    }
}
