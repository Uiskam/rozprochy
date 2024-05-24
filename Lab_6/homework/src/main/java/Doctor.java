import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Doctor {
    private static String getInput() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        return input.readLine();
    }

    private static String getExaminationName() throws IOException {
        System.out.print("Enter examination type id [1: hip | 2: knee | 3: elbow | q: exit]: ");
        String examinationType = getInput();
        return switch (examinationType) {
            case "1" -> "hip";
            case "2" -> "knee";
            case "3" -> "elbow";
            case "q" -> "exit";
            default -> "unknown";
        };
    }

    private static void sendToTechnician(Channel channel, String doctorName, String EXCHANGE_NAME) throws IOException {
        while (true) {
            String examinationType = getExaminationName();
            if (examinationType.equals("exit")) {
                break;
            } else if (examinationType.equals("unknown")) {
                System.out.println("Unknown examination type");
                continue;
            }
            String patientName;
            System.out.print("Enter patient name: ");
            patientName = getInput();

            String message = doctorName + ";" + examinationType + ";" + patientName;
            channel.basicPublish(EXCHANGE_NAME, examinationType, null, message.getBytes(StandardCharsets.UTF_8));
        }
    }

    private static void receiveFromTechnician(Channel channel, String doctorName, String EXCHANGE_NAME) throws IOException {
        channel.queueDeclare(doctorName, false, false, false, null);
        channel.queueBind(doctorName, EXCHANGE_NAME, doctorName);
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("\nReceived: " + message);
        };
        channel.basicConsume(doctorName, true, deliverCallback, consumerTag -> {});
    }

    private static void receiveFromAdmin(Channel channel, String EXCHANGE_NAME) throws IOException {
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, EXCHANGE_NAME, "");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println("Received from admin: " + message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {});
    }

    public static void main(String[] argv) throws Exception {
        Config config = new Config();

        if (argv.length < 1) {
            System.out.println("Usage: Doctor <doctor_name>");
            System.exit(1);
        }
        String doctorName = argv[0];
        System.out.println("Doctor " + doctorName + " is ready to work");

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        String DOCTOR_TO_TECH_EXCHANGE = config.getProperty("DOCTOR_TO_TECH_EXCHANGE");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.exchangeDeclare(DOCTOR_TO_TECH_EXCHANGE, "topic");
            receiveFromTechnician(channel, doctorName, DOCTOR_TO_TECH_EXCHANGE);
            receiveFromAdmin(channel, config.getProperty("FROM_ADMIN_TO_ALL_EXCHANGE"));
            sendToTechnician(channel, doctorName, DOCTOR_TO_TECH_EXCHANGE);
        }
    }
}
