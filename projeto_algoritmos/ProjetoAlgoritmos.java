/**
 * Os códigos foram desenvolvidos durante as aulas pelos seguintes alunos
 * 
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 */
package projeto_algoritmos;

public class ProjetoAlgoritmos {
    public static void main(String[] args) {
        Fatorial.fatIter(500);
        // ------------------------------------
        long startTime = System.nanoTime();
        
        long x = Fatorial.fatIter(1000);
        
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("----------------------");
        System.out.println(totalTime + " ns");
        // ------------------------------------
        // ------------------------------------
        startTime = System.nanoTime();
        
        x = Fatorial.fatRec(1000);
        
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("----------------------");
        System.out.println(totalTime + " ns");
        // ------------------------------------
        // ------------------------------------
        
        x = Fibonacci.fibIter(6);
        System.out.println(x);
    }
}
