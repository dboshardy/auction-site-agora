import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.List;

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

    public Category getCategoryById(int categoryId) {
        Session session = HibernateUtils.getSessionFactory().openSession();
        Category category = null;
        try {
            session.beginTransaction();
            category = (Category) session.get(Category.class, categoryId);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get category with id: " + categoryId + " in database.");
        }
        return category;
    }
    public List<Category> getAllCategories(){

        Session session = HibernateUtils.getSessionFactory().openSession();
        List<Category> categories = null;
        try{
            session.beginTransaction();
            SQLQuery query = session.createSQLQuery("SELECT * FROM categories WHERE category_name!=\'root\'").addEntity(Category.class);
            categories = query.list();
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            LOG.warn("Could not get categories from database.");
        }
        return categories;
    }
}
