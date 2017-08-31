package http;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class HTTPClient {

	public static void main(String[] args) {
		try {
			//firstRequest();
			secondRequest();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

    private static void firstRequest() throws UnknownHostException, IOException {
        Socket socket = new Socket("www.ufs.br", 80);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        String separator = "\r\n";
        // METHOD RESOURCE PROTOCOL OPERATORS
        String msg = "GET / HTTP/1.1" + separator + "Host:www.ufs.br" + separator + separator;
        out.write(msg.getBytes());
        byte[] buffer = new byte[1024];
        int size;
        while ((size = in.read(buffer)) > 0) {
            String reply = new String(buffer, 0, size);
            System.out.println(reply);
        }
        socket.close();
    }
    
    private static void secondRequest() throws UnknownHostException, IOException {
        Socket socket = new Socket("www.ufs.br", 80);
        InputStream in = socket.getInputStream();
        OutputStream out = socket.getOutputStream();
        String separator = "\r\n";
        // METHOD RESOURCE PROTOCOL OPERATORS
        String msg = "GET /uploads/image/path/103/Institu_Pano.jpg HTTP/1.1" + separator + "Host:www.ufs.br" + separator + separator;
        out.write(msg.getBytes());
        //
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        //
        byte[] buffer = new byte[1024];
        int size;
        String reply;
        while ((reply = br.readLine()).length() > 0) {
            System.out.println(reply);
        }
        File file = new File("imagem.jpg");
        FileOutputStream fos = new FileOutputStream(file);
        byte[] secondBuffer = new byte[1024];
        while ((size = in.read(secondBuffer)) > 0) {
            fos.write(secondBuffer, 0, size);
        }
        fos.flush();
        fos.close();
        socket.close();
    }

}
