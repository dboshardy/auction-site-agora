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
        CategoryController categoryController = new CategoryController();
        UserAccountController userAccountController = new UserAccountController();

        if (type.equals("create")) {
            int user_id = obj.getInt("user_id");
            String auctionName = obj.getString("item_name");
            String item_desc = obj.getString("item_desc");
            int category_id = obj.getInt("category_id");
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

            boolean buyNow = obj.getBoolean("buy_it_now");
            Double buyNowPrice;
            Auction auction;

            if (buyNow) {
                buyNowPrice = obj.getDouble("buy_now_price");
                auction = new Auction(auctionName, user_id, startTime, endTime, item_desc, buyNowPrice, bidPrice, category_id);
            } else {
                auction = new Auction(auctionName, user_id, startTime, endTime, item_desc, bidPrice, category_id);
            }

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
            int category_id = obj.getInt("category");
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
            auction.setCategoryId(category_id);
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
        } else if (type.equals("categories")) {
            List<Category> list = categoryController.getAllCategories();
            JSONArray jsonArray = new JSONArray();
            for(Category c:list){
                JSONObject ele= new JSONObject();
                ele.put("category",c.getName());
                ele.put("category_id",c.getCategoryId());

                jsonArray.put(ele);
            }
            output.put("categories",jsonArray);

        } else if (type.equals("show")) {
            String auction_id = obj.getString("auction_id");
            Auction auction = auctionController.getAuctionById(Integer.parseInt(auction_id));
            output.put("item_name", auction.getAuctionName());
            output.put("auction_id",auction.getAuctionId());
            output.put("item_desc",auction.getDescription());

//            BidController bid = new BidController();
            Bid highestBid = auction.getCurrentHighestBid();

            output.put("highest_bid", highestBid.getBidAmount());
            output.put("buy_now_price",auction.getBuyItNowPrice());
            output.put("bidder_id",highestBid.getBidderId());
            output.put("bidder_username",highestBid.getBidder().getUserName());

            UserAccountController sellerController = new UserAccountController();
            UserAccount seller = sellerController.getUserById(auction.getSellerId());

            output.put("seller_id",seller.getUserId());
            output.put("seller_username",seller.getUserName());
            output.put("category",auction.getCategory().getName());
        } else if (type.equals("keyword")) {
            String keyword = obj.getString("keyword");
            List<Auction> list = auctionController.getAllAuctionsByKeyword(keyword);
            JSONArray jsonArray = new JSONArray();
            for(Auction a:list) {
                JSONObject ele = new JSONObject();
                ele.put("auction_id", a.getAuctionId());
                ele.put("auction_name", a.getAuctionName());
                ele.put("end_time", a.getEndTime());
                ele.put("item_desc", a.getDescription());

                BidController bid = new BidController();
                Bid highestBid = bid.getBidById(a.getCurrentHighestBidId());

                if (highestBid != null) {
                    ele.put("highest_bid", highestBid.getBidAmount());
                }
                jsonArray.put(ele);
            }
            output.put("auctions",jsonArray);
        } else if (type.equals("category")) {
            int category = obj.getInt("category");
            List<Auction> list = auctionController.getAllAuctionsByCategory(category);
            JSONArray jsonArray = new JSONArray();
            for(Auction a:list) {
                JSONObject ele = new JSONObject();
                ele.put("auction_id", a.getAuctionId());
                ele.put("auction_name", a.getAuctionName());
                ele.put("end_time", a.getEndTime());
                ele.put("item_desc", a.getDescription());

                BidController bid = new BidController();
                Bid highestBid = bid.getBidById(a.getCurrentHighestBidId());

                if (highestBid != null) {
                    ele.put("highest_bid", highestBid.getBidAmount());
                }
                jsonArray.put(ele);
            }
            output.put("auctions",jsonArray);
        } else if (type.equals("stop")) {
            int auction_id = obj.getInt("auction_id");
            Auction auction = auctionController.getAuctionById(auction_id);
            auction.setIsEnded(true);
            result = auctionController.updateAuction(auction);
            if (result.equals("true")) {
                output.put("succeed", true);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        } else if (type.equals("update_category")) {
            int category_id = obj.getInt("category_id");
            String name = obj.getString("category_name");
            Category category = categoryController.getCategoryById(category_id);
            category.setName(name);
            result = categoryController.updateCategory(category);
            if (result.equals("true")) {
                output.put("succeed", true);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        } else if (type.equals("delete_category")) {
            int category_id = obj.getInt("category_id");
            Category category = categoryController.getCategoryById(category_id);
            result = categoryController.deleteCategory(category);
            if (result.equals("true")) {
                output.put("succeed", true);
            } else {
                output.put("succeed", false);
                output.put("Error", result);
            }
        } else if (type.equals("auctions_bid_on")) {
            int user_id = obj.getInt("user_id");
            UserAccount user = userAccountController.getUserById(user_id);
            List<Auction> list = auctionController.getAllActiveAuctionsBidOnForUser(user);
            JSONArray jsonArray = new JSONArray();
            for(Auction a:list) {
                JSONObject ele = new JSONObject();
                ele.put("auction_id", a.getAuctionId());
                ele.put("auction_name", a.getAuctionName());
                ele.put("end_time", a.getEndTime());
                ele.put("item_desc", a.getDescription());

                BidController bid = new BidController();
                Bid highestBid = bid.getBidById(a.getCurrentHighestBidId());

                if (highestBid != null) {
                    ele.put("highest_bid", highestBid.getBidAmount());
                }
                jsonArray.put(ele);
            }
            output.put("auctions",jsonArray);
        }
        return output;
    }


}