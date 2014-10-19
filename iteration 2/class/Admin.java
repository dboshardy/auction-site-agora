/**
 * Created by loubowen on 10/19/14.
 */
public class Admin extends UserAccount{
    public void authorize_transaction(){};
    public void authorize_auction(){};
    public String update_user_password(String new_pwd){
        return  new_pwd;
    };
    public Item update_item(Item item){return item;}
    public Email update_email(Email email){return email;}
    public Payment update_payment(Payment payment){return payment;}

}
