/**
 * Created by Miao on 11/8/14.
 */

import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class AuctionQueue extends Message {

    public JSONObject createMessageBody(JSONObject obj) {
        JSONObject output = new JSONObject();
        String result = "";
        String type = obj.getString("type");

        AuctionController auctionController = new AuctionController();
        UserAccountController uac= new UserAccountController();
        if (type.equals("create")) {
            // take appropriate action to create new tuple in database
            // create a JSON object for the return message
            String user_id = obj.getString("user_id");
            String auctionName = obj.getString("auctionName");
            String item_desc = obj.getString("item_desc");
            JSONObject auction_start_time = obj.getJSONObject("auction_start_time");
            Timestamp stamp = new Timestamp(auction_start_time.getLong("auction_start_time"));
            Date date = new Date(stamp.getTime());
            String start_bid = obj.getString("start_bid");
            Double bidPrice = Double.parseDouble(start_bid);

            Auction auction = new Auction(auctionName, Integer.parseInt(user_id),item_desc,new BigDecimal(bidPrice),date);
            result = auctionController.persistAuction(auction);

            output.put("result", result);
            if (result.equals("true")) {
                output.put("auction_id",auction.getAuctionId());
                output.put("succeed", true);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        } else if (type.equals("update")) {
            // you get the idea
            String auction_id = obj.getString("auction_id");
            String seller_id = obj.getString("seller_id");
            String auctionName = obj.getString("auctionName");
            String item_desc = obj.getString("item_desc");
            JSONObject endTime = obj.getJSONObject("endTime");
            Timestamp stamp = new Timestamp(endTime.getLong("endTime"));
            Date date = new Date(stamp.getTime());
            String start_bid = obj.getString("start_bid");
            Double bidPrice = Double.parseDouble(start_bid);

            Auction auction = new Auction(auctionName, Integer.parseInt(seller_id),item_desc,new BigDecimal(start_bid),date);
            result = auctionController.updateAuction(auction);
            if (result.equals("true")) {
                output.put("succeed", true);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        } else if (type.equals("delete")) {
            // you get the idea
            String auction_id = obj.getString("auction_id");
            output.put("auction_id", auction_id);
            Auction auction = auctionController.getAuctionById(Integer.parseInt(auction_id));
            result = auctionController.deleteAuction(auction);
            if (result.equals("true")) {
                output.put("succeed", true);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        } else if (type.equals("show")) {
            // you get the idea
            String auction_id = obj.getString("auction_id");
            output.put("auction_id", auction_id);
            Auction auction = auctionController.getAuctionById(Integer.parseInt(auction_id));
            //ArrayList<Bid> list= auctionController.getAuctionBids(auction)
            output.put("auction", auction);
        }else if (type.equals("index")) {
            // you get the idea

            String user_id = obj.getString("user_id");
            output.put("user_id", user_id);
            List<Auction> list=auctionController.getAllAuctionsByUserId(Integer.parseInt(user_id));
            //BidController bidController=new BidController();
            JSONArray jsonArray = new JSONArray();
            for(Auction a:list){
                JSONObject ele= new JSONObject();
                ele.put("auction_id", a.getAuctionId());
                ele.put("auction_name",a.getAuctionName());
                ele.put("highest_bid",a.getCurrentHighestBid().getBidAmount());
                ele.put("buyItNowPrice",a.getBuyItNowPrice());
                jsonArray.put(ele);
            }
            output.put("auctions",jsonArray);
        }
        return output;
    }


}