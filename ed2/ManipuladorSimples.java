/* *****************************************************************************
 * Códigos desenvolvidos pelos seguintes alunos
 *
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 * ****************************************************************************/

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
            // Insere o registro no final do arquivo
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
        // Informações do aluno removido
        Aluno removido = null;
        
        try {
            canal.position(0);
            // Posição do registro a ser removido (-1000 é não encontrado)
            long posicaoRegistro = -1000;
            // Posição do último registro do arquivo
            long posicaoUltimo = canal.size() - 157;
            
            for (int i = 0; i < canal.size()/157; i++) {
                canal.read(buf);
                buf.flip();
                int x = buf.getInt();
                // Encontrada a matrícula referente ao registro a ser removido
                if (x == matric) {
                    posicaoRegistro = canal.position() - 157;
                    buf.clear();
                    removido = new Aluno(buf);
                    break;
                }
                buf.clear();
            }
            
            // Se o registro foi encontrado
            if (posicaoRegistro != -1000) {
                if(posicaoRegistro == posicaoUltimo){
                    // É o último registro, apenas diminua o tamanho do arquivo
                    canal.truncate(canal.size() - 157);
                }
                else {
                    // Limpa o buffer pra reutilizar
                    buf.clear();
                    // Obtém o último registro, copia e exclue
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
            canal.position(0);
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