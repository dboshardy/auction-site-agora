/**
 * Created by Miao on 11/22/14.
 */


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class FlagQueue extends Message{

    public JSONObject createMessageBody(JSONObject obj){
        JSONObject output = new JSONObject();
        String result= "";
        String type = obj.getString("type");

        FlagController flagController= new FlagController();

        if (type.equals("create")){
            // take appropriate action to create new tuple in database
            // create a JSON object for the return message
            String auction_id = obj.getString("auction_id");
            String f_type= obj.getString("flag_type");
            String flag_desc = obj.getString("flag_desc");
            String user_id= obj.getString("user_id");


            UserAccountController uac=new UserAccountController();
            AuctionController ac = new AuctionController();
            //keep an eye on this
            Flag flag= new Flag(flag_type, uac.getUserById(Integer.parseInt(user_id)), ac.getAuctionById(Integer.parseInt(auction_id)));

            result= flagController.persistFlagOnAuction(flag);
            if(result.equals("true")){
                output.put("succeed",true);
            }else{
                output.put("succeed",false);
                output.put("Error",result);
            }

        }/* else if(type.equals("update")){
            // you get the idea
            String flag_type= obj.getString("flag_type");
            String flag_desc = obj.getString("flag_desc");
            String flag_id=obj.getString("flag_id");


            UserAccount user= new UserAccount(username,email, password_hash, first_name, last_name, user_description)
            result= userController.updateUserAccount(user);
            if(result.equals("true")){
                output.put("succeed",true);
            }else{
                output.put("succeed",false);
                output.put("Error",result);
            }
        }*/else if(type.equals("delete")){
            // you get the idea
            String flag_id=obj.getString("flag_id");
            FlagController fc = new FlagController();

            result= flagController.removeFlag(fc.getFlagById(Integer.parseInt(flag_id)));
            output.put("result",result);

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

        }*/else if(type.equals("index")){
            // you get the idea
            FlagController fc = new FlagController();
            String auction_id = obj.getString("auction_id");
            AuctionController ac = new AuctionController();
            List<Flag> list = flagController.getAllFlagsOnAuction(ac.getAuctionById(Integer.parseInt(auction_id)));
            JSONArray jsonArray = new JSONArray();
            for(Flag f:list){
                JSONObject ele= new JSONObject();
                ele.put("flag_type",f.getFlagType());
                ele.put("flag_id",f.getFlagId());
                ele.put("auction_id",f.getAuctionId());
                ele.put("getFlaggingUser",f.getFlaggingUser());
                jsonArray.put(ele);
            }
            output.put("flag",jsonArray);

        }
        return output;
    }


}