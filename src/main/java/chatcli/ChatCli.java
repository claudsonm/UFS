package chatcli;

import com.google.protobuf.ByteString;
import com.rabbitmq.client.*;

import protobuf.MessageProtos.Message;

import java.io.IOException;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * Chat CLI que utiliza o protocolo AMQP com o serviço Cloud AMQP
 * 
 * @author Claudson Martins
 * @author Edgar Lima
 * @author Guilherme Boroni
 * @version 1.3.0
 * @since 16/08/2017
 */
public class ChatCli {

    /**
     * Comando para criar um novo grupo de conversa. Exemplo: {@code !addGroup ufs}
     */
    public static final String CREATE_GROUP = "addgroup";

    /**
     * Comando para excluir um grupo de conversa. Exemplo: {@code !delGroup ufs}
     */
    public static final String DEL_GROUP = "delgroup";

    /**
     * Comando para adicionar um usuário no grupo de conversa. Exemplo: {@code !addUser alice ufs}
     */
    public static final String ADD_USER = "adduser";

    /**
     * Comando para excluir um usuário do grupo de conversa. Exemplo: {@code !delUser alice ufs}
     */
    public static final String DEL_USER = "deluser";
    
    /**
     * Comando para finalizar a execução do chat. Exemplo: {@code !quit}
     */
    public static final String END_CHAT = "quit";

    /**
     * Nome do usuário que está utilizando o chat.
     */
    public static String user = "";

    /**
     * Nome do destinatário da mensagem. Pode ser um usuário ou um grupo.
     */
    public static String sendTo = "";

    /**
     * Identifica se o destinatário é um usuário ou um grupo.
     */
    public static boolean isGroup = false;

    /**
     * Conexão com o servidor que processa a fila de mensagens.
     */
    public static Connection connection;
    
    /**
     * Canal de comunicação que utiliza a conexão com o servidor AMQP.
     */
    public static Channel channel;
    
    /**
     * Saída padrão com codificação UTF-8.
     */
    public static PrintStream out;

    public static Scanner scanner;

    public static String msg = "";

