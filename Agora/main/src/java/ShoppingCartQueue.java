/**
 * Created by Miao on 11/22/14.
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ShoppingCartQueue extends Message{

    public JSONObject createMessageBody(JSONObject obj){
        JSONObject output = new JSONObject();
        String result= "";
        String type = obj.getString("type");

        ShoppingCart shoppingCart= new ShoppingCart();
        AuctionController auctionController= new AuctionController();
        if (type.equals("create")){
            // take appropriate action to create new tuple in database
            // create a JSON object for the return message
            String user_id = obj.getString("user_id");
            ArrayList<Auction> list= shoppingCart.getShoppingCart(Integer.parseInt(user_id));
            JSONArray jsonArray = new JSONArray();
            for(Auction a:list){
                JSONObject ele= new JSONObject();
                ele.put("auction_id",a.getAuctionId());
                ele.put("auction_name",a.getAuctionName());

                jsonArray.put(ele);
            }
            output.put("auctions",jsonArray);

        }else if(type.equals("delete")){
            // you get the idea
            String auction_id = obj.getString("auction_id");
            result= shoppingCart.removeAuctionFromCart(auctionController.getAuctionById(Integer.parseInt(auction_id)));
            if(result.equals("true")){
                output.put("succeed",true);
            }else{
                output.put("succeed",false);
                output.put("Error",result);
            }
        }else if(type.equals("show")){
            // you get the idea
            int user_id = obj.getInt("user_id");
            if(result.equals("true")){
                output.put("succeed",true);
            }else{
                output.put("succeed",false);
                output.put("Error",result);
            }
        }
        return output;
    }





}