import org.json.JSONArray;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Calendar;

public class AuctionQueue extends Message {

    public JSONObject createMessageBody(JSONObject obj) {
        JSONObject output = new JSONObject();
        String result = "";
        String type = obj.getString("type");

        AuctionController auctionController = new AuctionController();

        if (type.equals("create")) {
            // take appropriate action to create new tuple in database
            // create a JSON object for the return message
            int user_id = obj.getInt("user_id");
            String auctionName = obj.getString("item_name");
            String item_desc = obj.getString("item_desc");
            //String auction_start_time = String.valueOf(obj.getJSONObject("auction_start_time"));

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

//            Calendar cal = new GregorianCalendar(year, month, day, hour, minutes);
//
//            Timestamp startTime = new Timestamp(cal.getTimeInMillis());

            int auction_length= obj.getInt("auction_length");
//
//            cal.add(Calendar.DAY_OF_MONTH, auction_length);
//            Timestamp endTime = new Timestamp(cal.getTimeInMillis());

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
        } /*else if (type.equals("update")) {
            // you get the idea
            String user_id = obj.getString("user_id");
            String auction_id = obj.getString("auction_id");
            String buy_it_now = obj.getString("buy_it_now");
            String item_desc = obj.getString("item_desc");
            String auction_start_time = String.valueOf(obj.getJSONObject("auction_start_time"));
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            try {
                date = sdf.parse(auction_start_time);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Timestamp timestamp = new Timestamp(date.getTime());

            String auction_length= String.valueOf(obj.getJSONObject("auction_length"));
            SimpleDateFormat sdf_l = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date_l = null;
            try {
                date_l = sdf.parse(auction_length);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Timestamp timestamp_l = new Timestamp(date_l.getTime());

            Date s_date = new Date(timestamp.getTime()+timestamp_l.getTime());

            String start_bid = obj.getString("start_bid");
            Double bidPrice = Double.parseDouble(start_bid);

            Auction auction = auctionController.getAuctionById(Integer.parseInt(auction_id));
            auction.setBuyItNowPrice(new BigDecimal(buy_it_now));
            auction.setEndTime(s_date);

            result = auctionController.updateAuction(auction); output.put("result", result);
            if (result.equals("true")) {
                output.put("succeed", true);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        }*/ else if (type.equals("delete")) {
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
            int user_id = obj.getInt("user_id");
            List<Auction> list = auctionController.getAllAuctionsByUserId(user_id);
//            String auction_id = obj.getString("auction_id");
//            Auction auction = auctionController.getAuctionById(Integer.parseInt(auction_id));
            JSONArray jsonArray = new JSONArray();
            for(Auction a:list){
                JSONObject ele= new JSONObject();
                ele.put("auction_id",a.getAuctionId());
                ele.put("auction_name",a.getAuctionName());
                ele.put("end_time", a.getEndTime());
                ele.put("item_desc",a.getDescription());
//                ele.put("highest_bid",a.getCurrentHighestBid().getBidAmount());
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
//            output.put("highest_bid",auction.getCurrentHighestBid().getBidAmount());
            output.put("buy_now_price",auction.getBuyItNowPrice());
//            output.put("bidder_id",auction.getCurrentHighestBid().getBidderId());
//            output.put("bidder_username",auction.getCurrentHighestBid().getBidder().getUserName());
//            output.put("seller_id",auction.getSellerId());
//            output.put("seller_username",auction.getSeller().getUserName());
        }
        return output;
    }


}