/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ed2;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aluno
 */
public class ManipuladorSimples implements FileOrganizer{
    
    private FileChannel canal;
    
    public ManipuladorSimples(String path) throws FileNotFoundException {
        File file = new File(path);
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        this.canal = rf.getChannel();
    }

    @Override
    public void addReg(Aluno a) {
        try {
            canal.position(canal.size());
            canal.write(a.getByteBuffer());
        } catch (IOException ex) {
            Logger.getLogger(ManipuladorSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("IRRA");
    }

    @Override
    public Aluno delReg(int matric) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Aluno getReg(int matric) {
        ByteBuffer buf = ByteBuffer.allocate(157);
        
        try {
            for (int i = 0; i < canal.size()/157; i++) {
                canal.read(buf);
                buf.flip();
                int x = buf.getInt();
                if (x == matric) {
                    buf.clear();
                    System.out.println("POSITION: " + buf.position());
                    Aluno a = new Aluno(buf);
                    System.out.println(i + " | " + x + " [C]: " + canal.position() + " | " + canal.size());
                    return a;
                }
                //short y = buf.getShort();
                //byte[] stringSize = new byte[2];
                //buf.get(stringSize);
                //String z = new String(stringSize);
                //System.out.println(x + " | " + y + " | " + z + " | [C]: " + canal.position());
                buf.clear();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ManipuladorSimples.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void fecharCanal() throws IOException{
        canal.close();
    }
    
}
