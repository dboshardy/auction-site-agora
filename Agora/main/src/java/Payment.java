import java.util.Date;

/**
 * Created by loubowen on 10/19/14.
 */

interface PaymentStrategy {void pay();}

class PayWithCreditCard implements PaymentStrategy{
    public void pay(){

    }
}

class PayWithPaypal implements PaymentStrategy{
    public void pay(){

    }
}

public class Payment {

    private double mPaymentAmount;
    private Date mPaymentDate;
    private UserAccount mPaymentSourceUser;
    private UserAccount mPaymentDestinationUser;

    private PaymentStrategy mPaymentStrategy;    // payment_method

    public Payment(PaymentStrategy mPaymentStrategy){
        this.mPaymentStrategy = mPaymentStrategy;
    }

    // getter
    public double getPaymentAmount() {
        return mPaymentAmount;
    }

    public Date getPaymentDate() {
        return mPaymentDate;
    }

    public UserAccount getPaymentSourceUser() {
        return mPaymentSourceUser;
    }

    public UserAccount getPaymentDestinationUser() {
        return mPaymentDestinationUser;
    }

    public PaymentStrategy getPaymentStrategy() {
        return mPaymentStrategy;
    }

    // setter
    public void setPaymentAmount(double paymentAmount) {
        this.mPaymentAmount = paymentAmount;
    }

    public void setPaymentDate(Date paymentDate) {
        this.mPaymentDate = paymentDate;
    }

    public void setPaymentSourceUser(UserAccount paymentSourceUser) {
        this.mPaymentSourceUser = paymentSourceUser;
    }

    public void setPaymentDestinationUser(UserAccount paymentDestinationUser) {
        this.mPaymentDestinationUser = paymentDestinationUser;
    }

    public void setPaymentStrategy(PaymentStrategy payStrategy) {
        this.mPaymentStrategy = payStrategy;
    }

    // check
    public boolean isAuthorized(){
        boolean authorizationFlag = false;
        return authorizationFlag;
    };

    public boolean isLegitimate(){
        boolean legitimateFlag = false;
        return legitimateFlag;
    }
}
