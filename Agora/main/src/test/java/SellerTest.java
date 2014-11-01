/**
 * Created by thomkel on 10/24/14.
 */
public class SellerTest {
    Seller tester;
    int mUserId;

    @BeforeClass
    public void testSetup() {
        mUserId = 5555;
        tester = new Seller(mUserId);
    }

    @Test
    public void testConstructor() {
        // assertEquals(mUserId, tester.getUserId());
    }

    public void testCreateAuction(){
        Auction testAuction = test.createAuction();
        // test each element of Auction
        // test Auction was added to database
        // TODO: research how Java communicates with Database.
    }

    public void testDeleteAuction(){
        // should only delete auction when no bids. Test two scenarios: auction with bids and auction without
        // confirm Auction is deleted from database
    }

    public void testReceiveTransaction(){
        // what does receive transaction actually do?
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
