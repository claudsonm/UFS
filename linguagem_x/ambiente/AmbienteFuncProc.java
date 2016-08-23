package ambiente;

import java.util.HashMap;
import java.util.List;
import checagem.*;

/**
 * Atua como uma fábrica dos Flyweights para gerar objetos da classe concreta
 * VinculavelFuncProc
 */
public class AmbienteFuncProc {
    public static final HashMap<String, Vinculavel> tabela = new HashMap<>();
    
    /**
     * Procura ou cria na tabela de símbolos uma função
     * 
     * @param id
     * @param p
     * @param r
     * @return
     */
    public VinculavelFuncProc lookupFuncProc(String id,
            List<PassagemTipoSemantico> p, TipoSemantico r) {
        VinculavelFuncProc x = (VinculavelFuncProc) tabela.get(id);
        if(x == null) {
            x = new VinculavelFuncProc(p, r);
            tabela.put(id, x);
            System.out.println("Criando FuncProc no ambiente : " + x.isFunc
                    + " " + id + "(" + x.paramFunc + ")");
        }
        return x;
    }
    
    /**
     * Procura ou cria na tabela de símbolos um procedimento
     * 
     * @param id
     * @param p
     * @return
     */
    public VinculavelFuncProc lookupFuncProc(String id, List<PassagemTipoSemantico> p) {
        VinculavelFuncProc x = (VinculavelFuncProc) tabela.get(id);
        if(x == null) {
            x = new VinculavelFuncProc(p);
            tabela.put(id, x);
            System.out.println("Criando FuncProc no ambiente : " + x.isFunc
                    + " " + id + "(" + x.paramProc + ")");
        }
        return x;
    }
    
    /**
     * Procura a função ou procedimento dado o nome do identificador
     * 
     * @param id Nome da função ou procedimento
     * @return
     */
    public VinculavelFuncProc get(String id) {
        return (VinculavelFuncProc) tabela.get(id);
    }
    
    /**
     * Retorna true se o identificador já existe na tabela de símbolos.
     * 
     * @param id Nome da função ou procedimento a ser verificado
     * @return
     */
    public boolean contem(String id) {
        return tabela.containsKey(id);
    }
}
