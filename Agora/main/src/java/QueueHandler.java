/**
 * Created by thomkel on 11/8/14.
 */

import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;
import org.apache.activemq.transport.stomp.Stomp.Headers.*;
import org.json.*;

import java.net.SocketTimeoutException;

public class QueueHandler {

    private StompConnection stompConsumerConnection;
    private StompConnection stompProducerConnection;
    private String mConsumerQueue;
    private String mProducerQueue;
    private int mProducerMessageNum;
    private int mConsumerMessageNum;

    public QueueHandler(String consumerQueue, String producerQueue) {
        stompConsumerConnection = new StompConnection();
        stompProducerConnection = new StompConnection();
        mProducerMessageNum = 1;
        mConsumerMessageNum = 2;

        String cQueue = "/queue/" + consumerQueue;
        String pQueue = "/queue/" + producerQueue;

        this.mConsumerQueue = cQueue;
        this.mProducerQueue = pQueue;
    }

    public void initializeQueues() throws Exception {
        //create stompConsumerConnection
        System.out.println("Trying to create connection for " + mConsumerQueue + "...");
        stompConsumerConnection.open("localhost", 61613);
        System.out.println("Opened connection..");
        stompConsumerConnection.connect("system", "manager");
        stompConsumerConnection.subscribe(mConsumerQueue, Subscribe.AckModeValues.CLIENT);

        //create stompProducerConnection
        stompProducerConnection.open("localhost", 61613);
        stompProducerConnection.connect("system", "manager");

    }

    public void runQueues() throws Exception {
        // Consume message from queue
        String cMessageNum = "tx" + getConsumerMessageNum();
        stompConsumerConnection.begin(cMessageNum);
        long timeoutWait = 1000;
        System.out.println("Consuming message..");
        String body = null;

        while(body == null) {
            try {
                StompFrame message = stompConsumerConnection.receive(timeoutWait);
                body = message.getBody();
                System.out.println(body);
                stompConsumerConnection.ack(message, cMessageNum);
                stompConsumerConnection.commit(cMessageNum);
                //stompConsumerConnection.disconnect();
                setConsumerMessageNum(getConsumerMessageNum() + 2);

                //parse body of message to get id
                JSONObject obj = new JSONObject(body);
                String id = obj.getString("id");

                JSONObject response = new JSONObject();
                response.put("id", id);

                // Produce response message
                String pMessageNum = "tx" + getProducerMessageNum();
                stompProducerConnection.begin(pMessageNum);
                stompProducerConnection.send(mProducerQueue, response.toString());
                stompProducerConnection.commit(pMessageNum);
                //stompProducerConnection.disconnect();
                setProducerMessageNum(getProducerMessageNum() + 2);

            } catch (SocketTimeoutException e) {
                System.out.println("No message found");
                Thread.sleep(1000);
            }
        }

    }

    public int getProducerMessageNum() {
        return mProducerMessageNum;
    }

    public void setProducerMessageNum(int mProducerMessageNum) {
        this.mProducerMessageNum = mProducerMessageNum;
    }

    public int getConsumerMessageNum() {
        return mConsumerMessageNum;
    }

    public void setConsumerMessageNum(int mConsumerMessageNum) {
        this.mConsumerMessageNum = mConsumerMessageNum;
    }
}