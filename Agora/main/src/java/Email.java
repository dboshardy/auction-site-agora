/**
 * Created by thomkel on 10/19/14.
 */
public class Email {

    private int mSenderId;
    private int mReceiverId;
<<<<<<< HEAD
    private String mEmailContent;
=======
>>>>>>> code merge and change

    public Email(int senderId, int receiverId){
        mSenderId = senderId;
        mReceiverId = receiverId;
    }
<<<<<<< HEAD

    public void sendEmail(UserAccount admin, UserAccount receiver, String content){
        mSenderId = admin.getUserId();
        mReceiverId = receiver.getUserId();
        mEmailContent = content;
    };
=======
    
    public void createEmail(){}

    public void sendEmail(){};
>>>>>>> code merge and change

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
