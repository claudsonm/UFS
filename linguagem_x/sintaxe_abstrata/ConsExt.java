package sintaxe_abstrata;

import java.util.List;

public class ConsExt extends DCons {
    public Tipo tipo;
    public String id;
    public List<Exp> exp;
    
    public ConsExt(Tipo tipo, String id, List<Exp> exp) {
        this.tipo = tipo;
        this.id = id;
        this.exp = exp;
    }

    @Override
    public Object accept(Visitor vis) {
        return vis.visitConsExt(this);
    }
    
    @Override
    public String toString() {
        return "ConsExt(" + tipo + ", " + id + ", " + exp + ")";
    }

}
