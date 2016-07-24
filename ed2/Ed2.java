/* *****************************************************************************
 * Códigos desenvolvidos pelos seguintes alunos
 *
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 * ****************************************************************************/

package ed2;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Ed2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException,
                                                  IOException {
        // Obtém o momento em que o algoritmo começou a ser processado
        // long startTime = System.currentTimeMillis();
        SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
        Date startTime = new Date();
        System.out.println("Início em: " + ft.format(startTime));
        
        // Instancia manualmente 3 alunos
        /*Aluno first = new Aluno(27, "Adalgisa", "Rua 4, 5", (short) 15, "F", "adalgisa@semcoracao.com");
        Aluno second = new Aluno(18, "Tairone", "Rua 5, 71", (short) 37, "M", "tairone@cigano.com");
        Aluno third = new Aluno(29, "Levy Vianna", "Rua 6, 45", (short) 14, "M", "levyvianna@volume5.com");
        Aluno fourth = new Aluno(28, "Pablo", "Rua 6, 45", (short) 14, "M", "pabo@doarrocha.com");
        Aluno fifth = new Aluno(39, "Safadao", "Rua 6, 45", (short) 14, "M", "safadao@issae.com");
        Aluno sixth = new Aluno(13, "Adelino Nascimento", "Rua 6, 45", (short) 14, "M", "adelino@issae.com");
        Aluno seventh = new Aluno(16, "Vando", "Rua 6, 45", (short) 14, "M", "vando@issae.com");
        Aluno vazio = new Aluno(0, "", "", (short) 0, "", "");*/

        // Instancia o manipulador de arquivos
        //String arquivo = "data\\enem_seq.db";
        String arquivo = "data\\enem_brent.db";
        //ManipuladorSimples teste = new ManipuladorSimples(arquivo);
        //ManipuladorSequencial teste = new ManipuladorSequencial(arquivo);
        OrganizadorBrent teste = new OrganizadorBrent(arquivo);
        //teste.inicializaArquivo(vazio);
        

        // Persiste os alunos instanciados manualmente no arquivo
        /*teste.addReg(first);
        teste.addReg(second);
        teste.addReg(third);
        teste.addReg(fourth);
        teste.addReg(fifth);
        teste.addReg(sixth);
        teste.addReg(seventh);
        teste.listarArquivo();
        
        Aluno a = teste.getReg(29);
        if (a != null) System.out.println(a.getMatricula() + " | " + 
                a.getNome().substring(0,15) + " | " +
                a.getEmail());
        
        Aluno del = teste.delReg(27);
        if (del != null)
            System.out.println("O aluno " + del.getNome() +
                               " (" + del.getMatricula() + ") foi removido.");
        
        teste.listarArquivo();*/
        
        
        // Instancia e persiste automaticamente 1 Milhão de registros no arquivo
        /*Aluno novo = null;
        for (int i = 1; i <= 1000000; i++) {
            novo = new Aluno(i, String.valueOf(i), "Rua 6, 45", (short) 14, "M", "email@com");
            teste.addReg(novo);
        }*/
        
        // Lê as matriculas a serem buscadas
        /*ManipuladorSequencial teste2 = new ManipuladorSequencial("data\\selected.db");
        int[] selected = teste2.lerSelecionados();*/
        
        // Realiza a busca sequencial das matriculas
        /*for (int i = 0; i < 1000; i++) {
            Aluno a = teste.getReg(selected[i]);
            if (a != null) System.out.println("[ " + i + " ] " +
                    a.getMatricula() + " | " + 
                    a.getNome().substring(0,15) + " | " +
                    a.getEmail());
        }*/
        
        // Migra os alunos do ENEM_ALEAT para o ENEM_BRENT
        ManipuladorSequencial teste2 = new ManipuladorSequencial("data\\enem_aleat.db");
        for (int i = 0; i < 8722356; i++) {
            Aluno a = new Aluno(teste2.lerAluno(i * 157));
            System.out.println("[ " + i + " ] " +
                    a.getMatricula() + " | " + 
                    a.getNome().substring(0,15) + " | " +
                    a.getEmail());
            teste.addReg(a);
        }
  
        // Obtém do arquivo os alunos que foram instanciados manualmente
        /*Aluno b = teste.getReg(8);
        if (b != null) System.out.println(b.getMatricula() + " | " + 
                                          b.getNome().substring(0,15) + " | " +
                                          b.getEmail());
        b = teste.getReg(15);
        if (b != null) System.out.println(b.getMatricula() + " | " + 
                                          b.getNome().substring(0,15) + " | " +
                                          b.getEmail());
        b = teste.getReg(7);
        if (b != null) System.out.println(b.getMatricula() + " | " + 
                                          b.getNome().substring(0,15) + " | " +
                                          b.getEmail());*/

        // Deleta do arquivo os alunos que foram instanciados manualmente
        /*Aluno del = teste.delReg(8);
        if (del != null)
            System.out.println("O aluno " + del.getNome() +
                               " (" + del.getMatricula() + ") foi removido.");
        del = teste.delReg(15);
        if (del != null)
            System.out.println("O aluno " + del.getNome() +
                               " (" + del.getMatricula() + ") foi removido.");
        del = teste.delReg(7);
        if (del != null)
            System.out.println("O aluno " + del.getNome() +
                               " (" + del.getMatricula() + ") foi removido.");*/

        // Obtém o momento em que o algoritmo terminou de ser processado
        // long endTime   = System.currentTimeMillis();
        Date endTime = new Date();
        // Calcula e imprime o tempo total de execução em milisegundos
        long totalTime = endTime.getTime() - startTime.getTime();
        
        // Computa a diferença detalhadamente
        List<TimeUnit> units = new ArrayList<TimeUnit>(EnumSet.allOf(TimeUnit.class));
        Collections.reverse(units);
        Map<TimeUnit,Long> result = new LinkedHashMap<TimeUnit,Long>();
        long milliSecondsRest = totalTime;
        for (TimeUnit unit : units) {
            long diff = unit.convert(milliSecondsRest,TimeUnit.MILLISECONDS);
            long diffInMilliSecondsForUnit = unit.toMillis(diff);
            milliSecondsRest = milliSecondsRest - diffInMilliSecondsForUnit;
            result.put(unit,diff);
        }
        
        System.out.println("---------------------");
        System.out.println("Hora de Início:    " + ft.format(startTime));
        System.out.println("Hora do Término:   " + ft.format(endTime));
        System.out.println("");
        System.out.println("Tempo de Execução: " + totalTime + " ms");
        /*System.out.println("                   " + (totalTime/1000) + " s");
        System.out.println("                   " +
                result.get(TimeUnit.HOURS) + " h : " +
                result.get(TimeUnit.MINUTES) + " min : " +
                result.get(TimeUnit.SECONDS) + " s");*/
    }
    
}
