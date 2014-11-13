/**
 * Created by thomkel on 11/8/14.
 */

import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;
import org.apache.activemq.transport.stomp.Stomp.Headers.*;
import org.json.*;

public class SimpleJMS {

    private StompConnection stompConsumerConnection;
    private StompConnection stompProducerConnection;

    public void before() throws Exception {
        //create stompConsumerConnection
        System.out.println("Trying to create connection...");
        stompConsumerConnection = new StompConnection();
        stompConsumerConnection.open("localhost", 61613);
        System.out.println("Opened connection..");
        stompConsumerConnection.connect("system", "manager");
        stompConsumerConnection.subscribe("/queue/Auction", Subscribe.AckModeValues.CLIENT);

        //create stompProducerConnection
        stompProducerConnection = new StompConnection();
        stompProducerConnection.open("localhost", 61613);
        stompProducerConnection.connect("system", "manager");

    }

    public void run() throws Exception {
        // Consume message from queue
        stompConsumerConnection.begin("tx2");
        System.out.println("Consuming message..");
        StompFrame message = stompConsumerConnection.receive();
        String body = message.getBody();
        System.out.println(body);
        stompConsumerConnection.ack(message, "tx2");
        stompConsumerConnection.commit("tx2");
        stompConsumerConnection.disconnect();

        //parse body of message to get id
        JSONObject obj = new JSONObject(body);
        String id = obj.getString("id");

        JSONObject response = new JSONObject();
        response.put("id", id);

        // Produce response message
        stompProducerConnection.begin("tx1");
        stompProducerConnection.send("/queue/AuctionConfirm", response.toString());
        stompProducerConnection.commit("tx1");
        stompProducerConnection.disconnect();
    }

    public static void main(String[] args) {
        SimpleJMS example = new SimpleJMS();
        System.out.print("\n\n\n");
        System.out.println("Starting SimpleJMS example now...");
        try {
            example.before();
            example.run();
//            example.after();
        } catch (Exception e) {
            System.out.println("Caught an exception during the example: " + e.getMessage());
        }
        System.out.println("Finished running the SimpleJMS example.");
        System.out.print("\n\n\n");
    }
}