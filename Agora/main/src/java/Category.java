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
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> code merge and change
=======
=======
>>>>>>> 981e7d85c0c4653c1ee943e9b064a28b3bf3fc6b

    @Override
    public String toString() {
        return "Category{" +
                "mName='" + mName + '\'' +
                ", mCategoryId=" + mCategoryId +
                ", mParentId=" + mParentId +
                '}';
    }
<<<<<<< HEAD
>>>>>>> 7f8760962d6d08baed4ced092bb09bc1b39253b1
=======
>>>>>>> 981e7d85c0c4653c1ee943e9b064a28b3bf3fc6b
}
