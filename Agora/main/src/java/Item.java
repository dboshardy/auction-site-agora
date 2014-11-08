/**
 * Created by thomkel on 10/19/14.
 */
public class Item {

    private int mItemId;
    private String mItemName;
    private String mItemDescription;

    public Item(int itemId, String itemName, String itemDescription){
        // input validation
        mItemId = itemId;
        mItemName = itemName;
        mItemDescription = itemDescription;
    }

    public int getItemId() {
        return mItemId;
    }

    public void setItemId(int mItemId) {
        this.mItemId = mItemId;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String mItemName) {
        this.mItemName = mItemName;
    }

    public void deleteItem(){}

    public String getItemDescription() {
        return mItemDescription;
    }

    public void setItemDescription(String mItemDescription) {
        this.mItemDescription = mItemDescription;
    }
}
