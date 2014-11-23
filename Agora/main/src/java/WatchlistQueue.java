/**
 * Created by Miao on 11/22/14.
 */

import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;
import org.apache.activemq.transport.stomp.Stomp.Headers.*;
import org.json.*;

import java.lang.ClassCastException;
import java.lang.Double;
import java.lang.String;
import java.util.Locale;

public class WatchlistQueue extends Message{

    public JSONObject createMessageBody(JSONObject obj){
        JSONObject output = new JSONObject();
        Boolean result= "";
        String type = obj.getString("type");

        WatchlistModel watchlistModel= new WatchlistModel();

        if (type.equals("create")){
            // take appropriate action to create new tuple in database
            // create a JSON object for the return message
            String user_id = obj.getString("user_id");
            String watchlist_name= obj.getString("watchlist_name");
            Watchlist list = new Watchlist(new UserAccount(Integer.parseInt(user_id)), watchlist_name);
            result = watchlistModel.createWatchlist(list);
            output.put("succeed",result);

        } /*else if(type.equals("update")){
            // you get the idea

        }*/else if(type.equals("delete")){
            String watchlist_name= obj.getString("watchlist_name");
            String watchlist_id= obj.getString("watchlist_id");
            Watchlist list = new Watchlist(watchlist_name,Integer.parseInt(watchlist_id));
            result = watchlistModel.removeWatchlist(list);
            output.put("succeed",result);
        }/*else if(type.equals("show")){
            // you get the idea

        }*/
        return output;
    }





}