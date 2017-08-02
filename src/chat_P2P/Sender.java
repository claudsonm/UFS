package chat_P2P;

import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Sender extends Thread {
    
    private Scanner scan;
    private Socket socket;
    
    public Sender(Socket socket) {
        this.socket = socket;
    }
    
    public void run() {
        try {
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            scan = new Scanner(System.in);
            while (true) {
                System.out.print("@Você: ");
                String text = scan.nextLine();
                dos.writeUTF(text);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scan.close();
        }
    }

}
