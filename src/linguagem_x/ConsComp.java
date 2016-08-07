package linguagem_x;

import java.util.List;

public class ConsComp extends DCons {
    Tipo tipo;
    String id;
    List<String> lista;
    Exp exp;
    
    public ConsComp(Tipo tipo, String id, List<String> lista, Exp exp) {
        this.tipo = tipo;
        this.id = id;
        this.lista = lista;
        this.exp = exp;
    }

    @Override
    Object accept(Visitor vis) {
        vis.visitConsComp(this);
        return null;
    }

}
