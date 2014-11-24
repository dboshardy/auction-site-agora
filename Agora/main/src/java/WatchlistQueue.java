/**
 * Created by Miao on 11/22/14.
 */

import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;
import org.apache.activemq.transport.stomp.Stomp.Headers.*;
import org.json.*;

import java.lang.ClassCastException;
import java.lang.Double;
import java.lang.Integer;
import java.lang.String;
import java.util.Locale;

public class WatchlistQueue extends Message{

    public JSONObject createMessageBody(JSONObject obj){
        JSONObject output = new JSONObject();
        Boolean result= "";
        String type = obj.getString("type");

        if (type.equals("create")){
            // take appropriate action to create new tuple in database
            // create a JSON object for the return message
            String user_id = obj.getString("user_id");
            String auction_id = obj.getString("auction_id");
            String watchlist_name= obj.getString("watchlist_name");
            AuctionController ac= new AuctionController();

            Watchlist list = new Watchlist(new UserAccount(Integer.parseInt(user_id)), watchlist_name);
            result = watchlist.addAuctionToWatchlist(ac.getAuctionById(Integer.parseInt(auction_id)));
            output.put("succeed",result);

        } else if(type.equals("add")) {
            // you get the idea
            String watchlist_name= obj.getString("watchlist_name");
            String auction_id = obj.getString("auction_id");
            AuctionController ac= new AuctionController();
            UserAccountController uac= new UserAccountController();
            Watchlist list = uac.getUserById(Integer.parseInt(user_id)).getWatchlist(watchlist_name);
            result=watchlist.addAuctionToWatchlist(ac.getAuctionById(Integer.parseInt(auction_id)));
            output.put("succeed",result);

        }else if(type.equals("deleteAuction")){
            String watchlist_name= obj.getString("watchlist_name");
            String auction_id = obj.getString("auction_id");
            AuctionController ac= new AuctionController();
            UserAccountController uac= new UserAccountController();
            Watchlist list = uac.getUserById(Integer.parseInt(user_id)).getWatchlist(watchlist_name);
            result=watchlist.deleteAuctionFromWatchlist(ac.getAuctionById(Integer.parseInt(auction_id)));
            output.put("succeed",result);
        }else if(type.equals("deleteWatchlist")){
            String watchlist_name= obj.getString("watchlist_name");
            String user_id = obj.getString("user_id");
            UserAccountController uac= new UserAccountController();
            Watchlist list = uac.getUserById(Integer.parseInt(user_id)).getWatchlist(watchlist_name);
            result = watchlist.removeWatchlist();
            output.put("succeed",result);
        }else if(type.equals("index")) {
            // you get the idea
            String watchlist_name= obj.getString("watchlist_name");
            String auction_id = obj.getString("auction_id");
            AuctionController ac= new AuctionController();
            UserAccountController uac= new UserAccountController();
            Watchlist watchlist = uac.getUserById(Integer.parseInt(user_id)).getWatchlist(watchlist_name);
            ArrayList<Auction> list=watchlist.getWatchlist();
            JSONArray jsonArray = new JSONArray();
            AuctionController ac= new AuctionController();
            for(Auction a:list){
                JSONObject ele= new JSONObject();
                ele.put("watchlist_name",watchlist_name);
                ele.put("auction_id",ac.getAuctionId());
                jsonArray.put(ele);
            }
            output.put("auction",jsonArray);

        }
        return output;
    }





}