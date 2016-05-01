package ed2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrganizadorBrent implements FileOrganizer {

    // Tamanho da tabela de registros do arquivo
    private final int P = 11; 
    
    // Número de bytes que um registro ocupa
    private final int TAMANHO_REGISTRO = 157;
    
    // Canal de comunicação com o arquivo
    private FileChannel canal;
    
    public OrganizadorBrent(String path) throws FileNotFoundException{
        File file = new File(path);
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        this.canal = rf.getChannel();
    }
    
    private int calculaHash(int matricula) {
        return (matricula % this.P);
    }
    
    private int calculaIncremento(int matricula) {
        return ((matricula / this.P) % this.P);
        //return (matricula % (this.P - 2)) + 1;
    }
    
    @Override
    public void addReg(Aluno a) {
        ByteBuffer buf = ByteBuffer.allocate(TAMANHO_REGISTRO);
        
        int matric = a.getMatricula();
        int hash = calculaHash(matric);
        int posicao = hash * TAMANHO_REGISTRO;
        int x;

        try {
            canal.position(posicao);
            canal.read(buf);
            buf.flip();
            x = buf.getInt();
            buf.clear();
            
            System.out.println("{" + posicao + "}  LIDO: " + x);
            // Se a posição estiver livre
            if (x == 0 || x == -1) {
                canal.position(posicao);
                canal.write(a.getByteBuffer());
                buf.clear();
            }
            // Houve uma colisão
            else {
                int incremento = calculaIncremento(matric);
                posicao = ((hash + incremento) % this.P) * TAMANHO_REGISTRO;
                canal.position(posicao);
                canal.read(buf);
                buf.flip();
                x = buf.getInt();
                buf.clear();
                
                if (x == 0 || x == -1) {
                    canal.position(posicao);
                    canal.write(a.getByteBuffer());
                    buf.clear();
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
        ByteBuffer buf = ByteBuffer.allocate(TAMANHO_REGISTRO);

        try {
            canal.position(0);
            for (int i = 0; i < canal.size()/TAMANHO_REGISTRO; i++) {
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
    
    public void inicializaArquivo(Aluno vazio) {        
        try {
            canal.position(0);
            for (int i = 0; i < this.P; i++)
                canal.write(vazio.getByteBuffer());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void listarArquivo() {
        ByteBuffer buf = ByteBuffer.allocate(TAMANHO_REGISTRO);
        try {
            canal.position(0);
            for (int i = 0; i < this.P; i++) {
                canal.read(buf);
                buf.flip();
                int x = buf.getInt();
                System.out.println("[ " + i + " ] " + x);
                buf.clear();
            }
        } catch (IOException ex) {
            Logger.getLogger(ManipuladorSequencial.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
    }
}
