package ed2;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PC-Ed
 */
public class ManipuladorSequencial implements FileOrganizer{

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
            ByteBuffer buf = ByteBuffer.allocate(157);
            if(canal.size() == 0)
                canal.write(a.getByteBuffer());
            else {
                int total = (int) (canal.size()/157);                
                for (int i = 1; i < total + 1; i++) {
                    if(i!=1)
                        canal.position(canal.size() - (i+1)*157);
                    else
                        canal.position(canal.size() - 157);
                    canal.read(buf);
                    buf.flip();
                    int x = buf.getInt();
                    buf.clear();
                    if (x < matric) {
                        canal.write(a.getByteBuffer());
                        buf.clear();
                        break;
                    }
                    else {
                        canal.write(buf);
                        buf.clear();
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
        Aluno removido = null;
        
        try {
            long posicaoRegistro = -1000;
            long posicaoUltimo = canal.size() - 157;
            int aux = 0;
            int total = (int) (canal.size()/157); 
            
            for (int i = 0; i < total; i++) {
                canal.read(buf);
                buf.flip();
                int x = buf.getInt();
                if (x == matric) {
                    posicaoRegistro = canal.position() - 157;
                    aux = i;
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
                    buf.clear(); // !!!! LIMPA BUF PRA REUTILIZAR
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
    
}
