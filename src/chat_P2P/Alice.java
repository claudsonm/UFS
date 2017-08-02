package chat_P2P;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * Cliente Alice, recebe na porta 44444
 *
 */
public class Alice {

    public static void main(String[] args) throws IOException {
        String ipDestiny = "localhost";
        int portDestiny = 33333;
        int runPort = 44444;
                
        System.out.print("Inicializando entregador de mensagens do chat P2P... ");
        Socket sockOut = new Socket(ipDestiny, portDestiny);
        System.out.println("OK!");
        new Sender(sockOut).start();
    }

}
