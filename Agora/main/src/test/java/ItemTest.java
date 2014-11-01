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
}
