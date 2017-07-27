package threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Servidor multi-thread que lembra os clientes de tomar remédio.
 * 
 * @see ClienteLembrete Realiza as requisições
 */
public class ServidorLembretes {

	public static void main(String[] args) {
		try {
			@SuppressWarnings("resource")
			ServerSocket ss = new ServerSocket(33333);
			while (true) {
				// O accept é bloqueante. Só sai daqui após algum cliente solicitar.
				Socket sock = ss.accept();
				LembreteTCP l = new LembreteTCP(sock);
				l.start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
