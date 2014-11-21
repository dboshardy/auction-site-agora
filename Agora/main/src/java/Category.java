import java.util.HashSet;
import java.util.Set;

/**
 * Created by drew on 10/19/14.
 */
public class Category {
    private String mName;
    private Category mParent;
    private Set<Category> mChildren = new HashSet<Category>();

    public Category(String name, Category parent) {
        mName = name;
        mParent = parent;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Category getParent() {
        return mParent;
    }

    public void setParent(Category parent) {
        mParent = parent;
    }

    public Set<Category> getChildren() {
        return mChildren;
    }

    public void setChildren(Set<Category> children) {
        mChildren = children;
    }
}
