import java.util.Date;

/**
 * Created by loubowen on 10/19/14.
 */
public class Transaction {
    private Date mTransactionDate;
<<<<<<< HEAD
    private UserAccount mTransactionSource;
    private UserAccount mTransactionDestination;
=======
    private Bidder mTransactionSource;
    private Seller mTransactionDestination;
>>>>>>> code merge and change

    public Transaction getTransaction(){
        return this;
    }

    public void setTransactionDate(Date transactionDate){
        this.mTransactionDate = transactionDate;
    }


<<<<<<< HEAD
    public void setTransactionSource(UserAccount transactionSource) {
        this.mTransactionSource = transactionSource;
    }

    public void setTransactionDestination(UserAccount transactionDestination) {
=======
    public void setTransactionSource(Bidder transactionSource) {
        this.mTransactionSource = transactionSource;
    }

    public void setTransactionDestination(Seller transactionDestination) {
>>>>>>> code merge and change
        this.mTransactionDestination = transactionDestination;
    }

    public boolean isAuthorized(){
        boolean authorization_flag = false;
        return authorization_flag;
    };

    public boolean isLegitimate(){
        boolean legitimate_flag = false;
        return legitimate_flag;
    }



}
