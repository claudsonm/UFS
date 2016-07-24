/**
 * Os códigos foram desenvolvidos durante as aulas pelos seguintes alunos
 * 
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 */
package projeto_algoritmos;

public class Fibonacci {
    public static int fibRec(int n) {        
        if (n < 2) return n;
        return fibRec(n-1) + fibRec(n - 2);
    }
    
    public static int fibIter(int n) {
        int n2 = 0;
        int n1 = 1;
        for (int k = 0; k < n; k++) {
            n1 = n2 + n1;
            n2 = n1 - n2;
        }
        return n2;
    }
}
