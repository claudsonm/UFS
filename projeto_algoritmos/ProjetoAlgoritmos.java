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
        long startTime, endTime, totalTime;
        Fatorial.fatIter(3);
        Fibonacci.fibIter(3);
        //long x;
        int x;
        // ------------------------------------
        // ------------------------------------
        startTime = System.nanoTime();
        
        //x = Fatorial.fatIter(1000);
        x = Fibonacci.fibIter(6);
        
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("----------------------");
        System.out.println("Valor f(n): " + x);
        System.out.println(totalTime + " ns");
        // ------------------------------------
        // ------------------------------------
        startTime = System.nanoTime();
        
        //x = Fatorial.fatRec(1000);
        x = Fibonacci.fibRec(6);
        
        endTime   = System.nanoTime();
        totalTime = endTime - startTime;
        System.out.println("----------------------");
        System.out.println("Valor f(n): " + x);
        System.out.println(totalTime + " ns");
    }
}
