import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.InputStream;

/**
 * Created by drew on 11/10/14.
 */
public class HibernateUtils {

    private static  SessionFactory sessionFactory;

    public static SessionFactory createSessionFactory() {
        InputStream inputStream = AuctionEngine.class.getResourceAsStream("/hibernate.cfg.xml");
        Configuration configuration = new Configuration().addInputStream(inputStream);
        configuration.configure();
        ServiceRegistry serviceRegistry = (ServiceRegistry) new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        return configuration.buildSessionFactory(serviceRegistry);

    }

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null){
            return createSessionFactory();
        }
        else {
            return sessionFactory;
        }
    }

}
