import org.apache.log4j.Logger;
import org.hibernate.*;

/**
 * Created by drew on 11/7/14.
 */
public class UserAccountController {
    private Logger LOG = Logger.getLogger(UserAccountController.class);
    private static SessionFactory mSessionFactory;

    public static UserAccountController getInstance(SessionFactory sessionFactory) {
        setSessionFactory(sessionFactory);
        return new UserAccountController();
    }

    public SessionFactory getSessionFactory() {
        return mSessionFactory;
    }

    public static void setSessionFactory(SessionFactory sessionFactory) {
        mSessionFactory = sessionFactory;
    }

    public void addUserAccount(UserAccount user) {
        Session session = mSessionFactory.openSession();
        org.hibernate.Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            user.setUserId((Integer) session.save(user));
            transaction.commit();
        }
        catch (HibernateException e){
            //LOG.warn("Could not insert user account : {} to database.",user.toString());
            //todo: implement
        }



    }
}
