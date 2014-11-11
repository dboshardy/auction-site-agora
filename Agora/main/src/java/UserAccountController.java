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
        try {
            session.beginTransaction();
            user.setUserId((Integer) session.save(user));
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            //LOG.warn("Could not insert user account : {} to database.",user.toString());
        }

    }

    public void deleteUserAccount(UserAccount user) {
        Session session = mSessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            //LOG.warn("Could not remove user account : {} to database.",user.toString());
        }
    }

    public void updateUserAccount(UserAccount user) {
        Session session = mSessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            //LOG.warn("Could not update user account : {} to database.",user.toString());
        }
    }

    public UserAccount getUserById(int i) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        return (UserAccount) session.get(UserAccount.class, i);
    }
}
