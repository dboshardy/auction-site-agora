/**
 * Created by thomkel on 11/8/14.
 */

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.transport.stomp.Stomp;
import org.apache.activemq.transport.stomp.StompCodec;
import org.apache.activemq.transport.stomp.StompConnection;
import org.apache.activemq.transport.stomp.StompFrame;
import org.apache.activemq.transport.stomp.Stomp.Headers.*;

import javax.jms.*;

public class SimpleJMS {

    private final String connectionUri = "tcp://localhost:61616";
    private ActiveMQConnectionFactory connectionFactory;
    private StompConnection stompConnection;
    private Connection connection;
    private Session session;
    //private Destination destination;
    private Destination auctionQueue;

    public void before() throws Exception {
//        connectionFactory = new ActiveMQConnectionFactory(connectionUri);
//        connection = connectionFactory.createConnection();
//        connection.start();
//        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
//        //destination = session.createQueue("SimpleJMSQueue");  //This is our message "destination"
//        auctionQueue = session.createQueue("Auction");

        //create stompConnection
        System.out.println("Trying to create connection...");
        stompConnection = new StompConnection();
        stompConnection.open("localhost", 61613);
        System.out.println("Opened connection..");
        stompConnection.connect("system", "manager");
        stompConnection.subscribe("/queue/Auction", Subscribe.AckModeValues.CLIENT);
        stompConnection.begin("tx2");
//        StompFrame message = stompConnection.receive();
//        System.out.println(message.getBody());
//        stompConnection.ack(message, "tx2");
//        stompConnection.commit("tx2");

    }

    public void after() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public void run() throws Exception {
        //Create a message producer, which knows where to send the message:  to "destination"
//        MessageProducer producer = session.createProducer(destination);
//        try {
//            TextMessage message = session.createTextMessage();
//            message.setText("Welcome to activemq and JMS fellow UofC Student!");
//            System.out.println("Sending a welcome message to the ActiveMQ destination queue 'SimpleJMSQueue'");
//            producer.send(message); //SEND a test message to the destination queue "SimpleJMSQueue"
//            System.out.println("Just sent the message to SimpleJMSQueue...might want to go and check to make sure it's there...");
//            System.out.println("Press any key to continue..."); //pause so we can check to see if the message is in ActiveMQ...
//            System.in.read();
//        } finally {
//            producer.close();
//        }

//        MessageConsumer consumer = session.createConsumer(auctionQueue);
//        System.out.println("Consumer created");
//        try {
//            Message message = consumer.receive();  //RECEIVE the message from the queue
//            System.out.println("Here's the message we received: " + message);
//        } finally {
//            consumer.close();
//        }

        System.out.println("Consuming message..");
        StompFrame message = stompConnection.receive();
        System.out.println(message.getBody());
        stompConnection.ack(message, "tx2");
        stompConnection.commit("tx2");
        stompConnection.disconnect();
    }

    public static void main(String[] args) {
        SimpleJMS example = new SimpleJMS();
        System.out.print("\n\n\n");
        System.out.println("Starting SimpleJMS example now...");
        try {
            example.before();
            example.run();
            example.after();
        } catch (Exception e) {
            System.out.println("Caught an exception during the example: " + e.getMessage());
        }
        System.out.println("Finished running the SimpleJMS example.");
        System.out.print("\n\n\n");
    }
}