/**
 * Created by loubowen on 10/19/14.
 */
public class Admin extends UserAccount{

    public void authorizeTransaction(){};
    public void authorizeAuction(){};
    public String updateUserPassword(String newPwd){
        return  newPwd;
    };
    public String updateItem(Auction auction){return "response";}
    public Email updateEmail(Email email){return email;}
    public Payment updatePayment(Payment payment){return payment;}

}
