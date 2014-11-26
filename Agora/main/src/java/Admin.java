/**
 * Created by loubowen on 10/19/14.
 */
public class Admin extends UserAccount{
<<<<<<< HEAD

=======
>>>>>>> code merge and change
    public void authorizeTransaction(){};
    public void authorizeAuction(){};
    public String updateUserPassword(String newPwd){
        return  newPwd;
    };
<<<<<<< HEAD
    public String updateItem(Auction auction){return "response";}
=======
    public Item updateItem(Item item){return item;}
>>>>>>> code merge and change
    public Email updateEmail(Email email){return email;}
    public Payment updatePayment(Payment payment){return payment;}

}
