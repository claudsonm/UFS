package sintaxe_abstrata;

import java.util.List;

public class Funcao extends Dec {
    public Tipo tipo;
    public String id;
    public List<Parametro> listaParam;
    public Exp exp;
    
    public Funcao(Tipo tipo, String id, List<Parametro> lista, Exp exp) {
        this.tipo = tipo;
        this.id = id;
        this.listaParam = lista;
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitFuncao(this);
        return null;
    }

}
