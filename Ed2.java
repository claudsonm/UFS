/**
 * Os códigos foram desenvolvidos durante as aulas pelos seguintes alunos
 * 
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 */
package ed2;

import java.io.*;
import java.nio.*;
import java.nio.channels.FileChannel;

public class Ed2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, 
            IOException {
        long startTime = System.currentTimeMillis();
        // ------------------------------------
        
        Aluno first = new Aluno(15, "Adalgisa", "Rua 4, 5", (short) 15, "M", "adalgisa@semcoracao.com");
        Aluno second = new Aluno(8, "Tairone", "Rua 5, 71", (short) 37, "m", "tairone@cigano.com");
        Aluno third = new Aluno(7, "Levy Vianna", "Rua 6, 45", (short) 14, "M", "levyvianna@volume5.com");
               
        // AULA 2 -----------------------
//        ManipuladorSimples teste = new ManipuladorSimples("/home/aluno/NetBeansProjects/ed2/files/alunos.db");
        ManipuladorSequencial teste = new ManipuladorSequencial("/home/aluno/NetBeansProjects/ed2/files/alunos.db");
        teste.addReg(first);
        teste.addReg(second);
        teste.addReg(third);
  
//        Aluno b = teste.getReg(8);
//        if (b != null) System.out.println(b.getMatricula() + " | " + 
//                b.getNome().substring(0,15) + " | " +
//                b.getEmail());
//        
//        Aluno del = teste.delReg(8);
//        if (del != null) System.out.println("O aluno " + del.getNome() + " (" + del.getMatricula() + ") foi removido.");
//        
//        del = teste.delReg(9);
//        if (del != null) System.out.println("O aluno " + del.getNome() + " (" + del.getMatricula() + ") foi removido.");
//        
//        del = teste.delReg(10);
//        if (del != null) System.out.println("O aluno " + del.getNome() + " (" + del.getMatricula() + ") foi removido.");

        // ------------------------------------
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("----------------------");
        System.out.println(totalTime + " ms");
    }
    
}