    /**
     * Ponto de entrada da aplicação.
     * 
     * @param argv
     * @throws Exception
     */
    public static void main(String[] argv) throws Exception {
        out = new PrintStream(System.out, true, "UTF-8");
        scanner = new Scanner(System.in);
        // Lê a entrada do console e atribui o nome do usuário do chat
        out.print("User: ");
        user = scanner.nextLine();
        // Estabelece a conexão com o CloudAMQP
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("wombat.rmq.cloudamqp.com");
        factory.setUsername("tradflan");
        factory.setPassword("qfWbjO_c1Lu05JgpxraRsn4ouelMmfuW");
        factory.setVirtualHost("tradflan");
        connection = factory.newConnection();
        channel = connection.createChannel();
        // Cria ou obtém a fila referente ao usuário do chat
        channel.queueDeclare(user, false, false, false, null);
        // Define o comportamento do consumidor da fila de mensagens
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope,
                    AMQP.BasicProperties properties, byte[] body) throws IOException {
                msg = "";
                Message message = Message.parseFrom(body);
                String fromGroup = message.getGroup();
                String fromUser = message.getSender();
                String msg = message.getContent(0).getData().toStringUtf8();
                String date = message.getDate();
                String time = message.getTime();

                if (fromGroup.equals("")) {
                    // Exibe a mensagem direta
                    out.println("");
                    out.println("(" + date + " às " + time + ") " + fromUser + " diz: " + msg);
                } else {
                    // É uma mensagem de um grupo
                    if (!fromUser.equals(user)) {
                        // Exibe a mensagem se o emissor não for o próprio usuário (previne o "eco")
                        out.println("");
                        out.println("(" + date + " às " + time + ") " + fromUser
                                + " [" + fromGroup + "] diz: " + msg);
                    }
                }
                try {
                    // Impede que o console seja exibido 2x devido ao efeito "eco"
                    if (!fromUser.equals(user) && !fromGroup.equals("")) {
                        printAndScanPrompt();
                    }
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
     * Exibe na tela o indicador do prompt e faz a leitura do teclado.
     * 
     * @throws IOException
     * @throws TimeoutException
     */
    public static void printAndScanPrompt() throws IOException, TimeoutException {
        String groupIndicator = "";
        if (isGroup) {
            groupIndicator = "*";
        }
        out.print(sendTo + groupIndicator + " >> ");
        try {
            msg = scanner.nextLine().trim();
        } catch (Exception e) {
            // System.err.println(e);
        }
        processInput();
    }

    /**
     * Interpreta a entrada do usuário e executa a ação correspondente.
     * 
     * @throws IOException
     * @throws TimeoutException
     */
    public static void processInput() throws IOException, TimeoutException {
        if (msg != null && !msg.equals("")) {
            if (msg.startsWith("@")) {
                // Chaveia para troca de mensagens direta
                sendTo = msg.substring(1, msg.length()).trim();
                isGroup = false;
                printAndScanPrompt();
            } else if (msg.startsWith("#")) {
                // Chaveia para troca de mensagens em grupo
                sendTo = msg.substring(1, msg.length()).trim();
                isGroup = true;
                printAndScanPrompt();
            } else if (msg.startsWith("!")) {
                // Interpreta os diferentes comandos
                String[] inputData = msg.substring(1, msg.length()).trim().split(" ");
                String command = inputData[0].toLowerCase();
                String error = "";
                switch (command) {
                    case CREATE_GROUP:
                        if (inputData.length > 1) {
                            createGroup(inputData[1]);
                        } else {
                            error = "Error CREATE_GROUP";
                        }
                        break;

                    case DEL_GROUP:
                        if (inputData.length > 1) {
                            removeGroup(inputData[1]);
                        } else {
                            error = "Error DEL_GROUP";
                        }
                        break;

                    case ADD_USER:
                        if (inputData.length > 2) {
                            addUser(inputData[1], inputData[2]);
                        } else {
                            error = "Error ADD_USER";
                        }
                        break;

                    case DEL_USER:
                        if (inputData.length > 2) {
                            removeUser(inputData[1], inputData[2]);
                        } else {
                            error = "Error DEL_USER";
                        }
                        break;
                    
                    case END_CHAT:
                        channel.close();
                        connection.close();
                        System.exit(0);
                        break;

                    default:
                        error = "Comando inexistente!";
                        break;
                }

                if (!error.equals("")) {
                    out.println("");
                    out.println(error);
                }
                printAndScanPrompt();
            } else if (sendTo.equals("")) {
                // Identifica uma mensagem sem destinatário
                out.println("Ninguém te escuta. Utilize @<NOME_DO_USUÁRIO> ou "
                        + "#<NOME_DO_GRUPO> para definir um destinatário.");
                printAndScanPrompt();
            } else {
                // Transmite a mensagem
                sendMessage();
                printAndScanPrompt();
            }
        }
    }

    /**
     * Envia a mensagem lida para a fila de mensagens do destinatário.
     * 
     * @throws IOException
     * @throws TimeoutException
     */
    public static void sendMessage() throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        if (isGroup) {
            channel.exchangeDeclare(sendTo, "fanout");
            channel.basicPublish(sendTo, "", null, makeMessage(user, msg, sendTo));
        } else {
            channel.queueDeclare(sendTo, false, false, false, null);
            channel.basicPublish("", sendTo, null, makeMessage(user, msg));
        }
        channel.close();
    }

    /**
     * Retorna os bytes da mensagem direta no formato protocol buffer.
     * 
     * @param sender Usuário que enviou a mensagem
     * @param text Conteúdo textual da mensagem
     * @return Mensagem serializada
     */
    private static byte[] makeMessage(String sender, String text) {
        return makeMessage(sender, text, "");
    }

    /**
     * Retorna os bytes da mensagem no formato protocol buffer.
     * 
     * @param sender Usuário que enviou a mensagem
     * @param text Conteúdo textual da mensagem
     * @param group Nome do grupo destinatário
     * @return Mensagem serializada
     */
    private static byte[] makeMessage(String sender, String text, String group) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        Date now = new Date();
        Message.Content.Builder data = Message.Content.newBuilder()
                .setData(ByteString.copyFromUtf8(text)).setType("text/plain");
        Message.Builder m = Message.newBuilder().setSender(sender).setDate(dateFormat.format(now))
                .setTime(timeFormat.format(now)).addContent(data);
        if (!group.isEmpty()) {
            m.setGroup(group);
        }
        return m.build().toByteArray();
    }

    /**
     * Cria um exchange e vincula-o ao usuário atual.
     * 
     * @param groupName Nome do grupo
     * @throws IOException
     * @throws TimeoutException
     */
    public static void createGroup(String groupName) throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(groupName, "fanout");
        channel.queueBind(user, groupName, "");
        channel.close();
    }

    /**
     * Deleta um exchange.
     * 
     * @param groupName Nome do grupo
     * @throws IOException
     * @throws TimeoutException
     */
    public static void removeGroup(String groupName) throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.exchangeDelete(groupName);
        channel.close();
    }

    /**
     * Vincula a fila de um usuário ao exchange do grupo.
     * 
     * @param user Nome do usuário
     * @param group Nome do grupo
     * @throws IOException
     * @throws TimeoutException
     */
    public static void addUser(String user, String group) throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(group, "fanout");
        channel.queueBind(user, group, "");
        channel.close();
    }

    /**
     * Desvincula a fila de um usuário do exchange do grupo.
     * 
     * @param user Nome do usuário
     * @param group Nome do grupo
     * @throws IOException
     * @throws TimeoutException
     */
    public static void removeUser(String user, String group) throws IOException, TimeoutException {
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(group, "fanout");
        channel.queueUnbind(user, group, "");
        channel.close();
    }

}
