package threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Lembretes gerenciados pelo servidor.
 * 
 * @see ServidorLembretes Gerencia os lembretes
 */
public class LembreteTCP extends Thread {
	
	private Socket sock;

	public LembreteTCP(Socket sock) {
		super();
		this.sock = sock;
	}
	
	@Override
	public void run() {
		try {
			DataInputStream dis = new DataInputStream(sock.getInputStream());
			DataOutputStream dos = new DataOutputStream(sock.getOutputStream());
			
			String nome = dis.readUTF();
			int intervalo = dis.readInt();
			int total = dis.readInt();
			
			for (int i = 1; i <= total; i++) {
				dos.writeUTF("Hey, lembre-se de tomar a " + i + "ª dose de " + nome);
				Thread.sleep(intervalo * 1000);
			}
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
	}

}
