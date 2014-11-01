/**
 * Created by thomkel on 10/24/14.
 */
public class ItemTest {
    Item tester;
    int mItemId;
    String mItemName;
    String mItemDescription;

    @BeforeClass
    public void testSetup() {
        mItemDescription = "Made in Holland";
        mItemName = "Wooden Clogs";
        mItemId = 59876;
        tester = new Item(mItemId, mItemName, mItemDescription);
    }

    @Test
    public void testConstructor() {
        assertEquals(mItemId, tester.getItemId());
        assertEquals(mItemName, tester.getItemName());
        assertEquals(mItemDescription, tester.getItemDescription());
    }

    public void testEditItemName(){
        String itemName = "Snow shoes";
        Item testItem = new Item(5555, itemName, "Made in Germany");

        assertEquals(itemName, testItem.getItemName());

        itemName = "BMW M3";
        test.editItemName(itemName);

        assertEquals(itemName, testItem.getItemName());

    }

    public void testEditItemDescription(){
        String itemDesc = "Made in France";
        Item testItem = new Item(5555, "Baguette", itemDesc);

        assertEquals(itemDesc, testItem.getItemDescription());

        itemDesc = "Made in Vietnam";
        test.editItemDescription(itemDesc);

        assertEquals(itemDesc, testItem.getItemDescription());

    }
}
