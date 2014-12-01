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
        UserAccountController userAccountController = new UserAccountController();
        AuctionController auctionController = new AuctionController();

        if (type.equals("create")){
            // take appropriate action to create new tuple in database
            // create a JSON object for the return message
            int auction_id = obj.getInt("auction_id");
            String flag_type= obj.getString("flag_type");
            int user_id= obj.getInt("user_id");

            UserAccount user = userAccountController.getUserById(user_id);
            Auction auction = auctionController.getAuctionById(auction_id);

            Flag flag = new Flag(flag_type, user, auction);

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

        }else if(type.equals("index")){
            List<Flag> list = flagController.getAllFlags();
            JSONArray jsonArray = new JSONArray();
            for(Flag f:list){
                JSONObject ele= new JSONObject();
                ele.put("auction_id",f.getAuctionId());
                ele.put("flag_type",f.getFlagType());
                ele.put("flag_id",f.getFlagId());
                jsonArray.put(ele);
            }
            output.put("flags",jsonArray);

        }
        return output;
    }


}