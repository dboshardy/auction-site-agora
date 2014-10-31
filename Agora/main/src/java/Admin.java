/**
 * Created by loubowen on 10/19/14.
 */
public class Admin extends UserAccount{
    public Admin(String userName, String email, Password password, Watchlist watchlist, ShoppingCart shoppingCart, UserDescription userDescription, String userLocation) {
        super(userName, email, password, watchlist, shoppingCart, userDescription, userLocation);
    }

    public void authorizeTransaction(){};
    public void authorizeAuction(){};
    public String updateUserPassword(String newPwd){
        return  newPwd;
    };
    public Item updateItem(Item item){return item;}
    public Email updateEmail(Email email){return email;}
    public Payment updatePayment(Payment payment){return payment;}

}
