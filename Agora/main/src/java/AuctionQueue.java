import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Calendar;

public class AuctionQueue extends Message {

    public JSONObject createMessageBody(JSONObject obj) {
        JSONObject output = new JSONObject();
        String result = "";
        String type = obj.getString("type");

        AuctionController auctionController = new AuctionController();

        if (type.equals("create")) {
            int user_id = obj.getInt("user_id");
            String auctionName = obj.getString("item_name");
            String item_desc = obj.getString("item_desc");
            JSONObject start_time = obj.getJSONObject("auction_start_time");
            Integer year = start_time.getInt("year");
            Integer month = start_time.getInt("month");
            Integer day = start_time.getInt("day");
            Integer hour = start_time.getInt("hour");
            Integer minutes = start_time.getInt("minutes");

            String auction_start_time = year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":00";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = null;
            try {
                startTime = sdf.parse(auction_start_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int auction_length= obj.getInt("auction_length");

            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            cal.add(Calendar.DATE, auction_length);

            Date endTime = cal.getTime();

            Double bidPrice = obj.getDouble("start_bid");
            Double buyNowPrice = obj.getDouble("buy_now_price");

            Auction auction = new Auction(auctionName, user_id, startTime, endTime, item_desc, buyNowPrice, bidPrice);
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
            int auction_id = obj.getInt("auction_id");

            Auction auction = auctionController.getAuctionById(auction_id);

            String auctionName = obj.getString("item_name");
            String item_desc = obj.getString("item_desc");
            JSONObject start_time = obj.getJSONObject("auction_start_time");
            Integer year = start_time.getInt("year");
            Integer month = start_time.getInt("month");
            Integer day = start_time.getInt("day");
            Integer hour = start_time.getInt("hour");
            Integer minutes = start_time.getInt("minutes");

            String auction_start_time = year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":00";

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date startTime = null;
            try {
                startTime = sdf.parse(auction_start_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            int auction_length= obj.getInt("auction_length");

            Calendar cal = Calendar.getInstance();
            cal.setTime(startTime);
            cal.add(Calendar.DATE, auction_length);

            Date endTime = cal.getTime();

            Double bidPrice = obj.getDouble("start_bid");
            Double buyNowPrice = obj.getDouble("buy_now_price");

            auction.setAuctionName(auctionName);
            auction.setListTime(startTime);
            auction.setEndTime(endTime);
            auction.setDescription(item_desc);
            auction.setBuyItNowPrice(new BigDecimal(buyNowPrice));
            result = auctionController.updateAuction(auction);
            output.put("result", result);
            if (result.equals("true")) {
                output.put("succeed", true);
                output.put("auction_id", auction.getAuctionId());
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }

            BidController bidController = new BidController();
            Bid bid = bidController.getBidById(auction.getCurrentHighestBidId());
            bid.setBidAmount(new BigDecimal(bidPrice));
            bidController.updateBid(bid);

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
            int user_id = obj.getInt("user_id");
            List<Auction> list = auctionController.getAllAuctionsByUserId(user_id);
            JSONArray jsonArray = new JSONArray();
            for(Auction a:list){
                JSONObject ele= new JSONObject();
                ele.put("auction_id",a.getAuctionId());
                ele.put("auction_name",a.getAuctionName());
                ele.put("end_time", a.getEndTime());
                ele.put("item_desc",a.getDescription());

                BidController bid = new BidController();
                Bid highestBid = bid.getBidById(a.getCurrentHighestBidId());

                if (highestBid != null) {
                    ele.put("highest_bid", highestBid.getBidAmount());
                }
                jsonArray.put(ele);
            }
            output.put("auctions",jsonArray);
        }else if (type.equals("show")) {
            String auction_id = obj.getString("auction_id");
            Auction auction = auctionController.getAuctionById(Integer.parseInt(auction_id));
            output.put("item_name", auction.getAuctionName());
            output.put("auction_id",auction.getAuctionId());
            output.put("item_desc",auction.getDescription());

            BidController bid = new BidController();
            Bid highestBid = bid.getBidById(auction.getCurrentHighestBidId());

            output.put("highest_bid", highestBid.getBidAmount());
            output.put("buy_now_price",auction.getBuyItNowPrice());
            output.put("bidder_id",highestBid.getBidderId());
            output.put("bidder_username",highestBid.getBidder().getUserName());

            UserAccountController sellerController = new UserAccountController();
            UserAccount seller = sellerController.getUserById(auction.getSellerId());

            output.put("seller_id",seller.getUserId());
            output.put("seller_username",seller.getUserName());
        }
        return output;
    }


}