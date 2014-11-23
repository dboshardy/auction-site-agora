import java.util.Date;

/**
 * Created by loubowen on 10/19/14.
 */
public class Transaction {
    private Date mTransactionDate;
    private UserAccount mTransactionSource;
    private UserAccount mTransactionDestination;

    public Transaction getTransaction(){
        return this;
    }

    public void setTransactionDate(Date transactionDate){
        this.mTransactionDate = transactionDate;
    }


    public void setTransactionSource(UserAccount transactionSource) {
        this.mTransactionSource = transactionSource;
    }

    public void setTransactionDestination(UserAccount transactionDestination) {
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
