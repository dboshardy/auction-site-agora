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
            System.out.println("Created user");
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
            int id = obj.getInt("user_id");
            String username = obj.getString("username");
            String email = obj.getString("email");
            String firstName = obj.getString("first_name");
            String lastName = obj.getString("last_name");
            String userDescription = obj.getString("user_description");
            String passwordHash = obj.getString("password_hash");


            UserAccount user = new UserAccount(id, username, email, passwordHash, firstName, lastName, userDescription);
            result = userController.updateUserAccount(user);
            if (result.equals("true")) {
                output.put("succeed", true);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        } else if (type.equals("delete")) {
            int user_id = obj.getInt("user_id");
            UserAccount user = userController.getUserById(user_id);
            result = userController.deleteUserAccount(user);
            if (result.equals("true")) {
                output.put("succeed", true);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        }/*else if(type.equals("suspend")){

        }*/ else if (type.equals("show")) {
            int user_id = obj.getInt("user_id");
            UserAccount user = userController.getUserById(user_id);
            output.put("user_id", user.getUserId());
            output.put("username", user.getUserName());
            output.put("first_name", user.getFirstName());
            output.put("last_name", user.getLastName());
            output.put("user_description", user.getDescription());
            output.put("email", user.getEmail());

        }
        else if (type.equals("login")){
            String userName = obj.getString("user_name");
            String passwordHash =  obj.getString("password_hash");
            System.out.println("Getting result...");
            UserAccount user = userController.getUserAccountByUsernameAndPasswordHash(userName, passwordHash);
            System.out.println("Found result...");
            if(user != null) {
                output.put("succeed", true);
                output.put("user_id", user.getUserId());
                output.put("is_admin", user.getIsAdmin());
            }
            else {
                output.put("status", false);
                output.put("Error","Could not get user for this username or password");
            }
        }
        return output;
    }


}
