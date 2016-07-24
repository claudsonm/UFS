/**
 * Os códigos foram desenvolvidos durante as aulas pelos seguintes alunos
 * 
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 */
package projeto_algoritmos;

public class DivisaoConquista {
    
    public static int partition(int v[], int i, int s){
        int p = i - 1;
        int aux;
        
        while (i < s){
            while (v[i] < v[p] && i < v.length)
                i++;
            
            while(v[s] > v[p] && s > 0)
                s--;
            
            aux = v[s];
            v[s] = v[i];
            v[i] = aux;
        }
        
        aux = v[s];
        v[s] = v[p];
        v[p] = aux;
        
        return s; 
    }
    
}
// 7 2 1 4s 8i