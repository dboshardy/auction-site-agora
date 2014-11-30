import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by thomkel on 10/19/14.
 */
public class Email {

    private int mSenderId;
    private String mSenderAccountEmail;
    private String mSenderPassword;

    private int mReceiverId;
    private String mReceiverAccountEmail;

    private String mEmailContent; private String mEmailSubject;

    public Email(String senderAccount, String senderPassword, String receiverAccount,
                 String emailSubject, String emailContent){
        mSenderAccountEmail = senderAccount;
        mSenderPassword = senderPassword;
        mReceiverAccountEmail = receiverAccount;
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

    public String getSenderAccountEmail() {
        return mSenderAccountEmail;
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

    public void setSenderAccountEmail(String senderAccountEmail) {
        mSenderAccountEmail = senderAccountEmail;
    }

    public String getReceiverAccountEmail() {
        return mReceiverAccountEmail;
    }

    public void setReceiverAccountEmail(String receiverAccountEmail) {
        mReceiverAccountEmail = receiverAccountEmail;
    }

    public String getEmailContent() {
        return mEmailContent;
    }

    public void setEmailContent(String emailContent) {
        mEmailContent = emailContent;
    }
    public static boolean sendEmail(final Email email){
        // JavaMail
        // Send an Email via Gmail SMTP server using SSL connection.
        // Should add external jar module before running

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        boolean sendResult = false;
        final String[] sender = {email.getSenderAccountEmail()};
        String receiver = email.getReceiverAccountEmail();
        String emailSubject = email.getEmailSubject();
        String emailContent = email.getEmailContent();

        // can test admin user account: agoraemailnoreply@gmail.com
        Session session = Session.getDefaultInstance(props,
                new Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        //return new PasswordAuthentication("agoraemailnoreply","agoraagora");
                        String senderPassword = email.getSenderPassword();

                        if (sender[0].contains("gmail.com")){
                            sender[0] = sender[0].split("@")[0];
                        }
                        return new PasswordAuthentication(sender[0], email.getSenderPassword());
                    }
                });

        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(sender[0]));
            message.setRecipients(MimeMessage.RecipientType.TO,
                    InternetAddress.parse(receiver));
            message.setSubject(emailSubject);
            message.setText(emailContent);
            Transport.send(message);

//            System.out.println("Done");
            sendResult = true;

        } catch (MessagingException e) {
//            System.out.println("May not received.");
            sendResult = false;
            throw new RuntimeException(e);
        }

        return sendResult;

    }
}
