/**
 * Created by Miao on 11/8/14.
 */

import org.json.JSONObject;

import java.lang.Double;
import java.lang.Integer;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

public class AuctionQueue extends Message {

    public JSONObject createMessageBody(JSONObject obj) {
        JSONObject output = new JSONObject();
        String result = "";
        String type = obj.getString("type");

        AuctionController auctionController = new AuctionController();

        if (type.equals("create")) {
            // take appropriate action to create new tuple in database
            // create a JSON object for the return message
            String user_id = obj.getString("user_id");
            String auctionName = obj.getString("auctionName");
            String item_desc = obj.getString("item_desc");
            String auction_start_time = obj.getJSONObject("auction_start_time");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(auction_start_time);
            Timestamp timestamp = new Timestamp(date.getTime());

            String auction_length= obj.getJSONObject("auction_length");
            SimpleDateFormat sdf_l = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_l = sdf.parse(auction_length);
            Timestamp timestamp_l = new Timestamp(date_l.getTime());

            Date s_date = new Date(timestamp.getTime()+timestamp_l.getTime());

            String start_bid = obj.getString("start_bid");
            Double bidPrice = Double.parseDouble(start_bid);

            Auction auction = new Auction(auctionName, Integer.parseInt(user_id),item_desc,bidPrice,s_date);
            result = auctionController.persistAuction(auction);
            output.put("result", result);
            if (result.equals("true")) {
                output.put("succeed", true);
                output.put("auction_id", auction.getAuctionId());
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        } else if (type.equals("update")) {
            // you get the idea
            String user_id = obj.getString("user_id");
            String auction_id = obj.getString("auction_id");
            String buy_it_now = obj.getString("buy_it_now");
            String item_desc = obj.getString("item_desc");
            String auction_start_time = obj.getJSONObject("auction_start_time");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = sdf.parse(auction_start_time);
            Timestamp timestamp = new Timestamp(date.getTime());

            String auction_length= obj.getJSONObject("auction_length");
            SimpleDateFormat sdf_l = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_l = sdf.parse(auction_length);
            Timestamp timestamp_l = new Timestamp(date_l.getTime());

            Date s_date = new Date(timestamp.getTime()+timestamp_l.getTime());

            String start_bid = obj.getString("start_bid");
            Double bidPrice = Double.parseDouble(start_bid);

            Auction auction = auctionController.getAuctionById(Integer.parseInt(auction_id));
            auction.setBuyItNowPrice(Double.parseDouble(buy_it_now));
            auction.setEndTime(s_date);

            result = auctionController.updateAuction(auction);
            output.put("result", result);
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
        } else if (type.equals("index")) {
            // you get the idea
            String user_id = obj.getString("user_id");
            List<Auction> list = auctionController.getAllAuctionsByUserId(Integer.parseInt(user_id));
            Auction auction = auctionController.getAuctionById(Integer.parseInt(auction_id));
            JSONArray jsonArray = new JSONArray();
            for(Auction a:list){
                JSONObject ele= new JSONObject();
                ele.put("auction_id",a.getAuctionId());
                ele.put("item_desc",a.getDescription());
                ele.put("highest_bid",a.getCurrentHighestBid().getBidAmount());
                jsonArray.put(ele);
            }
            output.put("auctions",jsonArray);
        }else if (type.equals("show")) {
            // you get the idea
            String auction_id = obj.getString("auction_id");
            output.put("auction_id", auction_id);
            Auction auction = auctionController.getAuctionById(Integer.parseInt(auction_id));
            //ArrayList<Bid> list= auctionController.getAuctionBids(auction)
            output.put("auction_id",auction.getAuctionId());
            output.put("item_desc",auction.getDescription());
            output.put("highest_bid",auction.getCurrentHighestBid().getBidAmount());
            output.put("buy_now_price",auction.getBuyItNowPrice());
            output.put("bidder_id",auction.getCurrentHighestBid().getBidderId());
            output.put("bidder_username",auction.getCurrentHighestBid().getBidderId().getUserName());
            output.put("seller_id",auction_id.getSellerId());
            output.put("seller_username",auction.getSellerId().getUserName());
        }
        return output;
    }


}