package ed2;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;

/**
 *
 * @author aluno
 */
public class Ed2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, 
            IOException {
        long startTime = System.currentTimeMillis();
        // ------------------------------------
        
//        Aluno first = new Aluno(4, "NinjasdoArrocha", "Rua 4, 5", (short) 15, "M", "socu@poimpoimpoim.com");
//        Aluno second = new Aluno(5, "Tairone", "Rua 5, 71", (short) 37, "m", "tairone@cigano.com");
//        Aluno third = new Aluno(6, "LevyVianna", "Rua 6, 45", (short) 14, "M", "levyvianna@volume5.com");
               
        // AULA 2 -----------------------
        ManipuladorSimples teste = new ManipuladorSimples("/home/aluno/NetBeansProjects/ed2/files/alunos.db");
//        teste.addReg(first);
//        teste.addReg(second);
//        teste.addReg(third);
        
//        Aluno b = teste.getReg(4);
//        System.out.println(b.getMatricula() + " | " + b.getNome().substring(0,15) + " | " +
//                b.getEmail());
        
//        Aluno del = teste.delReg(2);
//        System.out.println("O aluno " + del.getNome() + " (" + del.getMatricula() + ") foi removido.");
//        
        // ------------------------------------
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("----------------------");
        System.out.println(totalTime + " ms");
    }


    private static void lerArquivo(File file, ByteBuffer buf) 
            throws FileNotFoundException, IOException {
        FileChannel canal = new RandomAccessFile(file, "r").getChannel();
        
        for (int i = 0; i < canal.size()/8; i++) {
            canal.read(buf);
            buf.flip();
            int x = buf.getInt();
            short y = buf.getShort();
            byte[] stringSize = new byte[2];
            buf.get(stringSize);
            String z = new String(stringSize);
            System.out.println(z + " | [C]: " + canal.position() 
                    + " | " + canal.size());
            buf.clear();
        }
        
    }
    
}
