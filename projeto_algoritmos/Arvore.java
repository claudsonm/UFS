/**
 * Os códigos foram desenvolvidos durante as aulas pelos seguintes alunos
 * 
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 */
package projeto_algoritmos;

public class Arvore {

    private No raiz;
    
    public Arvore(No raiz) {
        this.raiz = raiz;
    }
        
    public void emOrdem () {
        this.emOrdem(this.raiz);
    }
    
    private void emOrdem (No no) {
        if (no.esq != null) emOrdem(no.esq);
        System.out.println(no.valor);
        if (no.dir != null) emOrdem(no.dir);
    }
    
    public void posOrdem () {
        this.posOrdem(this.raiz);
    }
    
    private void posOrdem (No no) {
        if (no.esq != null) posOrdem(no.esq);
        if (no.dir != null) posOrdem(no.dir);
        System.out.println(no.valor);
    }
    
    public void preOrdem () {
        this.preOrdem(this.raiz);
    }
    
    private void preOrdem (No no) {
        System.out.println(no.valor);
        if (no.esq != null) preOrdem(no.esq);
        if (no.dir != null) preOrdem(no.dir);
    }
}