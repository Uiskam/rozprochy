import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Administrator {
    private static void sendToAll(Channel channel, String EXCHANGE_NAME) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        while (true) {
            BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("A message to all staff: ");
            String message = input.readLine();
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes(StandardCharsets.UTF_8));
        }
    }

    private static void receiveFromAll(Channel channel, String EXCHANGE_NAME) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");
        String queueName = "admin";
        channel.queueDeclare(queueName, false, false, false, null);
        channel.queueBind(queueName, EXCHANGE_NAME, "#");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received: " + message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });
    }

    public static void main(String[] argv) throws Exception {
        Config config = new Config();
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");

        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            receiveFromAll(channel, config.getProperty("DOCTOR_TO_TECH_EXCHANGE"));
            sendToAll(channel, config.getProperty("FROM_ADMIN_TO_ALL_EXCHANGE"));
        }
    }
}
