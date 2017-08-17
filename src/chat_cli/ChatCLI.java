package chat_cli;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

/**
 * Chat CLI que utiliza o protocolo AMQP com o serviço Cloud AMQP
 * 
 * @author Claudson Martins
 * @author Edgar Lima
 * @author Guilherme Boroni
 * @version 1.0.0
 * @since 16/08/2017
 */
public class ChatCLI {

    /**
     * Separador das informações contidas no corpo das mensagens
     */
	public final static String SEPARATOR = "|";
	
	/**
	 * Nome do usuário que está utilizando o chat
	 */
	public static String user = "";
	
	/**
	 * Nome do usuário que receberá a mensagem
	 */
	public static String sendTo = "";
	
	/**
	 * Conexão com o servidor que processa a fila de mensagens
	 */
	public static Connection connection;

	public static Scanner scanner;

	public static String msg = "";

	public static void main(String[] argv) throws Exception {
        scanner = new Scanner(System.in);
        // Lê a entrada do console e atribui o nome do usuário do chat
        System.out.print("User: ");
        user = scanner.nextLine();
        // Estabelece a conexão com o CloudAMQP
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("wombat.rmq.cloudamqp.com");
        factory.setUsername("tradflan");
        factory.setPassword("qfWbjO_c1Lu05JgpxraRsn4ouelMmfuW");
        factory.setVirtualHost("tradflan");
        connection = factory.newConnection();
        Channel channel = connection.createChannel();
        // Cria ou obtém a fila referente ao usuário do chat
        channel.queueDeclare(user, false, false, false, null);
        // Define o comportamento do consumidor da fila de mensagens
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                    AMQP.BasicProperties properties,
                    byte[] body) throws IOException {
                msg = "";
                String message = new String(body, "UTF-8");
                String[] messageData = message.split(Pattern.quote(SEPARATOR));
                String fromUser = messageData[0];
                String msg = messageData[1];
                System.out.println("");
                System.out.println(fromUser + " diz: " + msg);
                try {
                    printAndScanPrompt();
                } catch (TimeoutException e) {
                    e.printStackTrace();
                }
            }
        };
        channel.basicConsume(user, true, consumer);
        // Inicia a interação com o usuário
        printAndScanPrompt();
	}

	/**
	 * Exibe na tela o indicador do prompt e faz a leitura do teclado
	 * 
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public static void printAndScanPrompt() throws IOException, TimeoutException {
		System.out.print(sendTo + " >> ");
		try {
			msg = scanner.nextLine().trim();
		} catch (Exception e) {
		    //System.err.println(e);
		}
		processInput();
	}

	/**
	 * Interpreta a entrada do usuário e executa a ação correspondente
	 * 
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public static void processInput() throws IOException, TimeoutException {
		if (msg != null && !msg.equals("")) {
			if (msg.startsWith("@")) {
				sendTo = msg.substring(1, msg.length()).trim();
				printAndScanPrompt();
			} else if (sendTo.equals("")) {
				System.out.println("Ninguém te escuta. Utilize "
				        + "@<NOME_DO_USUÁRIO> para definir um destinatário.");
				printAndScanPrompt();
			} else {
				sendMessage();
				printAndScanPrompt();
			}
		}
	}

	/**
	 * Envia a mensagem lida para a fila de mensagens do destinatário
	 * 
	 * @throws IOException
	 * @throws TimeoutException
	 */
	public static void sendMessage() throws IOException, TimeoutException {
		Channel channel = connection.createChannel();
		channel.queueDeclare(sendTo, false, false, false, null);
		channel.basicPublish("", sendTo, null,
		        (user + SEPARATOR + msg).getBytes("UTF-8"));
		channel.close();
	}
}
