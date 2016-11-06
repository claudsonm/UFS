package checagem;

import java.util.List;

public class VinculavelFuncProc extends Vinculavel {
    public boolean isFunc;

    /*
     * Atributos das funções: f(int, real): real
     * Nota: alterado para que func e proc recebam o tipo PassagemTipoSemantico
     */
    public List<PassagemTipoSemantico> paramFunc;
    public TipoSemantico tipoRetorno;

    /*
     * Atributos dos procedimentos: p((ref, int), (valor, real))
     */
    public List<PassagemTipoSemantico> paramProc;

    /**
     * Construtor para funções
     *
     * @param p
     * @param r
     */
    public VinculavelFuncProc(List<PassagemTipoSemantico> p, TipoSemantico r) {
        this.isFunc = true;
        this.paramFunc = p;
        this.tipoRetorno = r;
    }

    /**
     * Construtor para procedimentos
     *
     * @param p
     */
    public VinculavelFuncProc(List<PassagemTipoSemantico> p) {
        this.isFunc = false;
        this.paramProc = p;
    }
}
