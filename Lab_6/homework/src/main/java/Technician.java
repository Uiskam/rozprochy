import com.rabbitmq.client.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Technician {
    public static void main(String[] argv) throws Exception {
        Config config = new Config();

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        String EXCHANGE_NAME = config.getProperty("DOCTOR_TO_TECH_EXCHANGE");
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

        if (argv.length < 1) {
            System.err.println("Usage: Technician [knee|elbow|hip]+");
            System.exit(1);
        }

        Set<String> uniqueArgs = new HashSet<>(Arrays.asList(argv));
        if (uniqueArgs.size() != argv.length) {
            System.err.println("Duplicate values are not allowed.");
            System.exit(1);
        }

        for (String specialisation : argv) {
            if (!specialisation.equals("knee") && !specialisation.equals("elbow") && !specialisation.equals("hip")) {
                System.err.println("Usage: Technician [knee|elbow|hip]+");
                System.exit(1);
            }

            String fromDoctorQueueName = channel.queueDeclare(specialisation, false, false, false, null).getQueue();
            System.out.println("Binding to: " + specialisation);
            channel.queueBind(fromDoctorQueueName, EXCHANGE_NAME, specialisation);

            DeliverCallback deliverCallback = (consumerTag, delivery) -> {
                Random rand = new Random();
                String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
                System.out.println("Received on " + specialisation + ": " + message);
                try {
                    Thread.sleep(rand.nextInt(3000) + 2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                String doctorName = message.split(";")[0];
                String examinationType = message.split(";")[1];
                String patientName = message.split(";")[2];
                String toSentMessage = patientName + ";" + examinationType + ";done";
                channel.basicPublish(EXCHANGE_NAME, doctorName, null, toSentMessage.getBytes(StandardCharsets.UTF_8));

                System.out.println("Finished on " + specialisation + ": " + message);
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            };

            channel.basicQos(1);
            channel.basicConsume(fromDoctorQueueName, false, deliverCallback, consumerTag -> {});
        }

        System.out.println(" [*] Waiting for messages.");
    }
}
