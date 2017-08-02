package chat_P2P;

import java.net.ServerSocket;
import java.net.Socket;

public class Bob {

    public static void main(String[] args) throws Exception {
        String ipDestiny = "localhost";
        int portDestiny = 44444;
        int runPort = 33333;
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
