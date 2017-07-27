package threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Cliente que solicita ao servidor que lembre-o de tomar remédio.
 * 
 * @see ServidorLembretes Processa as requisições
 */
public class ClienteLembrete {

	public static void main(String[] args) {
		try {
			Socket sock = new Socket("localhost", 33333);
			InputStream in = sock.getInputStream();
			OutputStream out = sock.getOutputStream();
			DataInputStream dis = new DataInputStream(in);
			DataOutputStream dos = new DataOutputStream(out);
			
			String nome = "dipirona";
			int intervalo = 6;
			int total = 8;
			
			dos.writeUTF(nome);
			dos.writeInt(intervalo);
			dos.writeInt(total);
			
			for (int i = 0; i < total; i++) {
				String msg;
				msg = dis.readUTF();
				System.out.println(msg);
			}
			sock.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
