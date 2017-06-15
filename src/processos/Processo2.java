package processos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Processo Servidor
 *
 */
public class Processo2 {
    
    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(2300);
            Socket sock = server.accept();
            InputStream in = sock.getInputStream();
            OutputStream out = sock.getOutputStream();
            
            byte[] buf = new byte[512];
            int tam = in.read(buf);
            String msg = new String(buf);
            System.out.println(msg);
            
            msg = "fala edgay";
            out.write(msg.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
