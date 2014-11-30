import java.lang.String;

/**
 * Created by thomkel on 10/19/14.
 */
public class Email {

    private int mSenderId;
    private String mSenderAccount;
    private String mSenderPassword;

    private int mReceiverId;
    private String mReceiverAccount;

    private String mEmailContent;
    private String mEmailSubject;

    public Email(String senderAccount, String senderPassword, String receiverAccount,
                 String emailSubject, String emailContent){
        mSenderAccount = senderAccount;
        mSenderPassword = senderPassword;
        mReceiverAccount = receiverAccount;
        mEmailSubject = emailSubject;
        mEmailContent = emailContent;
    }

    public Email() {
    }

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

    public String getSenderAccount() {
        return mSenderAccount;
    }

    public String getSenderPassword() {
        return mSenderPassword;
    }

    public void setSenderPassword(String senderPassword) {
        mSenderPassword = senderPassword;
    }

    public String getEmailSubject() {
        return mEmailSubject;
    }

    public void setEmailSubject(String emailSubject) {
        mEmailSubject = emailSubject;
    }

    public void setSenderAccount(String senderAccount) {
        mSenderAccount = senderAccount;
    }

    public String getReceiverAccount() {
        return mReceiverAccount;
    }

    public void setReceiverAccount(String receiverAccount) {
        mReceiverAccount = receiverAccount;
    }

    public String getEmailContent() {
        return mEmailContent;
    }

    public void setEmailContent(String emailContent) {
        mEmailContent = emailContent;
    }
}
