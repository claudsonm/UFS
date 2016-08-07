package linguagem_x;

import java.util.List;

public class Funcao extends Dec {
    Tipo tipo;
    String id;
    List<Parametro> listaParam;
    Exp exp;
    
    public Funcao(Tipo tipo, String id, List<Parametro> lista, Exp exp) {
        this.tipo = tipo;
        this.id = id;
        this.listaParam = lista;
        this.exp = exp;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitFuncao(this);
        return null;
    }

}
