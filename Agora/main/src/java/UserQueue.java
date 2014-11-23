/**
 * Created by Miao on 11/22/14.
 */

import org.json.JSONObject;

public class UserQueue extends Message{

    public JSONObject createMessageBody(JSONObject obj){
        JSONObject output = new JSONObject();
        String result= "";
        String type = obj.getString("type");

        UserAccountController userController= new UserAccountController();

        if (type.equals("create")){
            // take appropriate action to create new tuple in database
            // create a JSON object for the return message
            String username = obj.getString("username");
            String email= obj.getString("email");
            String first_name= obj.getString("first_name");
            String last_name = obj.getString("last_name");
            String user_description= obj.getString("user_description");
            String password_hash= obj.getString("password_hash");


            UserAccount user= new UserAccount(username,email, password_hash, first_name, last_name, user_description);
            result= userController.persistUserAccount(user);
            if(result.equals("true")){
                output.put("succeed",true);
            }else{
                output.put("succeed",false);
                output.put("Error",result);
            }
        } else if(type.equals("update")){
            // you get the idea
            String username = obj.getString("username");
            String email= obj.getString("email");
            String first_name= obj.getString("first_name");
            String last_name = obj.getString("last_name");
            String user_description= obj.getString("user_description");
            String password_hash= obj.getString("password_hash");


            UserAccount user= new UserAccount(username,email, password_hash, first_name, last_name, user_description);
            result= userController.updateUserAccount(user);
            if(result.equals("true")){
                output.put("succeed",true);
            }else{
                output.put("succeed",false);
                output.put("Error",result);
            }
        }else if(type.equals("delete")){
            // you get the idea
            String user_id = obj.getString("user_id");
            result= userController.deleteUserAccount(new UserAccount(Integer.parseInt(user_id)));
            if(result.equals("true")){
                output.put("succeed",true);
            }else{
                output.put("succeed",false);
                output.put("Error",result);
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

        }*/else if(type.equals("show")){
            // you get the idea
            String user_id = obj.getString("user_id");
            result = userController.getUserById(Integer.parseInt(user_id)).toString();
            output.put("user",result);

        }
        return output;
    }





}