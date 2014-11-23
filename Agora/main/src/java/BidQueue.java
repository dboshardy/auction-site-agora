/**
 * Created by Miao on 11/8/14.
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.List;

public class BidQueue extends Message{

    public JSONObject createMessageBody(JSONObject obj){
        JSONObject output = new JSONObject();
        String result= "";
        String type = obj.getString("type");

        BidController bidController= new BidController();
        AuctionController auctionController= new AuctionController();

        if (type.equals("create")){
            // take appropriate action to create new tuple in database
            // create a JSON object for the return message
            String user_id = obj.getString("user_id");
            String auction_id= obj.getString("auction_id");
//            JSONObject endTime=obj.getJSONObject("endTime");
//            Timestamp stamp = new Timestamp(endTime.getLong("endTime"));
//            Date date = new Date(stamp.getTime());
            String start_bid= obj.getString("start_bid");
            Double bidAmount= Double.parseDouble(start_bid);

            UserAccountController userAccountController = new UserAccountController();
            Bid bid = new Bid(userAccountController.getUserById(Integer.parseInt(user_id)), auctionController.getAuctionById(Integer.parseInt(auction_id)), new BigDecimal(bidAmount));
            result = bidController.persistBid(bid);
            output.put("result",result);
            if(result.equals("true")){
                output.put("succeed",true);
            }else{
                output.put("succeed",false);
                output.put("Error",result);
            }
        }else if(type.equals("show")){
            // you get the idea
            String auction_id = obj.getString("auction_id");
            String user_id = obj.getString("user_id");

            Auction auction= auctionController.getAuctionById(Integer.parseInt(auction_id));
            List<Bid> bids= auctionController.getAuctionBids(auction);
            JSONArray jsonArray = new JSONArray();
            for(Bid b:bids){
                JSONObject ele= new JSONObject();
                ele.put("auction_id",b.getAuctionId());
                ele.put("bidder_id",b.getBidderId());
                ele.put("bid_amount",b.getBidAmount());
                jsonArray.put(ele);
            }
            output.put("bids",jsonArray);
        }
        return output;
    }





}