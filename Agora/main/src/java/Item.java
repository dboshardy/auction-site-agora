/**
 * Created by thomkel on 10/19/14.
 */
publixc class Item {

    private int mItemId;
    private String mItemName;
    private String mItemDescription;

    public Item(int itemId, String itemName, String itemDescription){
        mItemId = itemId;
        mItemName = itemName;
        mItemDescription = itemDescription;
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
