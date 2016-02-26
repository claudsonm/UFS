/**
 * Os códigos foram desenvolvidos durante as aulas pelos seguintes alunos
 * 
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 */
package projeto_algoritmos;

public class Fatorial {
    public static long fatRec(int n){
        long x = 1;
        if ( n > 0 )
            x = n * fatRec(n-1); 
        
        return x;
    }
    
    public static long fatIter (int n) {
        long x = 1;
        while (n > 0)
            x = x * n--;
        
        return x;
    }
}
