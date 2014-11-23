/**
 * Created by thomkel on 11/8/14.
 */

import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;
import org.apache.activemq.transport.stomp.Stomp.Headers.*;
import org.json.*;

import java.net.SocketTimeoutException;

public class QueueHandler implements Runnable{

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

    public void initializeQueue() throws Exception {
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

    public void run() {
        try {
            initializeQueue();
        } catch (Exception except){
            System.out.println("Cannot initialize queue" + except);
        }

        while(true) {
            // Consume message from queue
            String cMessageNum = "tx" + getConsumerMessageNum();
            try {
                stompConsumerConnection.begin(cMessageNum);
            } catch (Exception e) {
                System.out.println("Cannot begin StompConnection: " + e);
            }

            long timeoutWait = 1000;
            System.out.println("Consuming message..");
            String body = null;

            while (body == null) {
                try {
                    StompFrame message = stompConsumerConnection.receive(timeoutWait);
                    body = message.getBody();
                    System.out.println("Message body: " + body);
                    stompConsumerConnection.ack(message, cMessageNum);
                    stompConsumerConnection.commit(cMessageNum);
                    setConsumerMessageNum(getConsumerMessageNum() + 2);

                    //parse body of message to get id
//                    JSONObject JSONobj = new JSONObject(body);
//                    String id = JSONobj.getString("id");

                    MessageFactory messageFactory = new MessageFactory();
//                   System.out.println("getting message class of " + mConsumerQueue);
                    Message messageClass = messageFactory.getMessageClass(mConsumerQueue);
//                    System.out.println("Getting response from " + messageClass);
                    JSONObject response = messageClass.createResponseMessage(body);

                    // Produce response message
                    System.out.println("Producing message: ");
                    String pMessageNum = "tx" + getProducerMessageNum();
                    stompProducerConnection.begin(pMessageNum);
                    stompProducerConnection.send(mProducerQueue, response.toString());
                    stompProducerConnection.commit(pMessageNum);
                    setProducerMessageNum(getProducerMessageNum() + 2);

                    System.out.println("Sending message" + response);

                } catch (Exception e) {
                    //System.out.println("No message found");

                    try {
                        Thread.sleep(200);
                    } catch (InterruptedException ex) {
                        System.out.println("Cannot sleep: " + ex);
                    }

                }
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