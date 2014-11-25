/**
 * Created by Miao on 11/22/14.
 */

import org.json.JSONObject;

public class UserQueue extends Message {

    public JSONObject createMessageBody(JSONObject obj) {
        JSONObject output = new JSONObject();
        String result = "";
        String type = obj.getString("type");

        UserAccountController userController = new UserAccountController();

        if (type.equals("create")) {
            // take appropriate action to create new tuple in database
            // create a JSON object for the return message
            String username = obj.getString("username");
            String email = obj.getString("email");
            String firstName = obj.getString("first_name");
            String lastName = obj.getString("last_name");
            String userDescription = obj.getString("user_description");
            String passwordHash = obj.getString("password_hash");


            UserAccount user = new UserAccount(username, email, passwordHash, firstName, lastName, userDescription);
            result = userController.persistUserAccount(user);
            int user_id = user.getUserId();
            if (result.equals("true")) {
                output.put("succeed", true);
                output.put("user_id", user_id);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        } else if (type.equals("update")) {
            // you get the idea
            String username = obj.getString("username");
            String email = obj.getString("email");
            String firstName = obj.getString("first_name");
            String lastName = obj.getString("last_name");
            String userDescription = obj.getString("user_description");
            String passwordHash = obj.getString("password_hash");


            UserAccount user = new UserAccount(username, email, passwordHash, firstName, lastName, userDescription);
            result = userController.updateUserAccount(user);
            if (result.equals("true")) {
                output.put("succeed", true);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        } else if (type.equals("delete")) {
            // you get the idea
            String user_id = obj.getString("user_id");
            result = userController.deleteUserAccount(new UserAccount(Integer.parseInt(user_id)));
            if (result.equals("true")) {
                output.put("succeed", true);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        }/*else if(type.equals("login")){
            // you get the idea
            String username = obj.getString("username");
            String password_hash= obj.getString("password_hash");


            UserAccount user= new UserAccount(username,email, password_hash)
            result= userController.updateUserAccount(user);
            if(result.equals("true")){
                output.put("succeed",true);
            }else{
                output.put("succeed",false);
                output.put("Error",result);
            }
        }*//*else if(type.equals("suspend")){

        }*/ else if (type.equals("show")) {
            // you get the idea
            String user_id = obj.getString("user_id");
            result = userController.getUserById(Integer.parseInt(user_id)).toString();
            output.put("user", result);

        }
        else if (type.equals("login")){
            String userName = obj.getString("user_name");
            String passwordHash =  obj.getString("password_hash");
            System.out.println("Getting result...");
            result = userController.getUserAccountByUsernameAndPasswordHash(userName, passwordHash).toString();
            System.out.println("Found result...");
            if(result != null) {
                output.put("user", result);
            }
            else {
                output.put("Error","Could not get user for this username or password");
            }
        }
        return output;
    }


}
