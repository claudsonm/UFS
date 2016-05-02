package ed2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OrganizadorBrent implements FileOrganizer {

    // Tamanho da tabela de registros do arquivo
    private final int P = 12000017; 
    
    // Numero de bytes que um registro ocupa
    private final int TAMANHO_REGISTRO = 157;
    
    // Canal de comunicacao com o arquivo
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
        //return ((matricula / this.P) % this.P);
        return (matricula % (this.P - 2)) + 1;
    }
    
    @Override
    public void addReg(Aluno a) {
        ByteBuffer buf = ByteBuffer.allocate(TAMANHO_REGISTRO);
        
        int matric = a.getMatricula();
        int hash = calculaHash(matric);
        int posicao = hash * TAMANHO_REGISTRO;
        int x, z;
        int posicao2 = posicao;
        int posicao3 = posicao;

        try {
            canal.position(posicao);
            canal.read(buf);
            buf.flip();
            x = buf.getInt();
            z = x;
            buf.clear();
            
            // Se a posicao estiver livre
            if (x == 0 || x == -1) {
                canal.position(posicao);
                canal.write(a.getByteBuffer());
                buf.clear();
            }
            // Houve uma colisao
            else {
                //SIMULANDO OS PASSOS PARA ADD O NOVO REGISTRO SEM MOVER O PRIMEIRO
                int colisao = x;
                int incremento = calculaIncremento(matric);
                int passos = 1;
                while(x != 0 && x != -1) {
                    passos++;
                    posicao = (((posicao/TAMANHO_REGISTRO) + incremento) % this.P) * TAMANHO_REGISTRO;
                    canal.position(posicao);
                    canal.read(buf);
                    buf.flip();
                    x = buf.getInt();
                    buf.clear();
                }
                
                //SIMULANDO OS PASSOS PARA ADD O NOVO REGISTRO MOVENDO O PRIMEIRO
                int incremento2 = calculaIncremento(colisao);
                int passos2 = 1;
                while(z != 0 && z != -1) {
                    passos2++;
                    posicao2 = (((posicao2/TAMANHO_REGISTRO) + incremento2) % this.P) * TAMANHO_REGISTRO;
                    canal.position(posicao2);
                    canal.read(buf);
                    buf.flip();
                    z = buf.getInt();
                    buf.clear();
                }
                
                // Opcao I: apenas escrever na posicao encontrada
                if ( (custoBusca(colisao)+passos) <= (custoBusca(colisao)+passos2) ) { //+1 ja incluso
                    canal.position(posicao);
                    canal.write(a.getByteBuffer());
                    buf.clear();
                }
                // Opcao II: por o novo no inicio e mover o original
                else {
                    canal.position(posicao3);
                    canal.read(buf);
                    buf.clear();
                    canal.position(posicao2);
                    canal.write(buf);
                    buf.clear();
                    canal.position(posicao3);
                    canal.write(a.getByteBuffer());
                    buf.clear();
                }
            }

        } catch (IOException ex) {
            Logger.getLogger(ManipuladorSequencial.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        //System.out.println("Aluno adicionado!");
    }
    
    @Override
    public Aluno delReg(int matric) {
        ByteBuffer buf = ByteBuffer.allocate(TAMANHO_REGISTRO);
        // Informacoes do aluno removido
        Aluno removido = null;
        int hash = calculaHash(matric);
        int posicao = hash * TAMANHO_REGISTRO;
        
        try {
            canal.position(posicao);
            canal.read(buf);
            buf.flip();
            int x = buf.getInt();
            buf.clear();
            if (x == matric) {
                removido = new Aluno(buf);
                canal.position(posicao);
                canal.write(removedByteBuffer());
                return removido;
            }
            else {
                int incremento = calculaIncremento(matric);
                while(x != 0) {
                    posicao = (((posicao/TAMANHO_REGISTRO) + incremento) % this.P) * TAMANHO_REGISTRO;
                    canal.position(posicao);
                    canal.read(buf);
                    buf.flip();
                    x = buf.getInt();
                    buf.clear();
                    if (x == matric) {
                        removido = new Aluno(buf);
                        canal.position(posicao);
                        canal.write(removedByteBuffer());
                        return removido;
                    }
                }
            }
            System.out.println("Aluno inexistente!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return removido;
    }
    
    private ByteBuffer removedByteBuffer() {
        ByteBuffer b = ByteBuffer.allocate(TAMANHO_REGISTRO);
        b.putInt(-1);
        b.put("".getBytes());
        b.put("".getBytes());
        b.putShort((short) 0);
        b.put("".getBytes());
        b.put("".getBytes());
        b.flip();
        return b;
    }
    
    @Override
    public Aluno getReg(int matric) {
        ByteBuffer buf = ByteBuffer.allocate(TAMANHO_REGISTRO);
        
        int hash = calculaHash(matric);
        int posicao = hash * TAMANHO_REGISTRO;

        try {
            canal.position(posicao);
            canal.read(buf);
            buf.flip();
            int x = buf.getInt();
            buf.clear();
            if (x == matric) {
                Aluno a = new Aluno(buf);
                return a;
            }
            else {
                int incremento = calculaIncremento(matric);
                while(x != 0) {
                    posicao = (((posicao/TAMANHO_REGISTRO) + incremento) % this.P) * TAMANHO_REGISTRO;
                    canal.position(posicao);
                    canal.read(buf);
                    buf.flip();
                    x = buf.getInt();
                    buf.clear();
                    if (x == matric) {
                        Aluno a = new Aluno(buf);
                        return a;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    private int custoBusca(int matricula) {
        ByteBuffer buf = ByteBuffer.allocate(TAMANHO_REGISTRO);
        
        int hash = calculaHash(matricula);
        int posicao = hash * TAMANHO_REGISTRO;
        int passos = 1;
        
        try {
            canal.position(posicao);
            canal.read(buf);
            buf.flip();
            int x = buf.getInt();
            buf.clear();
            
            if (x == matricula) return passos;
            else {
                int incremento = calculaIncremento(matricula);
                while(x != 0) {
                    passos++;
                    posicao = (((posicao/TAMANHO_REGISTRO) + incremento) % this.P) * TAMANHO_REGISTRO;
                    canal.position(posicao);
                    canal.read(buf);
                    buf.flip();
                    x = buf.getInt();
                    buf.clear();
                    if (x == matricula) break; 
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return passos;
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
