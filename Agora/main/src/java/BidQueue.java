/**
 * Created by Miao on 11/22/14.
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

        UserAccountController userAccountController = new UserAccountController();
        BidController bidController= new BidController();
        AuctionController auctionController= new AuctionController();
        Auction auction;

        if (type.equals("create")){
            String user_id = obj.getString("user_id");
            String auction_id= obj.getString("auction_id");
            String start_bid= obj.getString("start_bid");
            Double bidAmount= Double.parseDouble(start_bid);

            Bid bid = new Bid(userAccountController.getUserById(Integer.parseInt(user_id)), auctionController.getAuctionById(Integer.parseInt(auction_id)), new BigDecimal(bidAmount));
            result = bidController.persistBid(bid);
            output.put("result",result);
            if(result.equals("true")){
                output.put("succeed",true);
            }else{
                output.put("succeed",false);
                output.put("Error",result);
            }
        } else if(type.equals("new")){
            int user_id = obj.getInt("user_id");
            int auction_id= obj.getInt("auction_id");
            Double bid_amount = obj.getDouble("bid_amount");

            // useraccount, bid, auction
            UserAccount user = userAccountController.getUserById(user_id);
            Bid bid = new Bid(user, auctionController.getAuctionById(auction_id),
                    new BigDecimal(bid_amount));
            auction = auctionController.getAuctionById(auction_id);

            result = userAccountController.placeBidOnAuction(user, bid, auction);
            output.put("result",result);

            if(result.equals("true")){
                output.put("succeed",true);
            }else{
                output.put("succeed",false);
                output.put("Error",result);
            }
        } else if(type.equals("show_auction")){

            int auction_id = obj.getInt("auction_id");

            auction= auctionController.getAuctionById(auction_id);
            List<Bid> bids= auctionController.getAuctionBids(auction);
            JSONArray jsonArray = new JSONArray();
            for(Bid b:bids){
                JSONObject ele= new JSONObject();
                ele.put("auction_id",b.getAuctionId());
                ele.put("bidder_id",b.getBidderId());
                ele.put("bidder_username", b.getBidder().getUserName());
                ele.put("bid_amount",b.getBidAmount());
                jsonArray.put(ele);
            }
            output.put("bids",jsonArray);
        }else if(type.equals("show_user")){

            int user_id = obj.getInt("user_id");

            List<Bid> bids = bidController.getBidHistoryForUser(user_id);
            JSONArray jsonArray = new JSONArray();
            for(Bid b:bids){
                JSONObject ele= new JSONObject();
                ele.put("auction_id",b.getAuctionId());
                ele.put("bidder_id",b.getBidderId());
                ele.put("bidder_username", b.getBidder().getUserName());
                ele.put("bid_amount",b.getBidAmount());
                jsonArray.put(ele);
            }
            output.put("bids",jsonArray);
        }
        return output;
    }





}