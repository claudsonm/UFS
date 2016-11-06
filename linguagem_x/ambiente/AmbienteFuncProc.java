package ambiente;

import checagem.*;
import java.util.HashMap;
import java.util.List;

/**
 * Gerencia a tabela de símbolos de funções e procedimentos do Checker. Atua como uma fábrica dos
 * flyweights para gerar objetos da classe concreta VinculavelFuncProc.
 *
 * @author Claudson Martins
 * @author Edgar Lima
 * @author Guilherme Boroni
 */
public class AmbienteFuncProc {

    /**
     * Tabela hash que mapeia o nome de um identificador para uma Vinculavel, que contém as
     * informações sobre os tipos das funções ou procedimentos.
     */
    public static final HashMap<String, Vinculavel> tabela = new HashMap<>();

    /**
     * Recupera ou cria na tabela de símbolos as informações sobre uma <b>função</b>.
     *
     * @param id nome do identificador
     * @param p lista de tipos dos parâmetros formais
     * @param r informações sobre o tipo de retorno
     * @return informações sobre a função
     */
    public VinculavelFuncProc lookupFuncProc(
            String id, List<PassagemTipoSemantico> p, TipoSemantico r) {
        VinculavelFuncProc x = (VinculavelFuncProc) tabela.get(id);
        if (x == null) {
            x = new VinculavelFuncProc(p, r);
            tabela.put(id, x);
            System.out.println(
                    "Criando FuncProc no ambiente: func " + id + "(" + x.paramFunc + ")");
        }
        return x;
    }

    /**
     * Recupera ou cria na tabela de símbolos as informações sobre um <b>procedimento</b>.
     *
     * @param id nome do identificador
     * @param p lista de tipos dos parâmetros formais
     * @return informações sobre a procedimento
     */
    public VinculavelFuncProc lookupFuncProc(String id, List<PassagemTipoSemantico> p) {
        VinculavelFuncProc x = (VinculavelFuncProc) tabela.get(id);
        if (x == null) {
            x = new VinculavelFuncProc(p);
            tabela.put(id, x);
            System.out.println(
                    "Criando FuncProc no ambiente: proc " + id + "(" + x.paramProc + ")");
        }
        return x;
    }

    /**
     * Recupera da tabela de símbolos a função ou procedimento.
     *
     * @param id nome da função ou procedimento
     * @return informações sobre a função ou procedimento
     */
    public VinculavelFuncProc get(String id) {
        return (VinculavelFuncProc) tabela.get(id);
    }

    /**
     * Verifica se o identificador já existe na tabela de símbolos.
     *
     * @param id nome do identificador
     * @return true caso já exista, false caso não exista
     */
    public boolean contem(String id) {
        return tabela.containsKey(id);
    }
}
