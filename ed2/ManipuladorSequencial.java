/* *****************************************************************************
 * Códigos desenvolvidos pelos seguintes alunos
 *
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 * ****************************************************************************/

package ed2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ManipuladorSequencial implements FileOrganizer {

    // Canal de comunicação com o arquivo
    private FileChannel canal;
    
    public ManipuladorSequencial(String path) throws FileNotFoundException{
        File file = new File(path);
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        this.canal = rf.getChannel();
    }
    
    @Override
    public void addReg(Aluno a) {
        int matric = a.getMatricula();

        try {
            canal.position(0);
            ByteBuffer buf = ByteBuffer.allocate(157);

            // Se o arquivo estiver vazio, apenas insira o registro
            if(canal.size() == 0) canal.write(a.getByteBuffer());
            else {
                // Número de registros existentes no arquivo
                int total = (int) (canal.size()/157);  

                for (int i = 1; i < total + 1; i++) {
                    // Se for a primeira iteração, vá para o ÚLTIMO registro
                    // Senão, vá para o ÚLTIMO - i
                    if (i != 1) canal.position(canal.size() - (i+1)*157);
                    else canal.position(canal.size() - 157);

                    canal.read(buf);
                    buf.flip();
                    int x = buf.getInt();
                    buf.clear();
                    // Se a matricula lida for menor, insira o registro desejado
                    if (x < matric) {
                        canal.write(a.getByteBuffer());
                        buf.clear();                        
                        break;
                    }
                    else {
                        // Empurra os registros até encontrar uma matrícula menor
                        canal.write(buf);
                        buf.clear();
                        // Insere o registro quando ele for menor que o primeiro
                        if(i == total){
                            canal.position(0);
                            canal.write(a.getByteBuffer());
                            break;
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(ManipuladorSequencial.class.getName())
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
            // Número total de registros existentes no arquivo
            int total = (int) (canal.size()/157); 
            // Iteração na qual o registro a ser removido foi encontrado
            int aux = 0;
            
            for (int i = 0; i < total; i++) {
                canal.read(buf);
                buf.flip();
                int x = buf.getInt();
                // Encontrada a matrícula referente ao registro a ser removido
                if (x == matric) {
                    posicaoRegistro = canal.position() - 157;
                    aux = i;
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
                    // Puxa os registros a partir do removido para uma posição acima
                    for(int i = aux; i <= total; i++){
                        canal.position((i+1)*157);
                        canal.read(buf);
                        buf.flip();
                        canal.position(i*157);
                        canal.write(buf);
                        buf.clear();
                    }
                    canal.truncate(canal.size() - 157);
                }
            }
            else System.out.println("Aluno inexistente!");
            
        } catch (IOException ex) {
            Logger.getLogger(ManipuladorSequencial.class.getName())
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
            Logger.getLogger(ManipuladorSequencial.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int[] lerSelecionados() {
        ByteBuffer buf = ByteBuffer.allocate(4);
        int[] selected = new int[1000];
        
        try {
            canal.position(0);
            for (int i = 0; i < canal.size()/4; i++) {
                canal.read(buf);
                buf.flip();
                int x = buf.getInt();
                selected[i] = x;
                buf.clear();
            }
            return selected;
            
        } catch (IOException ex) {
            Logger.getLogger(ManipuladorSequencial.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /* *************************************************************************
     * Função de obtenção de registros em busca binária
     * *************************************************************************
    @Override
    public Aluno getReg(int matric) {
        ByteBuffer buf = ByteBuffer.allocate(157);
        
        try {
            int inicio = 0;
            int fim = ((int) canal.size() / 157) - 1;

            while (inicio <= fim) {
                int meio = (inicio + fim) / 2;
                canal.position(meio * 157);
                canal.read(buf);
                buf.flip();
                int x = buf.getInt();
                if (x == matric) {
                    buf.clear();
                    Aluno a = new Aluno(buf);
                    return a;
                }
                buf.clear();

                if (matric > x) inicio = meio + 1;
                else fim = meio - 1;
            }
        } catch (IOException ex) {
            Logger.getLogger(ManipuladorSequencial.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }
    */
    
}
