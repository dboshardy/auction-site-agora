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

    public String persistFlagOnAuction(Flag flag) {

        String result= "";
        Session session = HibernateUtils.getSessionFactory().openSession();


        try{
            session.beginTransaction();
            session.save(flag);
            session.getTransaction().commit();
            session.close();
            result="true";

        }
        catch (HibernateException e){
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            result="Hibernate failed";
            LOG.warn("Could not persist flag: " + flag.toString() + "to database.");
        }
        return result;
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

    public String removeFlag(Flag flag){
        String response = "";
        Session session = HibernateUtils.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            session.delete(flag);
            session.getTransaction().commit();
            session.close();
            response = "Success!";
        }
        catch (HibernateException e){
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            response = "Failure!";
            LOG.warn("Could not remove flag "+flag.toString()+" from database.");
        }

        return response;
    }
    public List<Flag> getAllFlagsOnAuction(Auction auction){
        List<Flag> flags = null;
        Session session = HibernateUtils.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            Query query = session.createSQLQuery("SELECT * FROM flaggedauctions WHERE auctions_auction_id="+auction.getAuctionId()).addEntity(Flag.class);
            flags = query.list();
            session.getTransaction().commit();
            session.close();
        }
        catch (HibernateException e){
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not retrieve flags for auction: "+auction.toString()+" from database.");
        }
        return flags;
    }

    public Flag getFlagById(int flag_id){
        Session session = HibernateUtils.getSessionFactory().openSession();
        Flag flag =  null;
        try{
            session.beginTransaction();
            flag = (Flag) session.get(Flag.class,flag_id);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e){
            if(session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not retrieve flag with id: "+flag_id+" from database.");
        }
        return flag;
    }

}
