import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Created by drew on 10/19/14.
 */
public class Taxonomy {

    private Map<String, Category> mStringCategoryMap;

    public Taxonomy(Map<String, Category> stringCategoryMap) {
        mStringCategoryMap = stringCategoryMap;
    }

    public void addCategory(Category category){
        mStringCategoryMap.put(category.getName(),category);
    }

    public Category getCategory(String name) {
        return mStringCategoryMap.get(name);
    }

    // removes category given by name and returns true if successful and false otherwise
    public boolean removeCategory(String name) {

        boolean isSuccess = false;
        try {
            mStringCategoryMap.remove(name);
            isSuccess = true;
        } catch (Exception e) {

        }

        return isSuccess;
    }
}
