package ambiente;

import java.util.HashMap;
import checagem.*;

/**
 * Atua como uma fábrica dos Flyweights para gerar objetos da classe concreta
 * VinculavelConsVar
 */
public class AmbienteConsVar {
    public static final HashMap<String, Vinculavel> tabela = new HashMap<>();
    
    /**
     * Procura ou cria na tabela de símbolos uma variável ou constante
     * 
     * @param id
     * @param b
     * @param t
     * @return
     */
    public VinculavelConsVar lookup(String id, boolean b, TipoSemantico t) {
        VinculavelConsVar x = this.get(id);
        if(x == null) {
            x = new VinculavelConsVar(b, t);
            tabela.put(id, x);
            System.out.println(
                "Criando ConsVar no ambiente : " + x.isVar + " " + x.tipo + " " + id
            );
        }
        return x;
    }
    
    /**
     * Procura e retorna a instância de um elemento na tabela de símbolos
     * 
     * @param id
     * @return
     */
    public VinculavelConsVar get(String id) {
        return (VinculavelConsVar) tabela.get(id);
    }
}
