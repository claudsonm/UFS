/**
 * Os códigos foram desenvolvidos durante as aulas pelos seguintes
 * 
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 */

package ed2;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            Logger.getLogger(ManipuladorSimples.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        System.out.println("Aluno adicionado!");
    }

    @Override
    public Aluno delReg(int matric) {
        ByteBuffer buf = ByteBuffer.allocate(157);
        Aluno removido = null;
        
        try {
            long posicaoRegistro = -1000;
            long posicaoUltimo = canal.size() - 157;
            
            for (int i = 0; i < canal.size()/157; i++) {
                canal.read(buf);
                buf.flip();
                int x = buf.getInt();
                if (x == matric) {
                    posicaoRegistro = canal.position() - 157;
                    buf.clear();
                    removido = new Aluno(buf);
                    break;
                }
                buf.clear();
            }
            
            if (posicaoRegistro != -1000) {
                if(posicaoRegistro == posicaoUltimo){
                    // diminua o tamanho do arquivo
                    canal.truncate(canal.size() - 157);
                }
                else {
                    // pega o último, copia e exclue.
                    buf.clear(); // !!!! LIMPA BUF PRA REUTILIZAR
                    canal.position(posicaoUltimo);
                    canal.read(buf);
                    buf.flip();
                    canal.position(posicaoRegistro);
                    canal.write(buf);
                    canal.truncate(canal.size() - 157);
                }
            }
            else System.out.println("Aluno inexistente!");
            
        } catch (IOException ex) {
            Logger.getLogger(ManipuladorSimples.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return removido;
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
                    Aluno a = new Aluno(buf);
                    return a;
                }
                buf.clear();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(ManipuladorSimples.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void fecharCanal() throws IOException{
        canal.close();
    }
    
}