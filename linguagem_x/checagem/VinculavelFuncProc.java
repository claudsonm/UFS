package checagem;

import java.util.List;

public class VinculavelFuncProc extends Vinculavel {
    public boolean isFunc;
    
    /*
     * Atributos das funções: f(int, real): real
     */
    public List<TipoSemantico> paramFunc;
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
    public VinculavelFuncProc(List<TipoSemantico> p, TipoSemantico r) {
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
