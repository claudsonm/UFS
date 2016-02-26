/**
 * Os códigos foram desenvolvidos durante as aulas pelos seguintes alunos
 * 
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 */
package projeto_algoritmos;

public class Fibonacci {
    public static long fibRec(int n) {
        long x = 0;
        if (n>0){
            if (n == 1 || n == 2)
                return 1;
            x = fibRec(n-1) + fibRec(n-2);
        }
        return x;
    }
    
    public static long fibIter(int n) {
        // TODO
    }
    
    public int teste(int n){
        
        return 0;
    }
}
