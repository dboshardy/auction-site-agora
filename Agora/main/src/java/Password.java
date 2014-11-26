
/**
 * Created by Miao Yu on 10/19/14.
 */
public class Password {

    private String password;
<<<<<<< HEAD
=======
    private ;
>>>>>>> code merge and change

    public Password(String password) {
        this.password=password;
    }

    public String createHash(String input){
        String output = "";
        //TODO: create hash for input password
        return output;
    }

    public String getHash(){
        String hashedpwd="";
        return hashedpwd;
    }

    public boolean changePassword(String newPwd){
        //change may fail, so boolean for this method.

        return true;
    }
}
