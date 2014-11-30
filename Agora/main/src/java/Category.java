import java.util.HashSet;
import java.util.Set;

/**
 * Created by drew on 10/19/14.
 */
public class Category {
    private String mName;
    private Category mParent;
    private Set<Category> mChildren = new HashSet<Category>();
    private int mCategoryId;
    private int mParentId;

    //todo: make this work
    public int getParentId() {
        return mParentId;
    }

    public void setParentId(int parentId) {
        mParentId = parentId;
    }

    public Category() {
    }

    public int getCategoryId() {
        return mCategoryId;
    }

    public void setCategoryId(int categoryId) {
        mCategoryId = categoryId;
    }

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

    @Override
    public String toString() {
        return "Category{" +
                "mName='" + mName + '\'' +
                ", mCategoryId=" + mCategoryId +
                ", mParentId=" + mParentId +
                '}';
    }
}
