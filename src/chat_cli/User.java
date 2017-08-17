package chat_cli;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class User {

	public final static String SEPARATOR = "123456789";
	public static String sendTo = "";

	public static Scanner scanner;

	public static String msg = "";

	public static String user = "";

	public static void main(String[] argv) throws Exception {

		scanner = new Scanner(System.in);

		System.out.println("User: ");
		user = scanner.nextLine();

		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("wombat.rmq.cloudamqp.com");
		factory.setUsername("tradflan");
		factory.setPassword("qfWbjO_c1Lu05JgpxraRsn4ouelMmfuW");
		factory.setVirtualHost("tradflan");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(user, false, false, false, null);
		// System.out.println(" [*] Waiting for messages. To exit press
		// CTRL+C");

		Consumer consumer = new DefaultConsumer(channel) {
			@Override
			public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties,
					byte[] body) throws IOException {
				System.out.println("");
				msg = "";
				String message = new String(body, "UTF-8");
				String[] mu = message.split(SEPARATOR);
				String sentUser = mu[0];
				String msg = mu[1];
				System.out.println(sentUser + " diz: " + msg);
				try {
					printPrompt();
				} catch (TimeoutException e) {
					e.printStackTrace();
				}

			}
		};
		channel.basicConsume(user, true, consumer);

		printPrompt();
	}

	public static void printPrompt() throws IOException, TimeoutException {
		System.out.print(sendTo + ">>");
		try {
			msg = scanner.nextLine();
		} catch (Exception e) {
		}
		readPrompt();
		System.out.println("");
	}

	public static void readPrompt() throws IOException, TimeoutException {
		if (msg != null && !msg.equals("")) {
			if (msg.trim().startsWith("@")) {
				sendTo = msg.substring(1, msg.length());
				printPrompt();
			} else if (sendTo.trim().equals("")) {
				System.out.println("Informe para quem quer mandar a mensagem!");
				printPrompt();
			} else {
				sendMessage();
				printPrompt();
			}
		}
	}

	public static void sendMessage() throws IOException, TimeoutException {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("wombat.rmq.cloudamqp.com");
		factory.setUsername("tradflan");
		factory.setPassword("qfWbjO_c1Lu05JgpxraRsn4ouelMmfuW");
		factory.setVirtualHost("tradflan");
		Connection connection = factory.newConnection();
		Channel channel = connection.createChannel();

		channel.queueDeclare(sendTo, false, false, false, null);
		channel.basicPublish("", sendTo, null, (user + SEPARATOR + msg).getBytes("UTF-8"));
		// System.out.println(" [x] Sent '" + msg + "'");

		channel.close();
		connection.close();
	}
}
