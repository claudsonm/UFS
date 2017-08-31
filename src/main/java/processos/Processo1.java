package processos;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Processo Cliente
 *
 */
public class Processo1 {

    public static void main(String[] args) {
        try {
            Socket sock = new Socket("localhost", 2300);
            InputStream in = sock.getInputStream();
            OutputStream out = sock.getOutputStream();
            
            String msg = "Olá mundo";
            out.write(msg.getBytes());
            
            byte[] buf = new byte[512];
            int tam = in.read(buf);
            msg = new String(buf);
            System.out.println(msg);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
