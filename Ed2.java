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
    public static void main(String[] args) throws FileNotFoundException, IOException {
        long startTime = System.currentTimeMillis();
        // ------------------------------------
        
//        Aluno first = new Aluno(669, "Arara", "Rua 2, 667", (short) 550, "M", "jasdorge@capadocia.com");
//        ByteBuffer buf = first.getByteBuffer();
        
//        Aluno second = new Aluno(666, "Beija-flor", "Rua XYZ, 21", (short) 14, "F", "asduhausid@dragao.com");
//        System.out.println(second.getMatricula());
//        System.out.println(second.getNome());
//        System.out.println(second.getIdade());
//        System.out.println(second.getSexo());
//        System.out.println(second.getEmail());
//        System.out.println(second.getEndereco());
        
        // AULA 2 -----------------------
        ManipuladorSimples teste = new ManipuladorSimples("/home/aluno/NetBeansProjects/ed2/files/alunos.db");
//        teste.addReg(first);
//        teste.addReg(second);
        
        Aluno b = teste.getReg(666);
        System.out.println(b.getMatricula() + " | " + b.getNome());
        
        
        // ------------------------------------
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
        
        
//        int     x = 5; // 4 bytes
//        short   y = 12; // 2 bytes
//        String  z = "D\n"; // OQQ (5 bytes)
//        
//        File file = new File("/home/aluno/NetBeansProjects/ed2/files/teste.db");;;
//        RandomAccessFile rf = new RandomAccessFile(file, "rw");
//        FileChannel canal = rf.getChannel();
//        //FileChannel canal = new RandomAccessFile(file, "rw").getChannel();
//        
//        ByteBuffer buf = ByteBuffer.allocate(8);
//        /*buf.putInt(x);
//        buf.putShort(y);
//        buf.put(z.getBytes());
//        //System.out.println(buf);
//        
//        buf.flip();
//        canal.position(canal.size());
//        canal.write(buf);
//        canal.close();*/
//        
//        lerArquivo(file, buf);
    }


    private static void lerArquivo(File file, ByteBuffer buf) throws FileNotFoundException, IOException {
        FileChannel canal = new RandomAccessFile(file, "r").getChannel();
        
        for (int i = 0; i < canal.size()/8; i++) {
            canal.read(buf);
            buf.flip();
            int x = buf.getInt();
            short y = buf.getShort();
            byte[] stringSize = new byte[2];
            buf.get(stringSize);
            String z = new String(stringSize);
            //System.out.println(x + " | " + y + " | " + z + " | [C]: " + canal.position());
            System.out.println(z + " | [C]: " + canal.position() + " | " + canal.size());
            buf.clear();
        }
        
    }
    
}
