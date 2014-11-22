import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by drew on 11/21/14.
 */
public class FlagController {
    private static final org.apache.log4j.Logger LOG = Logger.getLogger(FlagController.class);
    private String mFlaggedAuctionsTableName = "FlaggedAuctions";

    public void persistFlagOnAccount(Flag flag) {

        Session session = HibernateUtils.getSessionFactory().openSession();

        try{
            session.beginTransaction();
            session.save(flag);
            session.getTransaction().commit();
            session.close();

        }
        catch (HibernateException e){
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not persist flag: " + flag.toString() + "to database.");
        }
    }

    public List<Flag> getAllFlags(){
        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Flag> flags = null;
        try{
            session.beginTransaction();
            Query query = session.createSQLQuery("SELECT * FROM flaggedauctions").addEntity(Flag.class);
            flags = query.list();
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not retrieve flags from database.");
        }
        return flags;
    }
}
