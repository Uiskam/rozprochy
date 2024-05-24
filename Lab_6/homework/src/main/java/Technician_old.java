import com.rabbitmq.client.*;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Technician_old {
    public static void main(String[] argv) throws Exception {
        Config config = new Config();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String from_doctor_exchange = config.getProperty("DOCTOR_TO_TECH_EXCHANGE");
        channel.exchangeDeclare(from_doctor_exchange, BuiltinExchangeType.DIRECT);


        if (argv.length < 1) {
            System.err.println("Usage: Technician [knee|elbow|hip]+");
            System.exit(1);
        }

        Set<String> uniqueArgs = new HashSet<>(Arrays.asList(argv));
        if (uniqueArgs.size() != argv.length) {
            System.err.println("Duplicate values are not allowed.");
            System.exit(1);
        }

        String queueName = channel.queueDeclare().getQueue();
        for (String specialisation : argv) {
            if (!specialisation.equals("knee") && !specialisation.equals("elbow") && !specialisation.equals("hip")) {
                System.err.println("Usage: Technician [knee|elbow|hip]+");
                System.exit(1);
            }
            System.out.println("Binding to: " + specialisation);
            channel.queueBind(queueName, from_doctor_exchange, specialisation);
        }
        System.out.println(" [*] Waiting for messages.");


        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            Random rand = new Random();
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            //System.out.println(" Received '" + delivery.getEnvelope().getRoutingKey() + "':'" + message + "'");
            System.out.println("Received:" + message);
            try {
                Thread.sleep(rand.nextInt(3000) + 2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished:" + message);
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };

        channel.basicQos(1);
        channel.basicConsume(queueName, false, deliverCallback, consumerTag -> {
        });
    }
}
