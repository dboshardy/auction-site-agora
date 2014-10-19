import java.util.Date;

/**
 * Created by loubowen on 10/19/14.
 */
public class Transaction {
    private Date mTransactionDate;
    private Buyer mTransactionSource;
    private Seller mTransactionDestination;

    public Transaction getTransaction(){
        return this;
    }

    public void setTransactionDate(Date transactionDate){
        this.mTransactionDate = transactionDate;
    }


    public void setTransactionSource(Buyer transactionSource) {
        this.mTransactionSource = transactionSource;
    }

    public void setTransactionDestination(Seller transactionDestination) {
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
