import java.util.Date;

/**
 * Created by loubowen on 10/19/14.
 */
public class Transaction {
    private Date transaction_date;
    private Buyer transaction_source;
    private Seller transaction_destination;

    public Transaction getTransaction(){
        return this;
    }

    public void setTransaction_date(Date transaction_date){
        this.transaction_date = transaction_date;
    }


    public void setTransaction_source(Buyer transaction_source) {
        this.transaction_source = transaction_source;
    }

    public void setTransaction_destination(Seller transaction_destination) {
        this.transaction_destination = transaction_destination;
    }

    public boolean is_authorized(){
        boolean authorization_flag = false;
        return authorization_flag;
    };

    public boolean is_legitimate(){
        boolean legitimate_flag = false;
        return legitimate_flag;
    }



}
