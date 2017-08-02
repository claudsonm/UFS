package chat_P2P;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Cliente Alice, recebe na porta 44444
 *
 */
public class Alice {

    public static void main(String[] args) throws IOException {
        String ipDestiny = "localhost";
        int portDestiny = 33333;
        int runPort = 44444;
        boolean sinc = false;
                
        System.out.print("Inicializando receptor de mensagens do chat P2P... ");
        ServerSocket listener = new ServerSocket(runPort);
        System.out.println("OK!");
   
        
   
        
        
        try {
            System.out.print("Aguardando conexão do peer... ");
            while (true) {
            	if (!sinc){
            		try {
          			  System.out.print("Inicializando entregador de mensagens do chat P2P... ");
               	        Socket sockOut = new Socket(ipDestiny, portDestiny);
               	        System.out.println("OK!");
               	        new Sender(sockOut).start();
               	        sinc = true;
						
					} catch (Exception e) {
					}
            	}
                Socket sockIn = listener.accept();
                System.out.println("OK!");
                new Receiver(sockIn).start();
                
                //System.out.print("Inicializando entregador de mensagens do chat P2P... ");
                //Socket sockOut = new Socket(ipDestiny, portDestiny);
                //System.out.println("OK!");
                new Sender(sockIn).start();
            }
        } finally {
            listener.close();
        }
    }

}
