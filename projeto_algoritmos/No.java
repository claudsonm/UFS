/**
 * Os códigos foram desenvolvidos durante as aulas pelos seguintes alunos
 * 
 * @author Claudson Bispo Martins Santos    201410042132
 * @author Edgar Vieira Lima Neto           201410042150
 * @author Guilherme Boroni Pereira         201410042197
 */
package projeto_algoritmos;

public class No {
    No esq, dir;
    Integer valor;

    public No(Integer valor) {
        esq = dir = null;
        this.valor = valor;
    }
}
