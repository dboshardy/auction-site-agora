import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;

/**
 * Created by drew on 11/30/14.
 */
public class CategoryController {
    private Logger LOG = Logger.getLogger(CategoryController.class);

    public CategoryController(){

    }

    public String persistCategory(Category category){
        String result = "";
        Session session = HibernateUtils.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(category);
            session.getTransaction().commit();
            result = "true";

        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            result = "Hibernate Failed";

            LOG.warn("Could not insert user account: " + category.toString() + " to database.");
        }
        return result;
    }

}
