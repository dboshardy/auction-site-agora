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
import java.util.ArrayList;
import java.util.List;

public class WatchlistQueue extends Message{

    public JSONObject createMessageBody(JSONObject obj){
        JSONObject output = new JSONObject();
        String result = "";
        String type = obj.getString("type");
        WatchlistModel watchlistModel = new WatchlistModel();

         if(type.equals("add")) {
            int auction_id = obj.getInt("auction_id");
            int user_id = obj.getInt("user_id");
            Watchlist watchlist = new Watchlist(user_id, auction_id);
            result = watchlist.addAuctionToWatchlist(user_id, auction_id);
            output.put("succeed", result);

        } else if(type.equals("delete")){
             int auction_id = obj.getInt("auction_id");
             int user_id = obj.getInt("user_id");
             Watchlist watchlist = new Watchlist(user_id, auction_id);
             result = watchlist.deleteWatchlist(user_id, auction_id);
             output.put("succeed", result);
         } else if(type.equals("deleteAuction")){
            String watchlist_name= obj.getString("watchlist_name");
            String auction_id = obj.getString("auction_id");
            AuctionController ac= new AuctionController();
            UserAccountController uac= new UserAccountController();
//            Watchlist watchlist = uac.getUserById(Integer.parseInt(user_id)).getWatchlist(watchlist_name);
//            result=watchlist.deleteAuctionFromWatchlist(ac.getAuctionById(Integer.parseInt(auction_id)));
//            output.put("succeed",result);
        }else if(type.equals("deleteWatchlist")){
//            String watchlist_name= obj.getString("watchlist_name");
//            user_id = obj.getString("user_id");
//            UserAccountController uac= new UserAccountController();
//            Watchlist watchlist = uac.getUserById(Integer.parseInt(user_id)).getWatchlist(watchlist_name);
//            result = watchlist.removeWatchlist();
//            output.put("succeed",result);
        }else if(type.equals("index")) {
            int user_id = obj.getInt("user_id");
            Watchlist watchlist = new Watchlist();
            ArrayList<Auction> list = watchlist.getWatchlists(user_id);
            JSONArray jsonArray = new JSONArray();
            for(Auction a:list){
                JSONObject ele= new JSONObject();
                ele.put("auction_id",a.getAuctionId());
                ele.put("auction_name", a.getAuctionName());
                jsonArray.put(ele);
            }
            output.put("auctions",jsonArray);

        }
        return output;
    }





}