import java.util.Date;

/**
 * Created by loubowen on 10/19/14.
 */

interface pay_strategy{void pay();}

class pay_by_credit_card implements pay_strategy{
    public void pay(){

    }
}

class pay_by_paypal implements pay_strategy{
    public void pay(){

    }
}

public class Payment {
    private double payment_amount;
    private Date payment_date;
    private User payment_source_user;
    private User payment_dest_user;

    private pay_strategy payStrategy;    // payment_method

    public Payment(pay_strategy payStrategy){
        this.payStrategy = payStrategy;
    }

    // getter
    public double getPayment_amount() {
        return payment_amount;
    }

    public Date getPayment_date() {
        return payment_date;
    }

    public User getPayment_source_user() {
        return payment_source_user;
    }

    public User getPayment_dest_user() {
        return payment_dest_user;
    }

    public pay_strategy getPayStrategy() {
        return payStrategy;
    }

    // setter
    public void setPayment_amount(double payment_amount) {
        this.payment_amount = payment_amount;
    }

    public void setPayment_date(Date payment_date) {
        this.payment_date = payment_date;
    }

    public void setPayment_source_user(User payment_source_user) {
        this.payment_source_user = payment_source_user;
    }

    public void setPayment_dest_user(User payment_dest_user) {
        this.payment_dest_user = payment_dest_user;
    }

    public void setPayStrategy(pay_strategy payStrategy) {
        this.payStrategy = payStrategy;
    }

    // check
    public boolean is_authorized(){
        boolean authorization_flag = false;
        return authorization_flag;
    };

    public boolean is_legitimate(){
        boolean legitimate_flag = false;
        return legitimate_flag;
    }
}
