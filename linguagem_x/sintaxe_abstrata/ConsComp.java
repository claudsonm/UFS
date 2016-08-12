package sintaxe_abstrata;

import java.util.List;

public class ConsComp extends DCons {
    public Tipo tipo;
    public String id;
    public List<String> lista;
    public Exp exp;
    
    public ConsComp(Tipo tipo, String id, List<String> lista, Exp exp) {
        this.tipo = tipo;
        this.id = id;
        this.lista = lista;
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        vis.visitConsComp(this);
        return null;
    }

}
