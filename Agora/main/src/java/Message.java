/**
 * Created by thomkel on 11/14/14.
 */
import org.json.*;

public abstract class Message {

    public JSONObject createResponseMessage(String body){
        JSONObject consumedMessage = new JSONObject(body);
        String id = consumedMessage.getString("id");

        JSONObject producedMessage = new JSONObject();
        producedMessage.put("id", id);
        producedMessage.put("response", createMessageBody(consumedMessage));

        return producedMessage;
    }

    public abstract JSONObject createMessageBody(JSONObject body);

}
