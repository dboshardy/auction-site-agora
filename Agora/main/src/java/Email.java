/**
 * Created by thomkel on 10/19/14.
 */
public class Email {

    private int mSenderId;
    private int mReceiverId;

    public Email(int senderId, int receiverId){
        mSenderId = senderId;
        mReceiverId = receiverId;
    }
    
    public void createEmail(){}

    public void sendEmail(){};

    public int getSenderId() {
        return mSenderId;
    }

    public void setSenderId(int mSenderId) {
        this.mSenderId = mSenderId;
    }

    public int getReceiverId() {
        return mReceiverId;
    }

    public void setReceiverId(int mReceiverId) {
        this.mReceiverId = mReceiverId;
    }
}
